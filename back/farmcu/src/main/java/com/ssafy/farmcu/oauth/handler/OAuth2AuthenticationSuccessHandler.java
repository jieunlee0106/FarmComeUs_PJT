package com.ssafy.farmcu.oauth.handler;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.repository.MemberRepository;
import com.ssafy.farmcu.api.service.member.MemberRefreshTokenServiceImpl;
import com.ssafy.farmcu.config.properties.AppProperties;
import com.ssafy.farmcu.api.entity.member.MemberRefreshToken;
import com.ssafy.farmcu.api.entity.member.ProviderType;
import com.ssafy.farmcu.api.entity.member.RoleType;
import com.ssafy.farmcu.oauth.Info.KakaoMemberInfo;
import com.ssafy.farmcu.oauth.Info.OAuth2MemberInfo;
import com.ssafy.farmcu.oauth.repository.MemberRefreshTokenRepository;
import com.ssafy.farmcu.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.ssafy.farmcu.oauth.token.AuthToken;
import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import com.ssafy.farmcu.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component // component 안해도 나오긴 함
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final MemberRepository memberRepository;
    private final MemberRefreshTokenServiceImpl memberRefreshTokenService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess");
        // 인증한 url
        String targetUrl = determineTargetUrl(request, response, authentication);
        log.info("targetUrl: " + targetUrl);

        if (response.isCommitted()) {
            log.info("이미 commit된 응답");
            return;
        }


        clearAuthenticationAttributes(request, response);
        // redirect
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUrl = CookieUtil.getCookie(request, OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUrl.isPresent() && !isAuthorizedRedirectUri(redirectUrl.get()))
            throw new IllegalArgumentException("승인되지 않은 redirect uri라 인증을 진행할 수 없음");

        String targetUrl = redirectUrl.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        ProviderType providerType = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

        OidcUser user = ((OidcUser) authentication.getPrincipal());
        OAuth2MemberInfo memberInfo = new KakaoMemberInfo(user.getAttributes());

        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

        RoleType roleType = RoleType.ROLE_USER;

        String Id = memberInfo.getProvider()+"-"+memberInfo.getProviderId();
        Member member = memberRepository.findById(Id).orElse(null);


        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(
                Long.toString(member.getMemberId()),
                member.getRoleType(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );

        // refreshToken 설정
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

        AuthToken refreshToken = tokenProvider.createAuthToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        // DB 저장
        MemberRefreshToken memberRefreshToken = memberRefreshTokenRepository.findById(Id);
        memberRefreshTokenService.saveRefreshTokenTable(refreshToken.getToken(), member.getId());

        int cookieMaxAge = (int) refreshTokenExpiry / 60;

        CookieUtil.deleteCookie(request, response, OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN);
        CookieUtil.addCookie(response, OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);


        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", accessToken.getToken())
                .queryParam("nickname", member.getNickname())
                .build().toUriString();
    }

    /**
     * 권한이 있는지 확인
     *
     * @param authorities
     * @param authority
     * @return
     */
    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
        if (authorities == null) return false;
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority()))
                return true;
        }
        return false;
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        // 리다이렉트 URI
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort();
                });
    }

    /**
     * 세션에 저장된 에러 지우기
     *
     * @param request
     * @param response
     */
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}
