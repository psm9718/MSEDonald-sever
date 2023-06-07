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

import static com.msedonald.result.data.WinOrLose.LOSE;
import static com.msedonald.result.data.WinOrLose.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    ResultRepository resultRepository;

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
                .token("this token is not using for actual auth, just for double-check")
                .score(1000L)
                .winOrLose(WIN)
                .build();

        mockMvc.perform(post("/api/scores")
                        .content(objectMapper.writeValueAsString(resultSave))
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", jwtProvider.generateAccessToken(user.getId(), user.getUsername())))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        List<Result> results = userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundAuthException::new)
                .getResults();

        assertThat(results).hasSize(1);
        Result result = results.get(0);
        assertThat(result.getScore()).isEqualTo(1000L);
        assertThat(result.getUser().getId()).isEqualTo(user.getId());

    }

    @Test
    @DisplayName("get current 5 games of each user")
    void getTop5Results() throws Exception {
        //given
        User user = User.builder()
                .username("tester")
                .password("pwd")
                .build();
        userRepository.save(user);

        //when
        for (int i = 0; i < 8; i++) {
            resultRepository.save(Result.builder()
                    .user(user)
                    .score(1000L + (i * 100))
                    .winOrLose(WIN)
                    .build()
            );
        }

        mockMvc.perform(get("/api/scores")
                        .header("Authorization", jwtProvider.generateAccessToken(user.getId(), user.getUsername()))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5))) // Check the size of the array
                .andExpect(jsonPath("$[0].score").value(1700))
                .andExpect(jsonPath("$[0].username").value(user.getUsername()))
                .andDo(print());
    }

    @Test
    @DisplayName("get current games of each user who only play twice")
    void getResults() throws Exception {
        //given
        User user = User.builder()
                .username("tester")
                .password("pwd")
                .build();
        userRepository.save(user);

        resultRepository.save(Result.builder()
                .user(user)
                .score(200L)
                .winOrLose(LOSE)
                .build());

        resultRepository.save(Result.builder()
                .user(user)
                .score(700L)
                .winOrLose(WIN)
                .build()
        );

        //expected
        mockMvc.perform(get("/api/scores")
                        .header("Authorization", jwtProvider.generateAccessToken(user.getId(), user.getUsername()))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))) // Check the size of the array
                .andExpect(jsonPath("$[1].score").value(200))
                .andExpect(jsonPath("$[1].username").value(user.getUsername()))
                .andExpect(jsonPath("$[1].winOrLose").value(LOSE.toString().toLowerCase()))
                .andDo(print());
    }


}