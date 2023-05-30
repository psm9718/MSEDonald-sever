package com.msedonald.socket;

import com.msedonald.auth.LoginUser;
import com.msedonald.auth.UserAuth;
import com.msedonald.socket.data.MessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

//    @MessageMapping("/game")
//    public void handleGameMessage(MessageDTO message) {
//        log.info("Game message received: {}", message);
//        simpMessageSendingOperations.convertAndSend("/sub", message);
//    }

//    @MessageMapping("/game")
//    public void handleGameMessage(@Payload String payload) {
//        log.info("Game message received: {}", payload);
//        simpMessageSendingOperations.convertAndSend("/sub", payload);
//    }

    @GetMapping("/api/test")
    @Operation(summary = "test api", description = "response with String \"test api\" Response body ")
    public String test() {
        log.info(">> test api received");
        return "test api";
    }

    @GetMapping("/api/test/auth")
    @Operation(summary = "test auth api", description = "test Auth, response with String \"authenticated access\" Response body ")
    public String testAuth(@LoginUser UserAuth userAuth) {
        log.info("> auth access : {}", userAuth.getUsername());
        return "authenticated access";
    }
}
