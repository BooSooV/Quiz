package engine.compleatedQuiz;

import engine.compleatedQuiz.CompletedQuiz;
//import engine.quiz.CompletedQuizForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class CompletedQuizPagenation {
    public int totalPages;
    public long totalElements;
    public boolean last;
    public boolean first;
    public Sort sort;
    public int number;
    public int numberOfElements;
    public int size;
    public boolean empty;
    public Pageable pageable;
    public List<CompletedQuiz> content = new ArrayList<>();

    public CompletedQuizPagenation(Page<CompletedQuiz> page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.first = page.isFirst();
        this.sort = page.getSort();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.size = page.getSize();
        this.empty = page.isEmpty();
        this.pageable = page.getPageable();

        List<CompletedQuiz> allCompletedQuiz = page.getContent();
        for (CompletedQuiz quizCompletedQuiz : allCompletedQuiz) {
            this.content.add(quizCompletedQuiz);
        }
    }

    public CompletedQuizPagenation() {
    }




}