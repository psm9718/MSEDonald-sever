package com.msedonald.socket.data;

import lombok.Builder;

public record Location(Long x, Long z) {

    @Builder
    public Location {
    }
}
