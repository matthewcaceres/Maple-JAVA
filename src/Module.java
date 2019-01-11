import java.net.URL;

public class Module {
    private String data = "";
    public boolean isURL = false;
    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Module(int x){
        data=x + "";
    }
    public Module(String x){
        if (isValid(x)) {
            data = x;
            isURL = true;
        }
        else
            data = x;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
