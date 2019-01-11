
import java.util.HashMap;

public class Tree {
    private HashMap<String,Branch> branches = new HashMap<>();
    public void addBranch(Branch temp){
        branches.put(temp.getName(),temp);
    }
    public void removeBranch(Branch temp) {
        branches.remove(temp.getName());
    }
}
