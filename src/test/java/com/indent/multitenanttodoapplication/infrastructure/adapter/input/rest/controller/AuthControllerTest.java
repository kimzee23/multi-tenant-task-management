package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.domain.service.LoginService;
import com.indent.multitenanttodoapplication.domain.service.RegisterService;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.RegisterRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.AuthResponse;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.JwtUtil;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.PasswordUtil;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.TenantContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthControllerTest {
    @Mock
    private UserRepositoryPort repo;
    @Test
  public void shouldLoginSuccessfully() {

        UserRepositoryPort repo = mock(UserRepositoryPort.class);

        LoginService useCase = new LoginService(repo);

        String rawPassword = "123456";
        String hashed = PasswordUtil.hash(rawPassword);

        when(repo.findByEmailAndTenantId("test@mail.com", "t1"))
                .thenReturn(Optional.of(
                        UserModel.builder()
                                .email("test@mail.com")
                                .password(hashed)
                                .tenantId("t1")
                                .build()
                ));
        UserModel user = useCase.login("t1", "test@mail.com", rawPassword);

        assertEquals("test@mail.com", user.getEmail());
    }

    @Test
    void shouldFailLoginWithWrongPassword() {

        UserRepositoryPort repo = mock(UserRepositoryPort.class);

        LoginService useCase = new LoginService(repo);

        when(repo.findByEmailAndTenantId(any(), any()))
                .thenReturn(Optional.of(UserModel.builder()
                        .password(PasswordUtil.hash("correct"))
                        .build()));

        assertThrows(RuntimeException.class, () ->
                useCase.login("t1", "mail", "wrong")
        );
    }
    @Test
   public void shouldRegisterUser() {

        RegisterService register = mock(RegisterService.class);
        LoginService login = mock(LoginService.class);
        JwtUtil jwtUtil = mock(JwtUtil.class);

        AuthController controller = new AuthController(register, login, jwtUtil);

        TenantContext.setTenantId("t1");

        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@mail.com");
        request.setPassword("123");

        when(register.register(any(), any(), any(),any()))
                .thenReturn(UserModel.builder()
                        .email("test@mail.com")
                        .tenantId("t1")
                        .phoneNumber("08115000080")
                        .role(UserRole.USER)
                        .build());

        AuthResponse response = controller.register(request);

        assertEquals("test@mail.com", response.getEmail());

        TenantContext.clear();
    }

}