package com.msedonald.user;

import com.msedonald.user.data.UserSave;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public void signup(@RequestBody UserSave userSave) {
        userService.save(userSave);
    }

    @GetMapping("/api/users")
    public void login() {

    }

    @DeleteMapping("/api/users")
    public void logout() {

    }
}
