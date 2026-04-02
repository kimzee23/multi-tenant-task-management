package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util;

import com.indent.multitenanttodoapplication.domain.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET = "secret";

    public String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("tenantId", user.getTenantId())
                .claim("role", user.getRole().name())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
