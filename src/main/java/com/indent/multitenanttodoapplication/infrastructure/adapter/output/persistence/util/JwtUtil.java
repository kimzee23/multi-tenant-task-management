package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util;

import com.indent.multitenanttodoapplication.domain.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "secretsecretsecretsecretsecret";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("tenantId", user.getTenantId())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }


    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
