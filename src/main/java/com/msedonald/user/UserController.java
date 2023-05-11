package com.msedonald.user;

import com.msedonald.user.data.UserLogin;
import com.msedonald.user.data.UserSave;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public void signup(@RequestBody UserSave userSave) {
        userService.save(userSave);
    }

    @GetMapping("/api/users")
    public Map<String, String> login(@RequestBody UserLogin userLogin) {
        String token = userService.login(userLogin);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("access token", token);

        return responseMap;
    }

    @DeleteMapping("/api/users")
    public void logout(@RequestBody String accessToken) {
        userService.expire(accessToken);
    }
}
