package engine.controller;

import engine.*;
import engine.answer.AnsToUser;
import engine.config.SpringSecurityConfig;
import engine.compleatedQuiz.CompletedQuizPagination;
import engine.quiz.QuizPagenation;
import engine.quiz.*;
import engine.service.QuizService;
import engine.service.UserService;
//import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

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
            quiz.setCreator(SecurityContextHolder.getContext().getAuthentication().getName());
            quizService.SaveOrUpdateQuiz(quiz);
            return quiz;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    //Get Quiz by id
    @GetMapping(path = "/api/quizzes/{idS}")
    public Quiz getQuiz(@PathVariable String idS){

        try {
            //Integer id = Integer.parseInt(idS);
            Quiz quiz = quizService.getQuizById(Integer.parseInt(idS));
            quiz.setAnswers(null);
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




    //Solve a Quiz
    @PostMapping(path = "/api/quizzes/{id}/solve")
    public AnsToUser solveQuiz(@PathVariable Integer id, @RequestBody Hashtable answerFromUser,@Context final HttpServletResponse response){
        ArrayList<Integer> answerFromRepository;

        try {
            answerFromRepository = quizService.getSortAnswerById(id);
        }
        catch (Exception excep) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", excep); }
        Collections.sort((List)answerFromUser.get("answer"));

        if (answerFromUser.get("answer").equals(answerFromRepository)) {
            userService.addCompleteQuiz(id, SecurityContextHolder.getContext().getAuthentication().getName());
            response.setStatus(HttpServletResponse.SC_OK);
            return new AnsToUser(true);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return new AnsToUser(false);
        }
    }


    //Delete Quiz by id
    @DeleteMapping(path = "/api/quizzes/{idS}")
    public ResponseStatusException deleteQuiz(@PathVariable String idS)  throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String creatorAuth = auth.getName();
        Integer id = Integer.parseInt(idS);
// its should be in service
        try {
            quizService.getQuizById(id);
        }
        catch (Exception exep) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", exep);
        }

        String creatorBase = quizService.getCreatorById(id);

        if((creatorBase).equals(creatorAuth)) {
            quizService.deleteQuizById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        //__________________________
    }


    @GetMapping(path = "/api/quizzes/completed")
    public CompletedQuizPagination getAllCompletedQuiz(@RequestParam int page){
        String creator = SecurityContextHolder.getContext().getAuthentication().getName();
        return new CompletedQuizPagination(userService.getUsersSolvedQuizzesPaging(creator, page, 10, "completed.completedAt"));
    }
}
