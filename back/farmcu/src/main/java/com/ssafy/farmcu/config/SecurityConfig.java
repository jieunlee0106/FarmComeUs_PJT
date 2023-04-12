package com.ssafy.farmcu.config;

import com.ssafy.farmcu.api.repository.MemberRepository;
import com.ssafy.farmcu.api.service.member.MemberRefreshTokenServiceImpl;
import com.ssafy.farmcu.config.properties.AppProperties;
import com.ssafy.farmcu.config.properties.CorsProperties;
import com.ssafy.farmcu.exception.RestAuthenticationEntryPoint;
import com.ssafy.farmcu.oauth.filter.TokenAuthenticationFilter;
import com.ssafy.farmcu.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.ssafy.farmcu.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.ssafy.farmcu.oauth.repository.MemberRefreshTokenRepository;
import com.ssafy.farmcu.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.ssafy.farmcu.oauth.service.PrincipalDetailsService;
import com.ssafy.farmcu.oauth.service.PrincipalOauth2MemberService;
import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    //    private final CustomOAuth2UserService customOAuth2UserService;
    private final PrincipalOauth2MemberService principalOauth2MemberService;
    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final MemberRefreshTokenRepository memberRefreshTokenRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final CorsProperties corsProperties;
    private final MemberRepository memberRepository;
    private final MemberRefreshTokenServiceImpl memberRefreshTokenService;
//    private final TokenAuthenticationFilter tokenAuthenticationFilter;


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()

                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 열어두어야 CORS Preflight 막을 수 있음
                .antMatchers("/**").permitAll()
                .antMatchers("*/member/**").permitAll()

                // 시큐리티는 기본적으로 세션을 사용
                // 세션을 사용하지 않을거라 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 요청이 들어올 시, 인증 헤더를 보내지 않는 경우 401 응답 처리

                .and()
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization")
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/*/oauth2/code/*")
                .and()
                .userInfoEndpoint()
                .userService(principalOauth2MemberService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler())
                .and().build();
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();

        // 로그인 요청을 가로채 usernamepasswordAuthenticationToken이라는 인증용 객체 생성
//        return http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }

    /*
     * Spring Security에서 인증을 담당하는 AuthenticationManager auth 매니저 설정
     * */
//    @Bean
//    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    /*
    토큰 필터 설정
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /**
     * security 설정 시, 사용할 인코더
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Oauth 인증 성공 핸들러
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                memberRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                memberRepository,
                memberRefreshTokenService
        );
//        return new OAuth2AuthenticationSuccessHandler();
    }

    /*
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
//        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
        return new OAuth2AuthenticationFailureHandler();
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository(){
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(principalDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    /*
     * Cors 설정
     * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
//        corsConfig.addAllowedOriginPattern("*");
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}