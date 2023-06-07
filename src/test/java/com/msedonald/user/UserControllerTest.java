package com.msedonald.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msedonald.exception.UserNotFoundAuthException;
import com.msedonald.user.data.UserLogin;
import com.msedonald.user.data.UserSave;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("user sign up")
    void signup() throws Exception {

        UserSave userSave = UserSave.builder()
                .username("tester")
                .password("password")
                .build();

        String requestJson = objectMapper.writeValueAsString(userSave);

        mockMvc.perform(post("/api/users")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());

        User user = userRepository.findByUsernameAndPassword(userSave.username(), userSave.password())
                .orElseThrow(UserNotFoundAuthException::new);
        assertThat(user.getUsername()).isEqualTo(userSave.username());
    }

    @Test
    @DisplayName("user login")
    void login() throws Exception {
        //when
        userRepository.save(User.builder()
                .username("tester")
                .password("password")
                .build());

        UserLogin userLogin = UserLogin.builder()
                .username("tester")
                .password("password")
                .build();

        //expected
        mockMvc.perform(post("/api/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLogin)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}