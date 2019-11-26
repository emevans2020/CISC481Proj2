import java.util.*;

public class goalWithBindings {
    ArrayList<ArrayList<String>> currentGoal;
    HashMap<String, String> currentBinding;

    public goalWithBindings(ArrayList<ArrayList<String>> goal, HashMap<String, String> binding) {
        currentGoal = goal;
        currentBinding = binding;
    }

    @Override
    public String toString(){
        return "Goal: " + currentGoal.toString() + "\nBinding: " + currentBinding.toString();
    }
}