package com.msedonald.result.data;

import lombok.Getter;

@Getter
public record ScoreSave(Long userId, Long score) {
}
