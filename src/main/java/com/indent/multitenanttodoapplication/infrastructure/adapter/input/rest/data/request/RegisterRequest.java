package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String tenantId;
    private String email;
    private String phoneNumber;
    private String password;

}
