package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizEngine {
    public static void main(String[] args) {
        System.out.println("p0");
        SpringApplication.run(QuizEngine.class, args);
        //test roll back
        //
        //branch for optimize classes

    }
}