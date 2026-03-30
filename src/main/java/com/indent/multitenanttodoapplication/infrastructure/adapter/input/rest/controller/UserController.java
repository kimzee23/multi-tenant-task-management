package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.UserRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.UserResponse;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private CreateUserUseCase useCase;

    public UserController(CreateUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public UserResponse createUser(
            @RequestHeader("X-Tenant-ID") String tenantId,
            @RequestBody UserRequest request
    )
    {
        UserModel user = useCase.createUser(
                tenantId,
                request.getEmail(),
                UserRole.valueOf(request.getRole())
        );
        return UserMapper.toResponse(user);
    }
}
