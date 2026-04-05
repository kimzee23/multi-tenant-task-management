package com.indent.multitenanttodoapplication.infrastructure.config;

import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.JwtUtil;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.TenantContext;
import io.jsonwebtoken.Claims;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class JwtFilter implements Filter {
    private final JwtUtil jwtUtil;
    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest http = (HttpServletRequest) request;

        String header = http.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            Claims claims = jwtUtil.validateToken(token);

            String tenantId = claims.get("tenantId", String.class);

            TenantContext.setTenantId(tenantId);
        }

        chain.doFilter(request, response);

        TenantContext.clear();
    }
}