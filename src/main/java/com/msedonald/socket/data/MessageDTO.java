package com.msedonald.socket.data;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageDTO(LocalDateTime timestamp, String token, String data) {
}
