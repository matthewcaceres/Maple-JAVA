import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;


public class CustomBranch{
    public static void displayAddBranch(ConnectorUtil conn){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        Label label = new Label("Branch Name: ");
        TextField txt1 = new TextField();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label,txt1);
        VBox layout = new VBox (10);
        layout.setPadding(new Insets(20,10,20,10));

        Text preset = new Text("Presets:");
        CheckBox notes = new CheckBox("Notes");
        CheckBox list = new CheckBox("List");
        CheckBox sumList = new CheckBox("Adder list");
        Button submit = new Button("Submit");
        submit.setOnAction(e->{
            if (notes.isSelected()&& !list.isSelected() && !sumList.isSelected()){
                try {
                    conn.createBranch(txt1.getText(),"Notes");
                    Button temp = new Button(txt1.getText());

                    temp.setPrefSize(250,75);
                    temp.setOnAction(x -> {
                        try {
                            Main.getDb().showData(temp.getText());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    });
                    Main.getContainer().getChildren().add(temp);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
            else if (!notes.isSelected()&& list.isSelected() && !sumList.isSelected()) {


            }
            else if (!notes.isSelected()&& !list.isSelected() && sumList.isSelected()){
                try {
                    conn.createBranch(txt1.getText(),"Adder list");
                    Button temp = new Button (txt1.getText());
                    temp.setPrefSize(250,75);
                    temp.setOnAction(x->{
                        try{
                            Main.getDb().showData(temp.getText());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    });
                    Main.getContainer().getChildren().add(temp);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } window.close();
        });
        layout.getChildren().addAll(hbox,preset,notes,list,sumList,submit);


        Scene scene = new Scene(layout, 150,200);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

}
