package engine.controller;


import engine.*;
import engine.answer.AnsToUser;
import engine.config.SpringSecurityConfig;
import engine.compleatedQuiz.CompletedQuizPagenation;
import engine.quiz.QuizPagenation;
import engine.quiz.*;
import engine.compleatedQuiz.CompletedQuiz;
import engine.service.QuizService;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.regex.Pattern;



@RestController
public class TaskController {
    //private AnsToUser ansToUser = new AnsToUser();
    //public UserService userServ = new UserService();

    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    @Autowired
    SpringSecurityConfig springSecurityConfig;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;



    public TaskController() {
    }




    //Add user to base
    @PostMapping(path = "/api/register")
    public ResponseStatusException  RegisterNewUser(@RequestBody User user) throws Exception {

        if(Pattern.matches(".*@.*\\..*", user.email) && user.password.length() > 4) {
            if(userService.getUserByEmail(user.email).email == "null") {
                userService.SaveUser(user);
                springSecurityConfig.configure(authenticationManagerBuilder);

                throw new ResponseStatusException(HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //Add quiz to site
    @PostMapping(path = "/api/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz){
        if(quiz.isCorrect()) {
            quiz.creator = SecurityContextHolder.getContext().getAuthentication().getName();
            quizService.SaveOrUpdateQuiz(quiz);
            return quiz;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
/*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(quizFromUser.title != null && quizFromUser.text != null) {
            if(!quizFromUser.title.equals("") && !quizFromUser.text.equals("")) {
                if (quizFromUser.options.size() >= 2) {
                    int idBase = quizService.SaveOrUpdateQuiz(new QuizForStore(quizFromUser,auth.getName()));
                    QuizToUser addedQuiz = new QuizToUser(idBase, quizFromUser.title, quizFromUser.text, quizFromUser.options);
                    return addedQuiz;
                }
            }
        }

  "title": "Tea drinks",
  "text": "Select only tea drinks.",
  "options": ["black tea","green tea","Cappuccino","Sprite"],
  "answer": [0,1]

        */
    }

    //Get Quiz by id
    @GetMapping(path = "/api/quizzes/{idS}")
    public Quiz getQuiz(@PathVariable String idS){
        try {
            //Integer id = Integer.parseInt(idS);
            Quiz quiz = quizService.getQuizById(Integer.parseInt(idS));
            quiz.answers = null;
            return quiz;
        }
        catch (Exception excep) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", excep);
        }
    }




    @GetMapping(path = "/api/quizzes")
    public QuizPagenation getAllQuizzesPaging(@RequestParam int page){
        QuizPagenation quizPagenation = new QuizPagenation(quizService.getAllQuizzesPaging(page, 10, "id"));
        return quizPagenation;
    }






    //Solving a Quiz
    @PostMapping(path = "/api/quizzes/{id}/solve")
    public AnsToUser solveQuiz(@PathVariable Integer id, @RequestBody Hashtable answerFromUser){

        ArrayList<Integer> answerFromRepository;
        try {
            answerFromRepository = quizService.getSortAnswerById(id);
        }
        catch (Exception excep) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep); }

        Collections.sort((List)answerFromUser.get("answer"));

        if (answerFromUser.get("answer").equals(answerFromRepository)) {
            userService.addCompleteQuiz(id, SecurityContextHolder.getContext().getAuthentication().getName());
            return new AnsToUser(true);
        } else {
            return new AnsToUser(false);
        }
    }



    //Delete Quiz by id
    @DeleteMapping(path = "/api/quizzes/{idS}")
    public ResponseStatusException deleteQuiz(@PathVariable String idS)  throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer id = Integer.parseInt(idS);

        try {
            quizService.getQuizById(id);
        }
        catch (Exception exep) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", exep);
        }

        String creatorBase = quizService.getCreatorById(id);
        String creatorAuth = auth.getName();
        if((creatorBase).equals(creatorAuth)) {
            quizService.deleteQuizById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(path = "/api/quizzes/completed")
    public CompletedQuizPagenation getAllCompletedQuiz(@RequestParam int page){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String creatorAuth = auth.getName();
        CompletedQuizPagenation completedQuizPagenation = new CompletedQuizPagenation(userService.getUsersSolvedQuizzesPaging(creatorAuth, page, 10, "completed.completedAt"));
        return completedQuizPagenation;
    }
}
