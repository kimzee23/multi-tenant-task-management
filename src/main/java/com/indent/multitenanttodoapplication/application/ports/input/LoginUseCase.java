package com.indent.multitenanttodoapplication.application.ports.input;

public interface LoginUseCase {
    String login(String tenantId, String email, String password);
}
