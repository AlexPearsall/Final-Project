package battlesim;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets the basic GUI display along with
 */
public class BattlefieldController {

    private static BattlefieldController theController = null;
    @FXML
    private Pane theBattlefield;
    @FXML
    private ImageView imageHolder;
    private Label labelRed;
    private Label labelBlue;
    private ImageView arrowImage;
    private final List<Soldier> soldiers = new ArrayList<>();
    private int numberOfSoldiers;
    private int numberOfEnemies;
    private String currentFactory = "R";

    /**
     *
     */
    @FXML
    public void initialize() {

        imageHolder.setImage(new Image("images/Battlefield-Image.png"));
        theController = this;

        createInterface();

        for (int i = 0; i < numberOfSoldiers; i++) {
            soldiers.add(new Soldier(new Point2D(Math.random() * 960, Math.random() * 540), this));
        }
    }

    /**
     * Creates all the main buttons and labels to be displayed on the GUI
     */
    private void createInterface() {
        int dimensions = 45;

        labelBlue = createLabel(125, 500);
        labelRed = createLabel(817, 500);

        ImageView plusImageBlue = createImageView("images/Plus-Image.png", dimensions / 2);
        Button plusButtonBlue = createButton(173, 505, plusImageBlue, 0, false, 0);
        ButtonHandler plusBlueHandler = new ButtonHandler();
        plusBlueHandler.changeAmount(plusButtonBlue, labelBlue, 1);
        ImageView plusImageRed = createImageView("images/Plus-Image.png", dimensions / 2);
        Button plusButtonRed = createButton(865, 505, plusImageRed, 0, false, 0);
        ButtonHandler plusRedHandler = new ButtonHandler();
        plusRedHandler.changeAmount(plusButtonRed, labelRed, 1);

        ImageView minusImageBlue = createImageView("images/Minus-Image.png", dimensions / 2);
        Button minusButtonBlue = createButton(73, 505, minusImageBlue, 0, false, 0);
        ButtonHandler minusBlueHandler = new ButtonHandler();
        minusBlueHandler.changeAmount(minusButtonBlue, labelBlue, -1);
        ImageView minusImageRed = createImageView("images/Minus-Image.png", dimensions / 2);
        Button minusButtonRed = createButton(765, 505, minusImageRed, 0, false, 0);
        ButtonHandler minusRedHandler = new ButtonHandler();
        minusRedHandler.changeAmount(minusButtonRed, labelRed, -1);


        ImageView playImage = createImageView("images/Play-Image.png", dimensions);
        ImageView pauseImage = createImageView("images/Pause-Image.png", dimensions);

        //440 middle / 370 side
        Button playPauseButton = createButton(370, 447, playImage, 10, false, 3);
        ButtonHandler playPauseHandler = new ButtonHandler();
        playPauseHandler.playPause(playPauseButton, playImage, pauseImage);

        Button setBoard = new Button("Set Board");
        setBoard.setLayoutX(495);
        setBoard.setLayoutY(447);
        ButtonHandler setBoardHandler = new ButtonHandler();
        setBoardHandler.getEntities(setBoard, labelBlue, labelRed, this);

        arrowImage = new ImageView(new Image("images/Arrow-image.png"));
        arrowImage.setFitWidth(23);
        arrowImage.setPreserveRatio(true);
        arrowImage.setLayoutX(490);
        arrowImage.setLayoutY(477);

        Button randomFactoryButton = new Button("R");
        randomFactoryButton.setLayoutX(490);
        randomFactoryButton.setLayoutY(500);
        ButtonHandler rFactory = new ButtonHandler();
        rFactory.activeFactory(randomFactoryButton, arrowImage, this);

        Button balancedFactoryButton = new Button("B");
        balancedFactoryButton.setLayoutX(545);
        balancedFactoryButton.setLayoutY(500);
        ButtonHandler bFactory = new ButtonHandler();
        bFactory.activeFactory(balancedFactoryButton, arrowImage, this);

        theBattlefield.getChildren().addAll(playPauseButton, plusButtonBlue, minusButtonBlue,
                plusButtonRed, minusButtonRed, labelBlue, labelRed, setBoard, randomFactoryButton,
                balancedFactoryButton, arrowImage);
    }

    /**
     * Sets the number of enemies to be displayed
     * @param enemies The enemy
     */
    public void setEnemies(int enemies) {
        numberOfEnemies = enemies;
    }

    /**
     * Sets the number of soldiers to be displayed
     * @param soldiers The soldier
     */
    public void setSoldiers(int soldiers) {
        numberOfSoldiers = soldiers;
    }

    /**
     * Sets the current factory to use when generating weapons
     * @param factory The factory method
     */
    public void setCurrentFactory(String factory) {
        currentFactory = factory;
    }

    /**
     * Gets the current factory
     * @return The current factory method
     */
    public String getCurrentFactory() {
        return currentFactory;
    }

    /**
     * Creates a label that will display the number of entities on the board
     * @param x The x position of the label
     * @param y The y position of the label
     * @return A label that displayes the number of entities
     */
    private Label createLabel(int x, int y) {
        Label label = new Label();
        label.setText("0");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    /**
     * Creates an imageview with an image inside of it
     * @param imagePath The file directory of the image
     * @param dims The dimensions of the image
     * @return An imageview with an image in it
     */
    private ImageView createImageView(String imagePath, int dims) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(dims);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Creates a button to be interacted with
     * @param x The x position of the button
     * @param y The y position of the button
     * @param image The image of the button
     * @param padding The padding around the image
     * @param setFill Determines if the button should be filled in or transparent
     * @param boarderWidth The boarder width of the button
     * @return A button
     */
    private Button createButton(int x, int y, ImageView image, int padding, boolean setFill, int boarderWidth) {
        Button button = new Button();
        button.setTranslateX(x);
        button.setTranslateY(y);
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

    /**
     * Adds entities to the field
     * @param node The entity to be added
     */
    public void addEntities(Node node) {
        getTheController().instanceAddChild(node);
    }

    /**
     * Gets the current controller
     * @return The controller
     */
    public BattlefieldController getTheController() {
        if (theController == null) {
            throw new IllegalStateException("theController should not be accessed before the " +
                    "gameboard is initialized.");
        }
        return theController;
    }

    /**
     * Adds entities to the field
     * @param node The entity to be added
     */
    private void instanceAddChild(Node node) {
        theBattlefield.getChildren().add(node);
    }
}
