import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tools {
    protected static FlowPane updateBranch(FlowPane container) throws SQLException {
        ArrayList<String> names = Main.getDb().showBranches();
        for (String table : names) {
            Button temp = new Button(table);
            temp.setPrefSize(250,75);
            temp.setOnAction(e -> {
                try {
                    Main.getDb().showData(temp.getText());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            container.getChildren().add(temp);
        }
        return container;
    }
    protected static void addSeed(String branchName) throws SQLException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(850);
        window.setMaxHeight(100);
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(20);
        grid.add(new Label("Seed Name:"), 0, 0);
        TextField txt1 = new TextField();
        grid.add(txt1, 0, 1);
        grid.add(new Label("Image link:"), 1, 0);
        TextField txt2 = new TextField();
        grid.add(txt2, 1, 1);
        grid.add(new Label("Description:"), 2, 0);
        TextField txt3 = new TextField();
        grid.add(txt3, 2, 1);
        grid.add(new Label("Release Date:"), 3, 0);
        TextField txt4 = new TextField();
        grid.add(txt4, 3, 1);
        grid.add(new Label("Price:"), 4, 0);
        TextField txt5 = new TextField();
        grid.add(txt5, 4, 1);
        grid.add(new Label("Bought:"), 5, 0);
        CheckBox ck = new CheckBox();
        grid.add(ck, 5, 1);
        Button submit = new Button();
        grid.add(submit, 6, 0);
        submit.setOnAction(e -> {
            Statement stmt = null;
            SumItem temp = new SumItem(txt2.getText(), txt1.getText(), txt3.getText(), txt4.getText(), ck.isSelected(), Integer.parseInt(txt5.getText()));
            String query = "INSERT INTO " + branchName + " (imgid,itemname,description,releasedate,bought,price) VALUES ('"+temp.getImgid()+"','" + temp.getItemname() + "', '"
                    + temp.getDescription() + "', '" + temp.getReleasedate() + "', " + temp.getBought() + ", " + temp.getPrice() +");";
            try {
                stmt = Main.getConn().createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ((TableView)Main.getSp2().getContent()).getItems().add(temp);
            window.close();
        });
        grid.setPadding(new Insets(0, 10, 0, 10));
        Scene scene = new Scene(grid,850,100);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


    }
}
