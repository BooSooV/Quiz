package engine.thymeleaf;

public class QuizIdAndTitle {
    private int id;
    private String title;

    public QuizIdAndTitle(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public QuizIdAndTitle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format(id + " - " + title);
    }
}
