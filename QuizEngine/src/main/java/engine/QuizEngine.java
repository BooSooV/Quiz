package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class QuizEngine {
    public static void main(String[] args) {
        SpringApplication.run(QuizEngine.class, args);
    }
}

/*
{
  "email": "test@gmail.com",
  "password": "secret"
}
 */