package com.indent.multitenanttodoapplication.application.ports.output;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    UserModel save(UserModel user);
    boolean existsByEmailAndTenantId(String email, String tenantId);

    List<UserModel> findByTenantId(String tenantId);

    Optional<UserModel> findByEmailAndTenantId(String email, String tenantId);



}
