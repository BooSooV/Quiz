package engine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;



@RestController
public class TaskController {
    private AnsToUser ansToUser = new AnsToUser();
    public ArrayList<Quiz> allQuizzes = new ArrayList();
    public  UserService userServ = new UserService();

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
    public QuizToUser addQuiz(@RequestBody QuizFromUser quizFromUser){
        System.out.println("try Add quiz to site");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        if(quizFromUser.title != null && quizFromUser.text != null) {
            if(!quizFromUser.title.equals("") && !quizFromUser.text.equals("")) {
                if (quizFromUser.options.size() >= 2) {
                    //allQuizzes.add(quizFromUser);
                    int idBase = quizService.SaveOrUpdateQuiz(new QuizForStore(quizFromUser,auth.getName()));
                    QuizToUser addedQuiz = new QuizToUser(idBase, quizFromUser.title, quizFromUser.text, quizFromUser.options);
                    //quizService.SaveOrUpdateQuiz(quizFromUser);
                    System.out.println("quiz added");
                    return addedQuiz;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    //Get Quiz by id
    @GetMapping(path = "/api/quizzes/{idS}")
    public QuizToUser getQuiz(@PathVariable String idS){
        System.out.println("Get Quiz by id");
        try {
            Integer id = Integer.parseInt(idS);
            //QuizToUser quizToUser = new QuizToUser(id+1, allQuizzes.get(id).title, allQuizzes.get(id).text, allQuizzes.get(id).options);
            return quizService.getQuizById(id);
        }
        catch (Exception exep) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exep);
        }
    }
/*
    //Get all quizzes
    @GetMapping(path = "/api/quizzes")
    public ArrayList<QuizToUser> getAllQuizzes(){
        System.out.println("Get all quizzes");
        ArrayList<QuizToUser> allQuizzesToUser = new ArrayList();
        //int id = 1;
        List<QuizForStore> allQuizzesFromBase = quizService.GetAllQuizzes();

        //System.out.println(allQuizzesFromBase);

        for (QuizForStore quiz : allQuizzesFromBase) {
            //System.out.println(quiz.id);
            ArrayList<String> optionsForUser = new ArrayList();
            for (var quizOptions : quiz.options) {
                optionsForUser.add(quizOptions.option);
            }
            allQuizzesToUser.add(new QuizToUser(quiz.id, quiz.title, quiz.text, optionsForUser));
        }
        return allQuizzesToUser;
    }
    //Get all quizzes
*/




    @GetMapping(path = "/api/quizzes")
    public QuizPagenation getAllQuizzesPaging(@RequestParam int page){
        QuizPagenation quizPagenation = new QuizPagenation(quizService.getAllQuizzesPaging(page, 10, "id"));
        return quizPagenation;
    }






    //Solving a Quiz
    @PostMapping(path = "/api/quizzes/{id}/solve")
    public AnsToUser addTask(@PathVariable Integer id, @RequestBody AnswerFromUser answer){
        System.out.println("Solving a Quiz");
        ArrayList<Integer> answerFromRepository = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        LocalDateTime now = LocalDateTime.now();

        try {
            answerFromRepository = quizService.getAnswerById(id);
            Collections.sort(answerFromRepository);
            Collections.sort(answer.answer);

            boolean equalsAnswers = answerFromRepository.toString().equals(answer.answer.toString());

            if (equalsAnswers) {
                ansToUser.success = true;
                ansToUser.feedback = "Congratulations, you're right!";
                String userEmail = auth.getName();
                System.out.println(userEmail);
                user = userService.getUserByEmail(userEmail);
                user.completed.add(new CompletedQuiz(id,(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + "T" + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "." + now.getNano()).toString()));
                userService.SaveUser(user);
                return ansToUser;
            } else {
                ansToUser.success = false;
                ansToUser.feedback = "Wrong answer! Please, try again.";
                return ansToUser;
            }
        }
        catch (Exception exep) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exep);
        }

    }

    //Delete Quiz by id
    @DeleteMapping(path = "/api/quizzes/{idS}")
    public ResponseStatusException deleteQuiz(@PathVariable String idS)  throws Exception {
        System.out.println("Delete Quiz by id");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer id = Integer.parseInt(idS);

        try {
            quizService.getQuizById(id);
        }
        catch (Exception exep) {
            System.out.println("Delete Quiz by id catch");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz Not Found", exep);
        }

        String creatorBase = quizService.getCreatorById(id);
        String creatorAuth = auth.getName();
        System.out.println(creatorBase);
        System.out.println(creatorAuth);
        if((creatorBase).equals(creatorAuth)) {
            System.out.println("Delete Quiz by id 1");
            quizService.deleteQuizById(id);
            System.out.println("Delete Quiz by id 2");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(path = "/api/quizzes/completed")
    public CompletedQuizPagenation getAllCompletedQuiz(@RequestParam int page){
        System.out.println("point 1");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("point 2");
        String creatorAuth = auth.getName();
        System.out.println("point 3");

        CompletedQuizPagenation completedQuizPagenation = new CompletedQuizPagenation(userService.getUsersSolvedQuizzesPaging(creatorAuth, page, 10, "completed.completedAt"));
        return completedQuizPagenation;
    }

    /*
        @GetMapping(path = "/api/quizzes/completed")
    public Page<CompletedQuiz> getAllCompletedQuiz(@RequestParam int page){
        System.out.println("point 1");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("point 2");
        String creatorAuth = auth.getName();
        System.out.println("point 3");

        new CompletedQuizPagenation()
        return userService.getUsersSolvedQuizzesPaging(creatorAuth, page, 10, "completed.completedAt");
    }
     */

}
