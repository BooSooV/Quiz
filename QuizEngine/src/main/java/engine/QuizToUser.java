package engine;

import java.util.ArrayList;
import java.util.List;

public class QuizToUser {

    public int id;
    public String title;
    public String text;
    public List options = new ArrayList();

    public QuizToUser(int id, String title, String text, List options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public QuizToUser() {

    }
}
