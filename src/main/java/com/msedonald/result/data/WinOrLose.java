package com.msedonald.result.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WinOrLose {
    WIN,
    LOSE;

    @JsonCreator
    public static WinOrLose fromString(String key) {
        for (WinOrLose winOrLose : WinOrLose.values()) {
            if (winOrLose.name().equalsIgnoreCase(key)) {
                return winOrLose;
            }

        }
        throw new IllegalArgumentException("Invalid value for WinOrLose enum: " + key);
    }
}

