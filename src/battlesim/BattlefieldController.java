package battlesim;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.util.ArrayList;
import java.util.List;

public class BattlefieldController {

    public static final int NUM_SOLDIERS = 5;
    private static BattlefieldController theController = null;
    @FXML
    private Pane theBattlefield;
    @FXML
    private ImageView imageHolder;
    private Label labelRed;
    private Label labelBlue;
    private TextField textField;
    private final List<Soldier> soldiers = new ArrayList<>();
    private int numberOfSoldiers;
    private int numberOfEnemies;

    @FXML
    public void initialize() {

        imageHolder.setImage(new Image("images/Battlefield-Image.png"));
        theController = this;

        createInterface();
        //getInput();

        for (int i = 0; i < NUM_SOLDIERS; i++) {
            System.out.println("Created battlesim.Soldier");
            soldiers.add(new Soldier(new Point2D(Math.random() * 960, Math.random() * 540), this));
        }
    }

//    private void getInput() {
//        numberOfSoldiers = askFor("Number of Soldiers?", 0);
//        askFor("Number of Enemies?", 1);
//        askFor("DONE", 2);
//    }


//    private int askFor(String question) {
//        label.setText(question);
//        textField.clear();
//        textField.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                try {
//                    return Integer.parseInt(textField.getText());
//                } catch (NumberFormatException e) {
//                    label.setText("Please enter a valid integer for " + question.toLowerCase() + ".");
//                    textField.clear();
//                }
//            }
//        });
//        return -1;
//    }

    private void createInterface() {
        int dimensions = 45;

//        label = new Label();
//        label.setTranslateX(370);
//        label.setTranslateY(450);
//        label.setTextFill(Color.WHITE);
//
//        textField = new TextField();
//        textField.setMaxWidth(50);
//        textField.setTranslateX(370);
//        textField.setTranslateY(480);

        labelBlue = createLabel(125, 500);
        labelRed = createLabel(817, 500);

        ImageView plusImageBlue = createImageView("images/Plus-Image.png", dimensions/2);
        Button plusButtonBlue = createButton(173, 505, plusImageBlue, 0, false, 0);
        ButtonHandler plusBlueHandler = new ButtonHandler(plusButtonBlue, labelBlue, 1);
        ImageView plusImageRed = createImageView("images/Plus-Image.png", dimensions/2);
        Button plusButtonRed = createButton(865, 505, plusImageRed, 0, false, 0);
        ButtonHandler plusRedHandler = new ButtonHandler(plusButtonRed, labelRed, 1);

        ImageView minusImageBlue = createImageView("images/Minus-Image.png", dimensions/2);
        Button minusButtonBlue = createButton(73, 505, minusImageBlue, 0, false, 0);
        ButtonHandler minusBlueHandler = new ButtonHandler(minusButtonBlue, labelBlue, -1);
        ImageView minusImageRed = createImageView("images/Minus-Image.png", dimensions/2);
        Button minusButtonRed = createButton(765, 505, minusImageRed, 0, false, 0);
        ButtonHandler minusRedHandler = new ButtonHandler(minusButtonRed, labelRed, -1);


        ImageView playImage = createImageView("images/Play-Image.png", dimensions);
        ImageView pauseImage = createImageView("images/Pause-Image.png", dimensions);

        //440 middle / 370 side
        Button playPauseButton = createButton(370, 447, playImage, 10, false, 3);
        playPauseButton.setOnAction(new EventHandler() {
            int current = 0;

            @Override
            public void handle(Event event) {
                if (current == 0) {
                    playPauseButton.setGraphic(pauseImage);
                    current = 1;
                } else if (current == 1) {
                    playPauseButton.setGraphic(playImage);
                    current = 0;
                }
            }
        });

        Button setBoard = new Button("Set Board");
        setBoard.setLayoutX(480);
        setBoard.setLayoutY(447);
        setBoard.setOnAction(getEntities());


        //ImageView restartImage = createImageView("images/Restart-Image.png", dimensions/2);

        //Button restartButton = createButton(550, 447, restartImage, 2, true);

        theBattlefield.getChildren().addAll(playPauseButton, plusButtonBlue, minusButtonBlue,
                plusButtonRed, minusButtonRed, labelBlue, labelRed, setBoard);
    }

    private EventHandler getEntities() {
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                int blue = Integer.parseInt(labelBlue.getText());
                int red = Integer.parseInt(labelRed.getText());

                numberOfSoldiers = blue;
                numberOfEnemies = red;

                System.out.println("Num Soldiers " + blue);
                System.out.println("Num Enemies " + red);
            }
        };
        return eventHandler;
    }

    private Label createLabel(int x, int y) {
        Label label = new Label();
        label.setText("0");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD,35));
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    private void createButtonSides() {
        Button plusButton = new Button();
    }

    private ImageView createImageView(String imagePath, int dims) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(dims);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private Button createButton(int x, int y, ImageView image, int padding, boolean setFill, int boarderWidth) {
        Button button = new Button();
        button.setTranslateX(x);
        button.setTranslateY(y);
        //button.setPrefSize(dims, dims);
        button.setGraphic(image);

        String backgroundColor;
        if (setFill) {
            backgroundColor = "#3b3b3b";
        } else {
            backgroundColor = "transparent";
        }

            button.setStyle(
                    "-fx-background-color: " + backgroundColor + ";" +
                            "-fx-border-width: " + boarderWidth + ";" +
                            "-fx-border-radius: 20;" +
                            "-fx-border-color: #3b3b3b;" +
                            "-fx-padding:" + padding);

        return button;
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
