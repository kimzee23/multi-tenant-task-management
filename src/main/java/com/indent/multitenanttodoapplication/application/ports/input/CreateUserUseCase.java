package com.indent.multitenanttodoapplication.application.ports.input;

import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;

public interface CreateUserUseCase {
    UserModel createUser(String tenantId, String email, UserRole role);
}
