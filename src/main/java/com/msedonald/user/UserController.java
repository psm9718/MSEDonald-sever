package com.msedonald.user;

import com.msedonald.user.data.UserLogin;
import com.msedonald.user.data.UserSave;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    @Operation(summary = "user sign up", description = "Require user name & password JSON Request Body")
    public void signup(@RequestBody UserSave userSave) {
        userService.save(userSave);
    }

    @PostMapping("/api/users/login")
    @Operation(summary = "user login", description = "response with access token to auth")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLogin userLogin) {
        String token = userService.login(userLogin);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("access_token", token);

        return ResponseEntity.ok(responseMap);
    }

    @DeleteMapping("/api/users")
    @Operation(summary = "user logout", description = "expire access token")
    public void logout(@RequestBody String accessToken) {
        userService.expire(accessToken);
    }
}
