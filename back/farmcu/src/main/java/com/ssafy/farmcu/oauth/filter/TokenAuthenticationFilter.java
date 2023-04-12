package com.ssafy.farmcu.oauth.filter;

import com.ssafy.farmcu.oauth.token.AuthToken;
import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import com.ssafy.farmcu.utils.HeaderUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        log.info("filter start ! ");

        // 요청값의 header에서 토큰을 뽑아온다.
        AuthToken token = tokenProvider.convertAuthToken(request.getHeader("token"));

        try {
            if (token != null && token.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                // SecurityContextHolder 에 인증 객체를 넣는다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 에러가 발생했을 때, request에 attribute를 세팅하고 RestAuthenticationEntryPoint로 request를 넘겨준다.
        } catch (MalformedJwtException e) {
            log.info("유효하지 않은 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", "WRONG_TYPE_TOKEN");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            request.setAttribute("exception", ".EXPIRED_ACCESS_TOKEN");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식이나 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", "WRONG_TYPE_TOKEN");
        } catch (IllegalArgumentException e) {
            log.info(e.toString().split(":")[1].trim());
            request.setAttribute("exception","INVALID_ACCESS_TOKEN");
        }

        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}

