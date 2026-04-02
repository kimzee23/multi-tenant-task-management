package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String phoneNumber;
    private String password;

}
