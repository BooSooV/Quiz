package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class QuizPagenation {
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
    public List<QuizToUser> content = new ArrayList<>();

    public QuizPagenation(Page<QuizForStore> page) {
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

        List<QuizForStore> allQuizzesFromBase = page.getContent();
        for (QuizForStore quiz : allQuizzesFromBase) {
            ArrayList<String> optionsForUser = new ArrayList();
            for (var quizOptions : quiz.options) {
                optionsForUser.add(quizOptions.option);
            }
            this.content.add(new QuizToUser(quiz.id, quiz.title, quiz.text, optionsForUser));
        }
    }

    public QuizPagenation() {
    }


}
