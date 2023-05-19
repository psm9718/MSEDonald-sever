package com.msedonald.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAuth {
    private final Long id;
    private final String username;
    private final String password;

    @Builder
    public UserAuth(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
