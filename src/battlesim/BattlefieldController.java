package battlesim;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.List;

public class BattlefieldController {

    public static final int NUM_SOLDIERS = 5;
    private static BattlefieldController theController = null;
    @FXML
    private Pane theBattlefield;
    @FXML
    private ImageView imageHolder;
    private final List<Soldier> soldiers = new ArrayList<>();

    @FXML
    public void initialize() {

        imageHolder.setImage(new Image("images/Battlefield-Image.png"));
        theController = this;

        createInterface();

        for (int i = 0; i < NUM_SOLDIERS; i++) {
            System.out.println("Created battlesim.Soldier");
            soldiers.add(new Soldier(new Point2D(Math.random() * 960, Math.random() * 540), this));
        }
    }

    private void createInterface() {
        int dimensions = 45;
        ImageView playButton = new ImageView(new Image("images/Play-Button.png"));
        playButton.setFitHeight(dimensions);
        playButton.setPreserveRatio(true);

        ImageView pauseButton = new ImageView(new Image("images/Pause-Button.png"));
        pauseButton.setFitHeight(dimensions);
        pauseButton.setPreserveRatio(true);

        Button imageButton = new Button();
        imageButton.setTranslateX(360);
        imageButton.setTranslateY(447);
        imageButton.setPrefSize(dimensions, dimensions);
        imageButton.setGraphic(playButton);
        imageButton.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: #3b3b3b;" +
                        "-fx-padding: 10");
        imageButton.setOnAction(new EventHandler() {
            int current = 0;

            @Override
            public void handle(Event event) {
                if (current == 0) {
                    imageButton.setGraphic(pauseButton);
                    current = 1;
                } else if (current == 1) {
                    imageButton.setGraphic(playButton);
                    current = 0;
                }
            }
        });

        theBattlefield.getChildren().addAll(imageButton);
    }

    public static void addChild(Node node) {
        getTheController().instanceAddChild(node);
    }

    public static BattlefieldController getTheController() {
        if (theController == null) {
            throw new IllegalStateException("theController should not be accessed before the " +
                    "gameboard is initialized.");
        }
        return theController;
    }

    private void instanceAddChild(Node node) {
        theBattlefield.getChildren().add(node);
    }

}
