package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String email;
    private String role;
}
