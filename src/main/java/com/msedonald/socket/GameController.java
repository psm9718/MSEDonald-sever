package com.msedonald.socket;

import com.msedonald.auth.LoginUser;
import com.msedonald.auth.UserAuth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameController {

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
