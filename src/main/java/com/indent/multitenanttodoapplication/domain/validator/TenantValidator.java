package com.indent.multitenanttodoapplication.domain.validator;

import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.Tenant;

public class TenantValidator {
    public static void  validate(Tenant tenant)  {
        if (tenant.getName() == null || tenant.getName().isBlank()){
            throw new ValidationException("Tenant name cannot be empty");
        }

    }
}
