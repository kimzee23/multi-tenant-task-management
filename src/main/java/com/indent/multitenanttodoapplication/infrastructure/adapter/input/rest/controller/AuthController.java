package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.service.LoginService;
import com.indent.multitenanttodoapplication.domain.service.RegisterService;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.LoginRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.RegisterRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.AuthResponse;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.JwtUtil;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public AuthController(RegisterService registerService,
                          LoginService loginService,
                          JwtUtil jwtUtil) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        UserModel user = registerService.register(
                request.getTenantId(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber()
        );

        return AuthResponse.builder()
                .email(user.getEmail())
                .tenantId(user.getTenantId())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        UserModel user = loginService.login(
                request.getTenantId(),
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .tenantId(user.getTenantId())
                .role(user.getRole().name())
                .token(token)
                .build();
    }
}
