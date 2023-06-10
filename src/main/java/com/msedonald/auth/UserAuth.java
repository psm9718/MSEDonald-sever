package com.msedonald.auth;

import lombok.Builder;

@Builder
public record UserAuth(Long id, String username) {
}
