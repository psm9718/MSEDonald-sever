package com.msedonald.result.data;

import lombok.Builder;

@Builder
public record ResultSave(Long userId, Long score) {
}
