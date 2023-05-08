package com.msedonald.game;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @GetMapping("/api/test")
    public String startGame() {
        return "test api";
    }

    @PostMapping("/api/post")
    public String postTest(@RequestBody String str) {
        return "POST ok" + str;
    }
}
