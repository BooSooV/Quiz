package engine.answer;

public class AnsToUser {
    public boolean success;
    public String feedback;

    public AnsToUser(boolean correct) {
        success = correct;
        if(correct) {
            feedback = "Congratulations, you're right!";
        } else {
            feedback = "Wrong answer! Please, try again.";
        }
    }
    public AnsToUser() {}

}

