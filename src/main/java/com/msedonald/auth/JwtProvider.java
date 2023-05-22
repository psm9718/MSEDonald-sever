package com.msedonald.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

@Slf4j
@Component
public class JwtProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    public String generateAccessToken(Long id, String username) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Long getUserIdFromJwt(String authToken) {
        return Long.parseLong(getClaimsJws(authToken).getBody().getSubject());
    }

    public String getUsernameFromJwt(String authToken) {
        return getClaimsJws(authToken).getBody().get("username", String.class);
    }

    private Jws<Claims> getClaimsJws(String authToken) {
        return Jwts.parserBuilder()
                .setSigningKey(decodeBase64(jwtSecret))
                .build()
                .parseClaimsJws(authToken);
    }
}
