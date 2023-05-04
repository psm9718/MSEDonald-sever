package com.msedonald.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @RequestMapping("/api/test")
    public String startGame() {
        return "test api";
    }
}
