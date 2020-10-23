package engine.quiz;//package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OptionOfQuiz {



    @Id
    @GeneratedValue
    public long optionId;

    @Column
    public String optionOfQuiz;



    public OptionOfQuiz(String optionOfQuiz) {
        this.optionOfQuiz = optionOfQuiz;
    }
    public OptionOfQuiz() {
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionOfQuiz(String option) {
        this.optionOfQuiz = option;
    }

    public String getOptionOfQuiz() {
        return optionOfQuiz;
    }

    @Override
    public String toString() {
        return String.format(optionOfQuiz);
    }
}
