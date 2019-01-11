import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
    public static void createAlert(String message){
        createAlert(message,150,150);
    }

    public static void createAlert(String message,int width, int height){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        StackPane layout = new StackPane();

        Text text1 = new Text();
        text1.setText(message);
        text1.setWrappingWidth(width-10);
        text1.setTextAlignment(TextAlignment.CENTER);


        Button ok = new Button("Ok");
        ok.setOnAction(e->window.close());
        layout.setCenterShape(true);
        layout.getChildren().addAll(text1,ok);
        layout.setAlignment(text1, Pos.TOP_CENTER);
        layout.setAlignment(ok,Pos.BOTTOM_CENTER);
        layout.setPadding(new Insets(40,10,30,10));

        Scene scene = new Scene(layout,width,height);
        window.setScene(scene);
        window.showAndWait();


    }
}
