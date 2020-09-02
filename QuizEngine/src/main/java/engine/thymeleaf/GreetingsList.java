package engine.thymeleaf;
import engine.thymeleaf.Greeting;

import java.util.ArrayList;
import java.util.List;

public class GreetingsList {

    private List<Greeting> greetingsList = new ArrayList<>();

    public GreetingsList(List<Greeting> greetingsList) {
        this.greetingsList = greetingsList;
    }
    public GreetingsList() {

    }

    public void addGreeting(Greeting greeting) {
        this.greetingsList.add(greeting);
    }

    public void setGreetingsList(List<Greeting> greetingsList) {
        this.greetingsList = greetingsList;
    }

    public List<Greeting> getGreetingsList() {
        return greetingsList;
    }

    @Override
    public String toString() {
        return String.format("greetingsList: " + greetingsList + "\n");
    }

}
