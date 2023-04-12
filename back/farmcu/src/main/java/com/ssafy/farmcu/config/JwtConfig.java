package com.ssafy.farmcu.config;

import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public AuthTokenProvider jwtProvider() { return new AuthTokenProvider(secret); }
}
