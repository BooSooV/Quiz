package engine.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizFromUser {

    public String title;
    public String text;
    public List options = new ArrayList();
    public List answer = new ArrayList<>();

    public QuizFromUser(String title, String text, ArrayList options, ArrayList answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    public QuizFromUser() {

    }
}
