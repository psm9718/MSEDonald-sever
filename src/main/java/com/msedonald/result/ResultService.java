package com.msedonald.result;

import com.msedonald.auth.JwtProvider;
import com.msedonald.exception.UserNotFoundAuthException;
import com.msedonald.result.data.ResultResponse;
import com.msedonald.result.data.ResultSave;
import com.msedonald.user.User;
import com.msedonald.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    @Transactional
    public void save(ResultSave resultSave) {

        Long userIdFromJwt = jwtProvider.getUserIdFromJwt(resultSave.token());
        User user = userRepository.findById(userIdFromJwt)
                .orElseThrow(UserNotFoundAuthException::new);

        resultRepository.save(
                Result.builder()
                        .score(resultSave.score())
                        .user(user)
                        .winOrLose(resultSave.winOrLose())
                        .build()
        );
    }

    public List<ResultResponse> getResults(String token) {
        log.info("> user {} try to get results", token);
        Long userIdFromJwt = jwtProvider.getUserIdFromJwt(token);
        User user = userRepository.findById(userIdFromJwt)
                .orElseThrow(UserNotFoundAuthException::new);

        return resultRepository.findTop5ByUserOrderByScoreDesc(user).stream()
                .map(ResultResponse::new)
                .toList();
    }
}
