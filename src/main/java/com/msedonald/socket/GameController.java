package com.msedonald.socket;

import com.msedonald.socket.data.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {

    @GetMapping("/api/test")
    public String startGame() {
        log.info(">> test api received");
        return "test api";
    }


    @MessageMapping("/api/enter")
    public void enterGame(@RequestBody MessageDTO messageRequest) {

    }

    @MessageMapping("/api/message")
    public void send() {

    }

}
