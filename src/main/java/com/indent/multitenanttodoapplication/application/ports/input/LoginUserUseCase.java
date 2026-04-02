package com.indent.multitenanttodoapplication.application.ports.input;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

public interface LoginUserUseCase {
   UserModel login(String tenantId, String email, String password);
}
