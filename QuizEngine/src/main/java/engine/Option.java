package engine;//package engine;

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
}
