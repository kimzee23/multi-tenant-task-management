package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

;

public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String tenantId = request.getHeader("X-Tenant-ID");

        if (tenantId == null || tenantId.isBlank()) {
            throw new RuntimeException("Missing X-Tenant-ID header");
        }
        TenantContext.setTenantId(tenantId);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        TenantContext.clear();
    }
}
