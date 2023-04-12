package com.ssafy.farmcu.oauth.repository;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.ssafy.farmcu.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Cookie 사용 시
@Slf4j
public class OAuth2AuthorizationRequestBasedOnCookieRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public final static String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public final static String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    public final static String REFRESH_TOKEN = "refresh_token";
    private final static int cookieExpireSeconds = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        // request 헤더>쿠키에서 oauth2에 대한 내용 찾기
        return CookieUtil.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null); // 없으면 null 반환
    }

    /**
     * 인증 코드 부여 흐름을 시작하기 전에, 인증 요청 유지를 위해 OAuth2AuthorizationRequestRedirectFilter 를 사용.
     */
    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null) {
            CookieUtil.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
            CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
            log.debug("saveAuthorizationRequest!");
            return;
        }

        CookieUtil.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookieUtil.serialize(authorizationRequest), cookieExpireSeconds);
        log.debug("request = {}", request.getRequestURI());
        // redirect_uri 쿼리 파람 가져오기
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        // 파람이 있다
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            log.debug("cookie redirect_url = {}", redirectUriAfterLogin);
            CookieUtil.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, cookieExpireSeconds);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
    }
}


