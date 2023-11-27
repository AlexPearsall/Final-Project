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
import java.util.Random;

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
    Label winHow;
    private ImageView arrowImage;
    private final List<Soldier> soldiers = new ArrayList<>();
    private int numberOfSoldiers;
    private int numberOfEnemies;
    private String currentFactory = "R";
    private final int minSpawnX = 0;
    private final int minSpawnY = 0;
    private final int maxSpawnX = 200;
    private final int maxSpawnY = 400;

    /**
     *
     */
    @FXML
    public void initialize() {

        imageHolder.setImage(new Image("images/Battlefield-Image.png"));
        theController = this;

        createInterface();

        Random random = new Random();
        for (Soldier soldier: soldiers) {
            int startX = random.nextInt(maxSpawnX - minSpawnX) + minSpawnX;
            int startY = random.nextInt(maxSpawnY - minSpawnY) + minSpawnY;
            soldier.setStart(startX, startY);
        }

        //winnerDisplay("Soldiers", 0);
    }

    /**
     * Creates all the main buttons and labels to be displayed on the GUI
     */
    private void createInterface() {
        int dimensions = 45;

        labelBlue = createLabel(125, 500, 35);
        labelRed = createLabel(817, 500, 35);
        Label blueSword = createLabel(74, 458, 22);
        Label blueBow = createLabel(161, 458, 22);
        Label redSword = createLabel(767, 458, 22);
        Label redBow = createLabel(851, 458, 22);

        ImageView plusImageBlue = createImageView("images/Plus-Image.png", dimensions / 2);
        Button plusButtonBlue = createButton(173, 505, plusImageBlue);
        ButtonHandler plusBlueHandler = new ButtonHandler();
        plusBlueHandler.changeAmount(plusButtonBlue, labelBlue, 1);
        ImageView plusImageRed = createImageView("images/Plus-Image.png", dimensions / 2);
        Button plusButtonRed = createButton(865, 505, plusImageRed);
        ButtonHandler plusRedHandler = new ButtonHandler();
        plusRedHandler.changeAmount(plusButtonRed, labelRed, 1);

        ImageView minusImageBlue = createImageView("images/Minus-Image.png", dimensions / 2);
        Button minusButtonBlue = createButton(58, 505, minusImageBlue);
        ButtonHandler minusBlueHandler = new ButtonHandler();
        minusBlueHandler.changeAmount(minusButtonBlue, labelBlue, -1);
        ImageView minusImageRed = createImageView("images/Minus-Image.png", dimensions / 2);
        Button minusButtonRed = createButton(750, 505, minusImageRed);
        ButtonHandler minusRedHandler = new ButtonHandler();
        minusRedHandler.changeAmount(minusButtonRed, labelRed, -1);


        ImageView playImage = createImageView("images/Play-Image.png", 86);
        ImageView pauseImage = createImageView("images/Pause-Image.png", 86);

        //440 middle / 370 side
        Button playPauseButton = createButton(355, 435, playImage);
        ButtonHandler playPauseHandler = new ButtonHandler();
        playPauseHandler.playPause(playPauseButton, playImage, pauseImage);

        ImageView restartImage = createImageView("images/Restart-Image.png", 48);
        Button restartButton = createButton(459, 478, restartImage);

        ImageView setBoardImage = createImageView("images/SetBoard-Image.png", 41);
        Button setBoard = createButton(462, 427, setBoardImage);
        ButtonHandler setBoardHandler = new ButtonHandler();
        setBoardHandler.getEntities(setBoard, labelBlue, labelRed, this);

        arrowImage = createImageView("images/Arrow-image.png", 23);
        arrowImage.setLayoutX(531);
        arrowImage.setLayoutY(477);

        ImageView randomImage = createImageView("images/Random-Image.png", 30);
        Button randomFactoryButton = createButton(520, 497, randomImage);
        ButtonHandler rFactory = new ButtonHandler();
        rFactory.activeFactory(randomFactoryButton, arrowImage, "Random", this);

        ImageView balanceImage = createImageView("images/Balance-Image.png", 30);
        Button balancedFactoryButton = createButton(560, 497, balanceImage);
        ButtonHandler bFactory = new ButtonHandler();
        bFactory.activeFactory(balancedFactoryButton, arrowImage, "Balanced", this);

        theBattlefield.getChildren().addAll(playPauseButton, plusButtonBlue, minusButtonBlue,
                plusButtonRed, minusButtonRed, labelBlue, labelRed, setBoard, randomFactoryButton,
                balancedFactoryButton, arrowImage, restartButton, blueSword, blueBow, redSword,
                redBow);
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
    private Label createLabel(int x, int y, int fontSize) {
        Label label = new Label();
        label.setText("0");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
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
     * @return A button
     */
    private Button createButton(int x, int y, ImageView image) {
        Button button = new Button();
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setGraphic(image);

        //button.setStyle("-fx-background-color: transparent;");
        button.getStylesheets().add(getClass().getResource("ButtonFX.css").toExternalForm());

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

    private void winnerDisplay(String winner, int winBy) {
        winHow = createLabel(345, 35, 15);

        if (winBy == 0) {
            winHow.setText("No enemies left to continue the battle.\n" + winner + " win!");
        } else if (winBy == 1) {
            winHow.setText(winner + " breached the opponents side.\n" + winner + " win!");
        }

        theBattlefield.getChildren().add(winHow);
    }
}
