package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantResponse {
    private String id;
    private String name;
    private String createdAt;
}
