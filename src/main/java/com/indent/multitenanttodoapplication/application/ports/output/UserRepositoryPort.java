package com.indent.multitenanttodoapplication.application.ports.output;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

import java.util.List;

public interface UserRepositoryPort {
    UserModel save(UserModel user);
    boolean existsByEmailAndTenantId(String email, String tenantId);

    List<UserModel> findByTenantId(String tenantId);


}
