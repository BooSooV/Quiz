package engine.compleatedQuiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CompletedQuiz {

    @Id
    @GeneratedValue
    public long id;

    @Column
    public int quizId;
    @Column
    public String completedAt;

    public CompletedQuiz(int quizId) {
        LocalDateTime now = LocalDateTime.now();
        this.quizId = quizId;
        //this.completedAt = completedAt;
        this.completedAt = (now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()
                + "T" + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "." + now.getNano()).toString();
    }
    public CompletedQuiz() {
    }
}
