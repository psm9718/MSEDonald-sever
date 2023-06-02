package com.msedonald.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ResultControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    @DisplayName("result save")
//    void save() throws Exception {
//
//        //given
//        User user = User.builder().build();
//
//        ResultSave resultSave = ResultSave.builder()
//                .token(user.get)
//                .score(1000L)
//                .build();
//
//        mockMvc.perform(post("/api/scores")
//                        .content(objectMapper.writeValueAsString(resultSave))
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }


}