package com.msedonald.result;

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

    private final UserRepository userRepository;
    private final ResultRepository resultRepository;

    @Transactional
    public void save(Long id, ResultSave resultSave) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundAuthException::new);

        resultRepository.save(
                Result.builder()
                        .score(resultSave.score())
                        .user(user)
                        .winOrLose(resultSave.winOrLose())
                        .build()
        );
    }

    public List<ResultResponse> getResults(Long id) {
        log.info("> user {} try to get results", id);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundAuthException::new);

        return resultRepository.findTop5ByUserOrderByCreatedDateDesc(user).stream()
                .map(ResultResponse::new)
                .toList();
    }
}
