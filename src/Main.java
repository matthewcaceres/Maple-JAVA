import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main extends Application {
    private static ConnectorUtil db = new ConnectorUtil();
    private static Connection conn = null;
    private static String current = "";
    private static FlowPane container = null;
    private static ScrollPane sp2 = new ScrollPane();

    public static ScrollPane getSp2() {
        return sp2;
    }

    public static void setCurrent(String current) {
        Main.current = current;
    }

    public static String getCurrent() {
        return current;
    }

    public static FlowPane getContainer() {
        return container;
    }

    public static ConnectorUtil getDb() {
        return db;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        boolean contQuery = true;
        boolean validCommand = false;
        try {
            conn = db.getConnection();
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println("Cannot connect");
        }

        launch(args);
    }
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Maple");
        Button button1 = new Button("Branch");
        button1.setOnAction(e->CustomBranch.displayAddBranch(getDb()));
        Button button2 = new Button("Add seed");
            button2.setOnAction(e2 -> {
                try {if(!current.equals(""))
                    Tools.addSeed( current);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        HBox hb = new HBox();
        hb.getChildren().addAll(button1,button2);
        VBox vb = new VBox(hb);
        SplitPane sp = new SplitPane();
        vb.getChildren().add(sp);
        ScrollPane sp1 = new ScrollPane();
        sp1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        container = new FlowPane();
        sp1.setContent(Tools.updateBranch(container));
        sp2 = new ScrollPane();
        sp2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.getItems().addAll(sp1, sp2);
        sp.setDividerPositions(0.25);
        Scene scene = new Scene(vb,1000,700);
        sp1.maxWidthProperty().bind(sp.widthProperty().multiply(0.25));
        sp2.maxWidthProperty().bind(sp.widthProperty().multiply(0.74));
        sp2.setFitToWidth(true);
        sp2.setFitToHeight(true);
        sp.prefHeightProperty().bind(scene.heightProperty());
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }
}