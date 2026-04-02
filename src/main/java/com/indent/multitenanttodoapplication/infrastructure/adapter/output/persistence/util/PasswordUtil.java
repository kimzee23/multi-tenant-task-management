package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util;

import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;

public class PasswordUtil {
    private static final BcryptPassword4jPasswordEncoder encoder = new BcryptPassword4jPasswordEncoder();

    public static String hash(String password){
        return encoder.encode(password);
    }
    public static boolean matches(String raw, String hashed){
        return encoder.matches(raw, hashed);
    }
}
