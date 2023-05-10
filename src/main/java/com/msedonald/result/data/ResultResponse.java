package com.msedonald.result.data;

import com.msedonald.result.Result;
import lombok.Getter;

@Getter
public class ResultResponse {

    private Long score;
    private String username;

    public ResultResponse(Result result) {
        this.score = result.getScore();
        this.username = result.getUser().getUsername();
    }
}