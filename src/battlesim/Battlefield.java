package battlesim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Battlefield extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("battlefield.fxml"));
        primaryStage.setTitle("Battlefield");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("battlefield.fxml")));
//        primaryStage.setTitle("Battlefield");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

}
