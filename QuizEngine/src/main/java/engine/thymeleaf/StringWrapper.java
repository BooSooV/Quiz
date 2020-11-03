package engine.thymeleaf;

public class StringWrapper {
    private String str = new String();

    public StringWrapper(String str) {
        this.str = str;
    }

    public StringWrapper() {
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "StringWrapper{" +
                "str='" + str + '\'' +
                '}';
    }
}
