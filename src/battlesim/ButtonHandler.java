package battlesim;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Handler to assist buttons on the GUI
 */
public class ButtonHandler {
    String currentFactory;

    public ButtonHandler() {

    }

    /**
     * Changes the number of entities to spawn on the battlefield
     *
     * @param button The button that was pressed
     * @param label  The label that should display the entity amount
     * @param sum    An integer that will either add or subtract the current number of entities
     */
    public void changeAmount(Button button, Label label, int sum) {
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int current = Integer.parseInt(label.getText());
                if (current == 0 && sum == -1) {
                    label.setText("0");
                } else {
                    current += sum;
                    label.setText(Integer.toString(current));
                }
            }
        });
    }

    /**
     * A button that can start or stop the current time
     *
     * @param button The button that starts or stops the time
     * @param play   An image of the play button
     * @param pause  An image of the pause button
     */
    public void playPause(Button button, ImageView play, ImageView pause) {
        button.setOnAction(new EventHandler() {
            int current = 0;

            @Override
            public void handle(Event event) {
                if (current == 0) {
                    button.setGraphic(pause);
                    current = 1;
                } else if (current == 1) {
                    button.setGraphic(play);
                    current = 0;
                }
            }
        });
    }

    /**
     * Buttons that set the factory that should be used
     *
     * @param button     The factory button
     * @param arrow      An arrow that shows which factory is currently selected
     * @param controller The main controller of the program
     */
    public void activeFactory(Button button, ImageView arrow, String factory, BattlefieldController controller) {

        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                double locationX = button.getLayoutX() + 11;
                System.out.println(locationX);
                arrow.setLayoutX(locationX);
                System.out.println(factory + " Factory Selected");
                controller.setCurrentFactory(factory);
            }
        });
    }

    public void getEntities(Button button, Label blue, Label red, BattlefieldController controller) {
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int blue1 = Integer.parseInt(blue.getText());
                int red1 = Integer.parseInt(red.getText());

                controller.setSoldiers(blue1);
                controller.setEnemies(red1);

                System.out.println("Num Soldiers: " + blue1);
                System.out.println("Num Enemies: " + red1);
                System.out.println("Current Factory: " + controller.getCurrentFactory());
            }
        });
    }
}
