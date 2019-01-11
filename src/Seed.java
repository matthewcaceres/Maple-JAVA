

import java.util.ArrayList;

public class Seed extends SizeException{
    private String name;
    private ArrayList<Module> description = new ArrayList<>(5);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void addModule(Module x){
        description.add(x);
    }

}
