package com.indent.multitenanttodoapplication.application.ports.input;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

import java.util.List;


public interface GetUsersByTenantUseCase {
    List<UserModel> getUsersByTenant(String tenantId);
}
