package engine.controller;

import engine.quiz.Option;
import engine.thymeleaf.GreetingsList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import engine.thymeleaf.Greeting;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import engine.quiz.Quiz;;
@Controller
public class ThymeleafController {





    @GetMapping("/all")
    public String showAll(Model model) {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(new Greeting(1,"hello"));
        greetings.add(new Greeting(2,"hi"));
        greetings.add(new Greeting(3,"how are you"));
        model.addAttribute("greetings", greetings);
        return "testArrayList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        GreetingsList greetingsList = new GreetingsList();

        for (int i = 1; i <= 3; i++) {
            greetingsList.addGreeting(new Greeting());
        }

        model.addAttribute("form", greetingsList);
        return "createGreetingForm";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute GreetingsList form, Model model) {
        System.out.println(form);
        return "redirect:/all";
    }






    @GetMapping("/GUI/addQuiz")
    public String homePage(Model model) {
        Quiz quiz = new Quiz();
        quiz.options.add(new Option("opt1"));
        quiz.options.add(new Option("opt2"));
        quiz.options.add(new Option("opt3"));

        model.addAttribute("quiz", quiz);
        return "addQuiz";
    }

    @PostMapping("/GUI/addQuiz")
    public String testPostMethod(@ModelAttribute Quiz quiz, Model model) {
        System.out.println(quiz);
        return "homePage";
    }
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
}


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