package engine;

import javax.persistence.*;

@Entity
public class AllQuizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String quiz;

    public AllQuizzes(Long id, String quiz) {
        this.quiz = quiz;
    }
    public AllQuizzes() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
