package seminar.seminar10.g1055;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    private Connection c;
    private ServerController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL locatie = getClass().getResource("scenaServer.fxml");
        FXMLLoader loader = new FXMLLoader(locatie);
        Parent root = loader.load();
        controller = (ServerController) loader.getController();
        controller.setC(c);
        primaryStage.setTitle("SERVER");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        c = DriverManager.getConnection("jdbc:sqlite:seminar10.db");
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
