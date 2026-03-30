package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.mapper;

import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.UserResponse;

public class UserMapper {
    public static UserResponse toResponse(UserModel user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .tenantId(user.getTenantId())
                .build();
    }
}
