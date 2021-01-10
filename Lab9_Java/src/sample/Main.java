package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    public static Connection c;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Client JDBC Sqlite");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        c = DriverManager.getConnection("jdbc:sqlite:seminar9_1055.db");

    }

    @Override
    public void stop() throws Exception {
        if (c!=null){
            c.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
