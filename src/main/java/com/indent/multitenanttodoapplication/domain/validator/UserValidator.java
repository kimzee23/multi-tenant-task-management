package com.indent.multitenanttodoapplication.domain.validator;

import com.indent.multitenanttodoapplication.domain.model.UserModel;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{10,15}$");
    public static void validate(UserModel user)  {
        if(user.getTenantId()== null || user.getTenantId().isBlank()){
            throw new IllegalArgumentException("Tenant is required");
        }
        if (user.getEmail()==null || user.getEmail().isBlank()){
            throw new IllegalArgumentException ("Email is required");
        }
        if(user.getRole()== null){
            throw new IllegalArgumentException ("Role is required");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalArgumentException ("Invalid email format baba");
        }
        if (user.getPhoneNumber() != null && !PHONE_PATTERN.matcher(user.getPhoneNumber()).matches())

        if (user.getPassword()== null || user.getPassword().length()<6){
            throw new IllegalArgumentException("Password can't be blank or less than six characters ");
        }
    }
}
