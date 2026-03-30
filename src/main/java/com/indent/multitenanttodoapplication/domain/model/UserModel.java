package com.indent.multitenanttodoapplication.domain.model;

import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String id;
    private String tenantId;
    private String email;
    private UserRole role;
    private String createdAt;
}
