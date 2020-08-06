package engine;



import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuizTest2 {
    @Test
    public void getTitle() {
        ArrayList<Option> options = new ArrayList();
        ArrayList<Answer> answer = new ArrayList<>();

        options.add(new Option("Arabica"));
        options.add(new Option("Sprite"));
        options.add(new Option("Espresso"));
        options.add(new Option("Tea"));

        answer.add(new Answer("Arabica"));
        answer.add(new Answer("Sprite"));

        Quiz quiz1 = new Quiz("Type of coffee", "Whot is coffee in this list", options, answer);

        String expected = quiz1.getTitle();
        Assert.assertEquals(expected, "Type of coffee");
    }
}
