package com.msedonald.result.data;

import lombok.Builder;

@Builder
public record ResultSave(String token, Long score, WinOrLose winOrLose) {
}
