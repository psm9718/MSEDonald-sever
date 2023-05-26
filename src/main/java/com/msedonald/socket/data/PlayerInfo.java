package com.msedonald.socket.data;

import lombok.Builder;

@Builder
public record PlayerInfo(float move_x, float move_z, float rotation, int onHandle, int isInteract, int handIndex) {
}
