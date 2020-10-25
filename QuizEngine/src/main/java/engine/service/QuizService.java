package engine.service;

import engine.quiz.Quiz;
//import engine.quiz.QuizToUser;
import engine.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Page<Quiz> getAllQuizzesPaging(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Quiz> pagedResult = quizRepository.findAll(paging);
        return pagedResult;
    }

    public void SaveOrUpdateQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> GetAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        quizRepository.findAll().forEach(quiz -> quizzes.add(quiz));
        return  quizzes;
    }

    public Quiz getQuizById(int id) {
        Quiz quiz = quizRepository.findById(id).get();
        return quiz;
    }

    public ArrayList getSortAnswerById(int id) {
        ArrayList answerFromRepository = new ArrayList<>();

        for (var quizAnswers : quizRepository.findById(id).get().answers) {
            answerFromRepository.add(quizAnswers.answer);
        }
        Collections.sort(answerFromRepository);
        return answerFromRepository;
    }

    public String getCreatorById(int id) {
        return quizRepository.findById(id).get().creator;
    }

    public void deleteQuizById(int id) {
        quizRepository.deleteById(id);
    }


}
