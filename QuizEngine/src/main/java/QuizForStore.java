package engine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuizForStore {

    @Id
    @GeneratedValue
    public int id;
    @Column
    public String title;
    @Column
    public String text;
    @Column
    public String creator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<Option> options = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<Answer> answers = new ArrayList<>();

    public QuizForStore(String title, String text, List options, List answers) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.options = answers;
    }

    public QuizForStore(QuizFromUser quizFromUser, String creator) {
        this.title = quizFromUser.title;
        this.text = quizFromUser.text;
        this.creator = creator;

        for (var option : quizFromUser.options) {
            System.out.println(option);
            this.options.add(new Option(option.toString()));
        }
        for (var answer : quizFromUser.answer) {
            System.out.println(answer);
            this.answers.add(new Answer(answer.toString()));
        }
    }

    public QuizForStore() {

    }


}
