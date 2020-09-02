package engine.quiz;//package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {



    @Id
    @GeneratedValue
    public long optionId;

    @Column
    public String option;



    public Option(String option) {
        this.option = option;
    }
    public Option() {
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String toString() {
        return String.format(option);
    }
}
