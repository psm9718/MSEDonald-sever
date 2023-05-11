package com.msedonald.socket;

import com.msedonald.socket.data.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    @GetMapping("/api/test")
    public String startGame() {
        return "test api";
    }


    @MessageMapping("/api/enter")
    public void enterGame(@RequestBody MessageDTO messageRequest) {

    }

    @MessageMapping("/api/message")
    public void send() {

    }

}
