package engine.answer;

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
    public Integer answer;

    public Answer(Integer answer) {
        this.answer = answer;
    }

    public Answer() {
    }

    @Override
    public String toString() {
        return String.format(answer.toString());
    }
}

