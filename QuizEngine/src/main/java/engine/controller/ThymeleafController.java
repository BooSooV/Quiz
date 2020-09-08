package engine.controller;

import engine.answer.AnsToUser;
import engine.quiz.Answer;
import engine.quiz.Option;
import engine.quiz.QuizPagenation;
import engine.service.QuizService;
import engine.service.UserService;
import engine.thymeleaf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import engine.quiz.Quiz;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

@Controller
public class ThymeleafController {

    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    //Request Quiz
    @GetMapping("/GUI/addQuiz")
    public String getFormForCreatingQuiz(Model model) {
        Quiz quiz = new Quiz();
        ArrayListBooleanWrapper arrayListBooleanWrapper = new ArrayListBooleanWrapper();
        for (int i = 0; i <= 3; i++) {
            quiz.options.add(new Option());
            //quiz.answers.add(new Answer());
            arrayListBooleanWrapper.addBoolean(new BooleanWrapper());
        }


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
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/addedQuiz";
        }
        quiz.title += "_not_correct_";
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
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuiz";
        }
        catch (Exception excep) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep); }
    }

    //Check answer on quiz
    @PostMapping("/GUI/checkAnswer")
    public String checkAnswer(@ModelAttribute Quiz quiz, ArrayListBooleanWrapper arrayListBooleanWrapper, Model model) {
        System.out.println("Check answer on quiz");
        System.out.println(arrayListBooleanWrapper);
        System.out.println(quiz);


        ArrayList<Integer> answerFromRepository;
        try {
            answerFromRepository = quizService.getSortAnswerById(quiz.id);
            System.out.println("3___" + answerFromRepository);
        }
        catch (Exception excep) {
            System.out.println("Quiz Not Found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep);
        }

        List<Integer> answerFromUser = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            if(arrayListBooleanWrapper.getBoolList().get(i).isBool()) {
                answerFromUser.add(i);
            }
        }
        System.out.println(answerFromRepository);
        System.out.println(answerFromUser);

        if (answerFromUser.equals(answerFromRepository)) {
            userService.addCompleteQuiz(quiz.id, SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuizCorrect";
        } else {
            model.addAttribute("quiz", quiz);
            model.addAttribute("arrayListBooleanWrapper", arrayListBooleanWrapper);
            return "Quiz/solveQuizNotCorrect";
        }




//        return "Quiz/solveQuiz";
    }

}
        //        ArrayList<Integer> answerFromRepository;
//        try {
//            answerFromRepository = quizService.getSortAnswerById(id);
//        }
//        catch (Exception excep) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep); }
//        Collections.sort((List)answerFromUser.get("answer"));
//
//        if (answerFromUser.get("answer").equals(answerFromRepository)) {
//            userService.addCompleteQuiz(id, SecurityContextHolder.getContext().getAuthentication().getName());
//            return new AnsToUser(true);
//        } else {
//            return new AnsToUser(false);
//        }











    /*
    //Add quiz to site
    @PostMapping(path = "/api/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz){
        if(quiz.isCorrect()) {
            quiz.creator = SecurityContextHolder.getContext().getAuthentication().getName();
            quizService.SaveOrUpdateQuiz(quiz);
            return quiz;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
*/



//    @GetMapping("/all")
//    public String showAll(Model model) {
//        List<Greeting> greetings = new ArrayList<>();
//        greetings.add(new Greeting(1,"hello"));
//        greetings.add(new Greeting(2,"hi"));
//        greetings.add(new Greeting(3,"how are you"));
//        model.addAttribute("greetings", greetings);
//        return "testArrayList";
//    }
//
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        GreetingsList greetingsList = new GreetingsList();
//
//        for (int i = 1; i <= 3; i++) {
//            greetingsList.addGreeting(new Greeting());
//        }
//
//        model.addAttribute("form", greetingsList);
//        return "createGreetingForm";
//    }
//
//    @PostMapping("/save")
//    public String saveBooks(@ModelAttribute GreetingsList form, Model model) {
//        System.out.println(form);
//        return "redirect:/all";
//    }

/*


    @GetMapping("/")
    public String homePage(Model model) {
        Hashtable bufForTest = new Hashtable();;
        bufForTest.put("variable1", new String("variable1Value"));
        model.addAttribute("bufForTest", bufForTest);

        return "homePage";
    }

    @PostMapping("/")
    public String testPostMethod(@ModelAttribute Hashtable stringFromSite, Model model) {
        System.out.println(stringFromSite);
        return "homePage";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("variable1", "Value of variable1!");
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }
*/



/*<!DOCTYPE html>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Create Quiz</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <h1>Create new Quiz</h1>
    <form action="#" th:action="@{/GUI/addQuiz}" th:object="${quiz}" method="post">
        <p>title: <input type="text" th:field="*{title}" /></p>
        <p>text: <input type="text" th:field="*{text}" /></p>
        <p>
            <input type="submit" value="Submit" />
            <input type="reset" value="Reset" />
        </p>
    </form>
</body>
</html>

 */