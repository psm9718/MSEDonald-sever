package com.msedonald.user.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLogin {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;

    @Builder
    private UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}