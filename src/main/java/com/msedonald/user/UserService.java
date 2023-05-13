package com.msedonald.user;

import com.msedonald.user.data.UserLogin;
import com.msedonald.user.data.UserSave;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final Map<String, Long> sessoinMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    @Transactional
    public void save(UserSave userSave) {
        userRepository.save(User.createUser(userSave));
        log.info("> user {} saved in DB", userSave.username());
    }

    public String login(UserLogin userLogin) {
        User user = userRepository.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword())
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.info("> user {} login to server [ {} ]", user.getUsername(), user.getId());

        return String.valueOf(UUID.randomUUID());
    }

    public void expire(String accessToken) {
        sessoinMap.remove(accessToken);
        log.info("> token {} successfully expired [{}]", accessToken, LocalDateTime.now());
    }
}
