package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request;

import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;

@Setter
@Getter
public class UserRequest {
    private String tenantId;
    private String email;
    private String role;
    private String PhoneNumber;
    private String Password;
}
