package com.ssafy.farmcu.oauth.token;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthToken { // JwtUtil

    @Getter
    private final String token;
    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

    // refresh token
    public AuthToken(String id, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    // access token
    public AuthToken(String id, String role, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, role, expiry);
    }

    // jwt refresh token 생성
    private String createAuthToken(String id, Date expiry) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    // jwt access token 생성
    private String createAuthToken(String id, String role, Date expiry) {
        return Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }
    public Claims getTokenClaims() {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validate() {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claimsJws.getBody().isEmpty();
    }
//    public boolean validate() {
//        log.info("validate");
//        return this.getTokenClaims() != null;
//    }
//
//    /*
//    토큰 뜯어보기
//     */
//    public Claims getTokenClaims() {
//        log.info("getTokenClaims");
//        log.info("key = {}", key);
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (SecurityException e) {
//            log.info("Invalid JWT signature.");
//        } catch (MalformedJwtException e) {
//            log.info("Invalid JWT token.");
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
//            log.info(e.getMessage());
//        }
//        return null;
//    }

    public Long getMemberIdFromJwt(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return Long.parseLong(String.valueOf(claims.getBody().get("userid")));
    }

    public Claims getExpiredTokenClaims() {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token");
            return e.getClaims();
        }
        return null;
    }
}

