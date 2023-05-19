package com.msedonald.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAuth {
    private final Long id;
    private final String username;

    @Builder
    public UserAuth(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
