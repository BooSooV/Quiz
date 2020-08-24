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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = "test@gmail.com", password = "secret",roles = "ADMIN")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/clear-user-before.sql", "/quiz-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/create-user-before.sql", "/messages-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/messages-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TestQuizEngine {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addUser() throws Exception {
        User user = new User("test@gmail.com", "secret");
        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
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

        mockMvc.perform(post("/api/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quiz)))
                .andExpect(status().isOk());
    }

    @Test
    public void getQuizById() throws Exception {
        this.mockMvc.perform(get("/api/quizzes/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")));

    }
    @Test
    public void getAllQuizzesPaging() throws Exception {
        this.mockMvc.perform(get("/api/quizzes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")));

    }




}






















