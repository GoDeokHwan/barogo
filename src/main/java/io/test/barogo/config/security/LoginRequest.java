package io.test.barogo.config.security;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String loginId;
    private String password;
}
