package engine.controller;

import engine.User;
import engine.compleatedQuiz.CompletedQuizPagination;
import engine.config.SpringSecurityConfig;
import engine.quiz.Answer;
import engine.quiz.Option;
import engine.quiz.QuizPagenation;
import engine.service.QuizService;
import engine.service.UserService;
import engine.thymeleaf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import engine.quiz.Quiz;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class ThymeleafController {

    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    @Autowired
    SpringSecurityConfig springSecurityConfig;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;



    //Create Quiz
    @GetMapping("/GUI/addQuiz")
    public String getFormForCreatingQuiz(Model model) {
        Quiz quiz = new Quiz();
        ArrayListBooleanWrapper arrayListBooleanWrapper = new ArrayListBooleanWrapper();
        for (int i = 0; i <= 3; i++) {
            quiz.options.add(new Option());
            arrayListBooleanWrapper.addBoolean(new BooleanWrapper());
        }

        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("quiz", quiz);
        model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
        return "Quiz/addQuiz";
    }

    //Add quiz to base
    @PostMapping("/GUI/addQuiz")
    public String addQuizToBase(@ModelAttribute Quiz quiz, ArrayListBooleanWrapper arrayListBooleanWrapper, Model model) {
        System.out.println(quiz);
        System.out.println(arrayListBooleanWrapper);
        for (int i = 0; i <= 3; i++) {
            if(arrayListBooleanWrapper.getBoolList().get(i).isBool()) {
                quiz.answers.add(new Answer(i));
            }
        }
        if(quiz.isCorrect()) {
            quiz.creator = SecurityContextHolder.getContext().getAuthentication().getName();
            quizService.SaveOrUpdateQuiz(quiz);
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/addedQuiz";
        }
        quiz.title += "_not_correct_";
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("quiz", quiz);
        model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
        return "Quiz/addQuiz";
    }

    //Get all Quizzes pagination
    @GetMapping("/GUI/quizzes")
    public String getAllQuizzesPaging(@RequestParam String page, Model model) {
        System.out.println(page);
        QuizIdAndTitleList quizIdAndTitleList = new QuizIdAndTitleList();
        QuizPagenation quizPagenation = new QuizPagenation(quizService.getAllQuizzesPaging(Integer.parseInt(page), 10, "id"));
        for (Quiz quiz : quizPagenation.content)
        {
            quizIdAndTitleList.addQuizTitleAndId(new QuizIdAndTitle(quiz.id, quiz.title));
        }
        System.out.println(quizIdAndTitleList);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("currentPage", Integer.parseInt(page));
        model.addAttribute("numberPages", quizPagenation.totalPages);
        model.addAttribute("quizIdAndTitleList", quizIdAndTitleList);
        return "Quiz/allQuizzesPagination";
    }

    //Get Quiz for solve
    @GetMapping(path = "/GUI/quizzesSolve")
    public String solveQuiz(@RequestParam String id, Model model){
        ArrayListBooleanWrapper arrayListBooleanWrapper = new ArrayListBooleanWrapper();
        for (int i = 0; i <= 3; i++) { arrayListBooleanWrapper.addBoolean(new BooleanWrapper());}

        try {
            Quiz quiz = quizService.getQuizById(Integer.parseInt(id));
            System.out.println(quiz);
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuiz";
        }
        catch (Exception excep) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep); }
    }

    //Check answer on quiz
    @PostMapping("/GUI/checkAnswer")
    public String checkAnswer(@ModelAttribute Quiz quiz, ArrayListBooleanWrapper arrayListBooleanWrapper, Model model) {
        ArrayList<Integer> answerFromRepository;
        try {
            answerFromRepository = quizService.getSortAnswerById(quiz.id);
        }
        catch (Exception excep) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep);
        }

        List<Integer> answerFromUser = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            if(arrayListBooleanWrapper.getBoolList().get(i).isBool()) {
                answerFromUser.add(i);
            }
        }

        if (answerFromUser.equals(answerFromRepository)) {
            userService.addCompleteQuiz(quiz.id, SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuizCorrect";
        } else {
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuizNotCorrect";
        }
    }

    //Get home page
    @GetMapping(path = "/")
    public String getHomePage(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "Quiz/home";
    }

    //Get all solved Quizzes pagination
    @GetMapping("/GUI/AllSolvedQuizzes")
    public String getAllSolvedQuizzes(@RequestParam String page, Model model) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!user.equals("anonymousUser")){
            CompletedQuizPagination completedQuizzes = new CompletedQuizPagination(userService.getUsersSolvedQuizzesPaging(user, Integer.parseInt(page), 10, "completed.completedAt"));
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("currentPage", Integer.parseInt(page));
            model.addAttribute("completedQuizzes", completedQuizzes);
            System.out.println(completedQuizzes);
            return "Quiz/allSolvedQuizzes";
        } else {
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("currentPage", Integer.parseInt(page));
            return "Quiz/registerFirst";
        }
    }

    //Get delete quiz page
    @GetMapping("/GUI/deleteQuizPage")
    public String getDeleteQuizPage(Model model) {
        StringWrapper numberQuiz = new StringWrapper("");
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("numberQuiz", numberQuiz);
        model.addAttribute("mesToUser", new String(""));
        return "Quiz/deleteQuiz";
    }

    //Post delete quiz result
    @PostMapping("/GUI/deleteQuizResult")
    public String postDeleteQuizResult(@ModelAttribute StringWrapper numberQuiz, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String creatorAuth = auth.getName();
        Integer id = Integer.parseInt(numberQuiz.getStr());

        try {
            quizService.getQuizById(id);
        }
        catch (Exception exep) {
            model.addAttribute("numberQuiz", numberQuiz);
            model.addAttribute("mesToUser", new String("Quiz not exist"));
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            return "Quiz/deleteQuiz";
        }

        String creatorBase = quizService.getCreatorById(id);

        if((creatorBase).equals(creatorAuth)) {
            quizService.deleteQuizById(id);
            model.addAttribute("numberQuiz", numberQuiz);
            model.addAttribute("mesToUser", new String("Quiz deleted"));
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            return "Quiz/deleteQuiz";
        } else {
            model.addAttribute("numberQuiz", numberQuiz);
            model.addAttribute("mesToUser", new String("Its not your Quiz"));
            model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
            return "Quiz/deleteQuiz";
        }
    }

    //Get registration page
    @GetMapping("/GUI/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User("",""));
        model.addAttribute("mesToUser", new String(""));
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "Quiz/registration";
    }

    //Post registration result
    @PostMapping("/GUI/registrationResult")
    public String postRegistrationResult(@ModelAttribute User user, Model model) throws Exception  {
        System.out.println(user);

        if(Pattern.matches(".*@.*\\..*", user.email) && user.password.length() > 4) {
            if(userService.getUserByEmail(user.email).email == "null") {
                userService.SaveUser(user);
                springSecurityConfig.configure(authenticationManagerBuilder);
                model.addAttribute("mesToUser", new String("Registration successfully"));
                model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
                return "Quiz/registration";
            } else {
                model.addAttribute("mesToUser", new String("This user already registered"));
                model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
                return "Quiz/registration";
            }
        } else {
            model.addAttribute("mesToUser", new String("Not correct login or password"));
            model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
            return "Quiz/registration";
        }
    }
}