import javafx.fxml.FXML;
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
    private final List<Soldier> soldiers = new ArrayList<>();

    @FXML
    public void initialize() {

        theBattlefield.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        theController = this;

        createInterface();

        for(int i = 0; i < NUM_SOLDIERS; i++) {
            soldiers.add(new Soldier());
        }
    }

    private void createInterface() {
        Rectangle rect1 = new Rectangle(0, 400, 1000, 200);
        rect1.setFill(Color.GRAY);

        theBattlefield.getChildren().addAll(rect1);
    }

}
