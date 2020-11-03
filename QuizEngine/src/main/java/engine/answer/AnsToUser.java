package engine.answer;

public class AnsToUser {
    private boolean success;
    private String feedback;

    public AnsToUser(boolean correct) {
        success = correct;
        if(correct) {
            feedback = "Congratulations, you're right!";
        } else {
            feedback = "Wrong answer! Please, try again.";
        }
    }
    public AnsToUser() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

