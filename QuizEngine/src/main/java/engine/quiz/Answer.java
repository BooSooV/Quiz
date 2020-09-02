package engine.quiz;

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

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format(answer.toString());
    }
}

