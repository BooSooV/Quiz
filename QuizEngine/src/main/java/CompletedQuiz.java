package engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompletedQuiz {

    @Id
    @GeneratedValue
    public long quizId;

    @Column
    public int id;
    @Column
    public String completedAt;

    public CompletedQuiz(int id, String completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }
    public CompletedQuiz() {
    }
}
