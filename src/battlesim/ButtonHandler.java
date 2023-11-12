package battlesim;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ButtonHandler {

    public ButtonHandler(Button button, Label label, int sum) {

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


}
