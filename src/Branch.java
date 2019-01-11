import java.util.HashMap;

public class Branch {
    private String name;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private HashMap<String,Seed> seeds = new HashMap<>();
    public void addSeed(Seed temp){
        seeds.put(temp.getName(),temp);
    }
    public void removeSeed(Seed temp) {
        seeds.remove(temp.getName(),temp);
    }
}
