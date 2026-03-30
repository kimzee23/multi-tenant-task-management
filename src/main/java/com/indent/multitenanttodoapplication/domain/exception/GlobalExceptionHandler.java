package com.indent.multitenanttodoapplication.domain.exception;

import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {

        if (isEnumError(ex)) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", "Invalid role",
                            "message", "Allowed values: " + getAllowedRoles()
                    )
            );
        }

        return ResponseEntity.badRequest().body(
                Map.of("error", ex.getMessage())
        );
    }

    private boolean isEnumError(IllegalArgumentException ex) {
        return ex.getStackTrace()[0].getClassName().equals("java.lang.Enum");
    }

    private String getAllowedRoles() {
        return Arrays.stream(UserRole.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
