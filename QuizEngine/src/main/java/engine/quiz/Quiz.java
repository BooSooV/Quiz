package engine.quiz;

import engine.answer.Answer;
import engine.Option;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    public String title;
    public String text;
    public List<Option> options = new ArrayList();
    public List<Answer> answer = new ArrayList<>();

    public Quiz(String title, String text, ArrayList options, ArrayList answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    public String getTitle() {
        return this.title;
    }
    public Quiz() {

    }
}
