package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    public long answerId;

    @Column
    public String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer() {
    }
}

