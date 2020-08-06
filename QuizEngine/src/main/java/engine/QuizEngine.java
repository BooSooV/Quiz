package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizEngine {
    public static void main(String[] args) {
        SpringApplication.run(QuizEngine.class, args);
        //test roll back
    }
}