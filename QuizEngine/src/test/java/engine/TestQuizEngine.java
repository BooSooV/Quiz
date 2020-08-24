package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.quiz.Quiz;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = "test@gmail.com", password = "secret",roles = "ADMIN")
public class TestQuizEngine {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void contexLoads() throws Exception {
        this.mockMvc.perform(get("/api/quizzes/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2")));
        /////////////////(jsonPath("$.message").value("Hello World!!!")
    }
    @Test
    public void sendQuiz() throws Exception {

        List<String> options = new ArrayList();
        options.add("black tea");
        options.add("green tea");
        options.add("Cappuccino");
        options.add("Sprite");
        List<Integer> answers = new ArrayList();
        answers.add(0);
        answers.add(1);
        Quiz quiz = new Quiz("Tea drinks","Select only tea drinks.",options, answers);

        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(quiz);
        mockMvc.perform(post("/api/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quiz)))
                .andExpect(status().isOk());
    }
}






















