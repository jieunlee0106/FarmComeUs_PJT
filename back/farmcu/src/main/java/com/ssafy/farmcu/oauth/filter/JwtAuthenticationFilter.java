package com.ssafy.farmcu.oauth.filter;

import com.ssafy.farmcu.oauth.token.AuthToken;
import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final AuthTokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더('AUTH')에서 존재할 경우, JWT 를 받아옵니다.
        AuthToken authToken = tokenProvider.convertAuthToken(((HttpServletRequest) request).getHeader("token"));
        String token = authToken.getToken();
        // 유효한 토큰인지 확인합니다.
        if (token != null && authToken.validate()) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = tokenProvider.getAuthentication(authToken);
            // SecurityContextHolder 에 인증 객체를 넣는다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // ExpiredError을 받아서 컨트롤러에 Unauthorized error을 보냅니다.
            // 얘는 좀 아닌거 같애... 문제 : 5순위
        }
        chain.doFilter(request, response);
    }
}