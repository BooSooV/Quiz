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
    public List<Option> options = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn()
    public List<Answer> answers = new ArrayList<>();

    public Quiz(String title, String text, List options, List answers) {
        this.title = title;
        this.text = text;
        this.creator = null;
        this.options = options;
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
                if (this.options.size() >= 2) {
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
                "options: " + options + "\n"+
                "answers: " + answers);
    }
}
