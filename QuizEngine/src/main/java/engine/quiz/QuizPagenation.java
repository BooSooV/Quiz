package engine.quiz;

//import engine.quiz.QuizToUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class QuizPagenation {
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    private Sort sort;
    private int number;
    private int numberOfElements;
    private int size;
    private boolean empty;
    private Pageable pageable;
    private List<Quiz> content = new ArrayList<>();

    public QuizPagenation(Page<Quiz> page) {
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

        List<Quiz> allQuizzesFromBase = page.getContent();
        for (Quiz quiz : allQuizzesFromBase) {
            ArrayList<String> optionsForUser = new ArrayList();
            for (var quizOptions : quiz.getOptionOfQuizzes()) {
                optionsForUser.add(quizOptions.getOptionOfQuiz());
            }
            this.content.add(quiz);
        }
    }

    public QuizPagenation() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<Quiz> getContent() {
        return content;
    }

    public void setContent(List<Quiz> content) {
        this.content = content;
    }
}
