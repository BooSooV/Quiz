package engine.thymeleaf;

import java.util.ArrayList;
import java.util.List;

public class QuizIdAndTitleList {
    private List<QuizIdAndTitle> quizIdAndTitleList = new ArrayList<>();

    public QuizIdAndTitleList(List<QuizIdAndTitle> quizIdAndTitleList) {
        this.quizIdAndTitleList = quizIdAndTitleList;
    }
    public QuizIdAndTitleList() {    }

    public void addQuizTitleAndId(QuizIdAndTitle quizIdAndTitle) {
        this.quizIdAndTitleList.add(quizIdAndTitle);
    }

    public void setQuizIdAndTitleList(List<QuizIdAndTitle> quizIdAndTitleList) {
        this.quizIdAndTitleList = quizIdAndTitleList;
    }

    public List<QuizIdAndTitle> getQuizIdAndTitleList() {
        return quizIdAndTitleList;
    }

    @Override
    public String toString() {
        return String.format("quizIdAndTitleList: " + quizIdAndTitleList + "\n");
    }
}
