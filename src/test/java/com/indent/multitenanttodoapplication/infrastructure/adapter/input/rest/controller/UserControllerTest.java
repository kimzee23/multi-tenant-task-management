package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.UserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private final CreateUserUseCase useCase = mock(CreateUserUseCase.class);

    private final UserController controller =
            new UserController(useCase);

    @Test
    void shouldCreateUserSuccessfully() {

     UserRequest request = new UserRequest();
        request.setEmail("test@mail.com");
        request.setRole("ADMIN");

        when(useCase.createUser(any(), any(), any()))
                .thenReturn(UserModel.builder()
                        .id("1")
                        .tenantId("tenant-1")
                        .email("test@mail.com")
                        .role(UserRole.ADMIN)
                        .createdAt("now")
                        .build());

        var response = controller.createUser("tenant-1", request);

        assertEquals("test@mail.com", response.getEmail());
        assertEquals("ADMIN", response.getRole());

        verify(useCase).createUser("tenant-1", "test@mail.com", UserRole.ADMIN);
    }

}