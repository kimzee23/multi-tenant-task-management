package com.indent.multitenanttodoapplication.domain.validator;

import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;

public class UserValidator {
    public static void validate(UserModel user){
        if(user.getTenantId()== null || user.getTenantId().isBlank()){
            throw new ValidationException("Tenant is required");
        }
        if (user.getEmail()==null || user.getEmail().isBlank()){
            throw new ValidationException("Email is required");
        }
        if(user.getRole()== null){
            throw new ValidationException("Role is required");
        }
    }
}
