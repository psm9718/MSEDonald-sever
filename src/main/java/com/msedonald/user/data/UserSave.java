package com.msedonald.user.data;

import lombok.Builder;

@Builder
public record UserSave(String username, String password) {
}
