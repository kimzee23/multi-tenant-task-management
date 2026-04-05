package com.indent.multitenanttodoapplication.application.ports.input;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

public interface RegisterUserUseCase {
    UserModel register(String tenantId, String email, String password, String phoneNumber);

}
