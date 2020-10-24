package engine.quiz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue
    public int id;
    public String title;
    public String text;
    public String creator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<OptionOfQuiz> optionOfQuizzes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<Answer> answers = new ArrayList<>();

    public Quiz(String title, String text, List options, List answers) {
        this.title = title;
        this.text = text;
        this.creator = null;
        this.optionOfQuizzes = options;
        this.answers = answers;
    }

    public Quiz(String title, String text, String creator, List options, List answers) {
        this(title, text, options, answers);
        this.creator = creator;
    }

    public Quiz() {

    }

    public boolean isCorrect() {
        if(this.title != null && this.text != null) {
            if(!this.title.equals("") && !this.text.equals("")) {
                if (this.optionOfQuizzes.size() >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
                "id: " + id + "\n" +
                "title: " + title + "\n" +
                "text: " + text + "\n" +
                "creator: " + creator + "\n" +
                "options: " + optionOfQuizzes + "\n"+
                "answers: " + answers);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<OptionOfQuiz> getOptionOfQuizzes() {
        return optionOfQuizzes;
    }

    public void setOptionOfQuizzes(List<OptionOfQuiz> optionOfQuizs) {
        this.optionOfQuizzes = optionOfQuizs;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
