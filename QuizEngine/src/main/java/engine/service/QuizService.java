package engine.service;

import engine.quiz.QuizForStore;
import engine.quiz.QuizToUser;
import engine.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Page<QuizForStore> getAllQuizzesPaging(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<QuizForStore> pagedResult = quizRepository.findAll(paging);
        return pagedResult;
    }

    public int SaveOrUpdateQuiz(QuizForStore quiz) {
        QuizForStore quizForStoreFeedback;
        quizForStoreFeedback = quizRepository.save(quiz);
        return quizForStoreFeedback.id;
    }

    public List<QuizForStore> GetAllQuizzes() {
        List<QuizForStore> quizzes = new ArrayList<QuizForStore>();
        quizRepository.findAll().forEach(quiz -> quizzes.add(quiz));
        return  quizzes;
    }

    public QuizToUser getQuizById(int id) {
        QuizToUser quizToUser = new QuizToUser();
        quizToUser.id = quizRepository.findById(id).get().id;
        quizToUser.text = quizRepository.findById(id).get().text;
        quizToUser.title = quizRepository.findById(id).get().title;
        for (var quizOptions : quizRepository.findById(id).get().options) {
            quizToUser.options.add(quizOptions.option);
        }

        return quizToUser;
    }

    public ArrayList getAnswerById(int id) {
        ArrayList answerFromRepository = new ArrayList<>();

        for (var quizAnswers : quizRepository.findById(id).get().answers) {
            answerFromRepository.add(quizAnswers.answer);
        }

        return answerFromRepository;
    }

    public String getCreatorById(int id) {
        return quizRepository.findById(id).get().creator;
    }

    public void deleteQuizById(int id) {
        quizRepository.deleteById(id);
    }


}
