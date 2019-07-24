package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuScreen.fxml"));
        primaryStage.setTitle("Apka");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        new People("Dawid", "Koziej");
        new People("Adam", "Koziej");
        new People("Adam", "Macura");
        new People("Dawid", "Macura");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
