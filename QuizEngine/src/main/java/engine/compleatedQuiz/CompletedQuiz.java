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
    private long id;

    @Column
    private int quizId;
    @Column
    private String completedAt;

    public CompletedQuiz(int quizId) {
        LocalDateTime now = LocalDateTime.now();
        this.quizId = quizId;
        this.completedAt = (now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()
                + "T" + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "." + now.getNano()).toString();
    }
    public CompletedQuiz() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "\n" + "CompletedQuiz{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", completedAt='" + completedAt + '\'' +
                '}';
    }
}
