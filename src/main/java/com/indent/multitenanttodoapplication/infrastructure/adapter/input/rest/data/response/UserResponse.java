package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
    private String id;
    private String role;
    private String email;
    private String  tenantId;

}
