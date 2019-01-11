
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;


public class ConnectorUtil {
    String serverName;
    int portNumber;
    String dbName;

    public ConnectorUtil() {
        this.serverName = "localhost";
        this.portNumber = 3306;
        this.dbName = "mapletree";
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName + ":" + this.portNumber
                + "/" + this.dbName + "?autoReconnect=true&useSSL=false", "root", "melons");
        return conn;
    }

    public void executeUpdate(Connection conn, String command) throws SQLException {
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command);
        } catch (SQLException e) {
            System.out.println("An error has occurred");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public ArrayList showBranches() throws SQLException {
        Connection conn = null;
        conn = this.getConnection();
        Statement stmt = null;
        String query = "SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema = 'mapletree';";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> names = new ArrayList<>();
        while(rs.next()){
            names.add(rs.getString("TABLE_NAME"));
        }
        return names;
    }
    public void createBranch(String name, String type) throws SQLException {
        String query = "";
        Statement stmt = getConnection().createStatement();
        if(type.equals("Notes")){
            query = "CREATE TABLE IF NOT EXISTS " + name + "(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," + " notes TEXT);";
            stmt.executeUpdate(query);
            String query2 = "INSERT INTO " + name + " (id, notes) VALUE(1,''  );";
            stmt.executeUpdate(query2);
        }
       // else if (type.equals("List")){

       // }
        else if(type.equals("Adder list")){
            query = "CREATE TABLE " + name + " (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                    "imgid TEXT," +
                    "itemname TEXT," +
                    "description TEXT," +
                    "releasedate VARCHAR(15)," +
                    "bought BOOL," +
                    "price INT);";
            stmt.executeUpdate(query);
        }
    }
    public void showData(String branchName) throws SQLException {
        String query = "SELECT * FROM " + branchName;
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
//        final TextArea temp = new TextArea();
//        temp.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            String query2 ="UPDATE " + branchName + " SET notes ='" + temp.getText() + "' WHERE id = 1;";
//            try {
//                Statement stmt2 = getConnection().createStatement();
//                stmt2.executeUpdate(query2);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
            if (rsmd.getColumnCount() == 2) {
                rs.next();
                String text = rs.getString(2);
                TextArea temp = new TextArea();
                temp.setText(text);
                temp.textProperty().addListener((observable, oldValue, newValue) -> {
                    String query2 ="UPDATE " + branchName + " SET notes ='" + temp.getText() + "' WHERE id = 1;";
                    try {
                        Statement stmt2 = getConnection().createStatement();
                         stmt2.executeUpdate(query2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                Main.getSp2().setContent(temp);
                Main.setCurrent(branchName);
            }
            else if(rsmd.getColumnCount()>2){
                TableView table = new TableView();

                TableColumn imgCol = new TableColumn<>("");
                imgCol.setPrefWidth(100);
                imgCol.setCellValueFactory(new PropertyValueFactory<>("imageview"));
                TableColumn nameCol = new TableColumn<>("Name");
                nameCol.setCellValueFactory(new PropertyValueFactory<>("itemname"));
                nameCol.setPrefWidth(200);
                TableColumn desCol = new TableColumn<>("Description");
                desCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                desCol.setPrefWidth(200);
                TableColumn relCol = new TableColumn<>("Release");
                relCol.setCellValueFactory(new PropertyValueFactory<>("releasedate"));
                TableColumn buyCol = new TableColumn<>("Bought");
                buyCol.setCellValueFactory(new PropertyValueFactory<>("bought"));
                TableColumn priceCol = new TableColumn<>("Price");
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price") );

                while(rs.next()){
                    SumItem temp = new SumItem(rs.getString(2),rs.getString(3),rs.getString(4),
                            rs.getString(5),rs.getBoolean(6),rs.getInt(7));
                    table.getItems().add(temp);
                }table.getColumns().addAll(imgCol,nameCol,desCol,relCol,buyCol,priceCol);

                Main.getSp2().setContent(table);
                Main.setCurrent(branchName);


            }




    }

//    public void setData(String branchName) throws SQLException {
//        String query = "SELECT * FROM " + branchName;
//        Statement stmt = getConnection().createStatement();
//        ResultSet rs = stmt.executeQuery(query);
//        ResultSetMetaData rsmd = rs.getMetaData();
//        while (rs.next()) {
//            if (rsmd.getColumnCount() == 1) {
//                TextArea temp
//            }
//        }
//
//
//    }
}
