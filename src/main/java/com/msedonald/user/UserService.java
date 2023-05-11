package com.msedonald.user;

import com.msedonald.user.data.UserSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserSave userSave) {
        userRepository.save(User.createUser(userSave));
    }
}
