package engine.thymeleaf;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBooleanWrapper {
    private List<BooleanWrapper> boolList = new ArrayList<>();

    public ArrayListBooleanWrapper() {    }
    public ArrayListBooleanWrapper(List<BooleanWrapper> greetingsList) {
        this.boolList = greetingsList;
    }

    public List<BooleanWrapper> getBoolList() {
        return boolList;
    }

    public void setBoolList(List<BooleanWrapper> greetingsList) {
        this.boolList = greetingsList;
    }

    public void addBoolean(BooleanWrapper bool) {
        this.boolList.add(bool);
    }

    @Override
    public String toString() {
        return String.format("boolList: " + boolList + "\n");
    }
}
