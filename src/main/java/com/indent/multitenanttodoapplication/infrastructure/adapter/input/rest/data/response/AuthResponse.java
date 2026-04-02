package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuthResponse {
    private String tenantId;
    private String email;
    private String role;
}
