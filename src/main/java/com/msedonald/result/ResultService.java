package com.msedonald.result;

import com.msedonald.result.data.ScoreSave;
import com.msedonald.user.User;
import com.msedonald.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    public void save(ScoreSave scoreSave) {

        User user = userRepository.findById(scoreSave.userId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        resultRepository.save(
                Result.builder()
                .score(scoreSave.score())
                .user(user)
                .build()
        );
    }
}
