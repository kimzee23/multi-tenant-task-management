package com.indent.multitenanttodoapplication.application.ports.output;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

public interface UserRepositoryPort {
    UserModel save(UserModel user);
    boolean existsByEmailAndTenantId(String email, String tenantId);
}
