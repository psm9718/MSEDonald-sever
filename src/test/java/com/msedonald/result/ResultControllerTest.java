package com.msedonald.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msedonald.auth.JwtProvider;
import com.msedonald.exception.UserNotFoundAuthException;
import com.msedonald.result.data.ResultSave;
import com.msedonald.user.User;
import com.msedonald.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.msedonald.result.data.WinOrLose.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ResultControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("result save")
    void save() throws Exception {
        //given
        User user = User.builder()
                .username("tester")
                .password("pwd")
                .build();
        userRepository.save(user);

        //when
        ResultSave resultSave = ResultSave.builder()
                .token(jwtProvider.generateAccessToken(user.getId(), user.getUsername()))
                .score(1000L)
                .winOrLose(WIN)
                .build();

        mockMvc.perform(post("/api/scores")
                        .content(objectMapper.writeValueAsString(resultSave))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        List<Result> results = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundAuthException::new)
                .getResults();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getScore()).isEqualTo(1000L);

    }


}