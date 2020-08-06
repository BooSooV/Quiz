package engine;

import java.util.ArrayList;
import java.util.List;

public class CompletedQuizForUser {
    public int id;
    public String completedAt;

    public CompletedQuizForUser(int id, String completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public CompletedQuizForUser() {
    }
}
