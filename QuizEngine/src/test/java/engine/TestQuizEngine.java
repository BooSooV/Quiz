package engine;

import engine.controller.TaskController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.springframework.web.context.WebApplicationContext;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WithUserDetails(value = "1")
//@WithMockUser(value="test@gmail.com", password = "secret", roles = "ROLE_ADMIN")
//@WithMockUser
@WithMockUser(value = "test@gmail.com", password = "secret",roles = "ADMIN")
public class TestQuizEngine {

    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //TaskController controller;

    @Test
    public void contexLoads() throws Exception {
        this.mockMvc.perform(get("/api/quizzes/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2")));
    }
}
