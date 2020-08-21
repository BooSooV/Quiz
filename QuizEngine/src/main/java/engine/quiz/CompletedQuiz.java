package engine.quiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompletedQuiz {

    @Id
    @GeneratedValue
    public long id;

    @Column
    public int quizId;
    @Column
    public String completedAt;

    public CompletedQuiz(int quizId, String completedAt) {
        this.quizId = quizId;
        this.completedAt = completedAt;
    }
    public CompletedQuiz() {
    }
}
