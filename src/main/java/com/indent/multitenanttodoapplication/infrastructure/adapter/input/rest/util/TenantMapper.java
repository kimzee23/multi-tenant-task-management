package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.util;

import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.TenantResponse;

public class TenantMapper {

    public static TenantResponse toResponse(Tenant tenant) {
        return TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .createdAt(tenant.getCreatedAt())
                .build();
    }
}
