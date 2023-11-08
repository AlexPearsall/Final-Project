package battlesim;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Soldier implements Entity {

    private Point2D circleCenter;

    protected final Shape face;
    protected Shape leftEar;
    protected Shape rightEar;
    protected final Shape leftEye;
    protected final Shape rightEye;
    private BattlefieldController controller;

    public Soldier(Point2D randomPoint, BattlefieldController controller) {
        //super(randomPoint);
        this.controller = controller;
        face = createEllipse(30,30);
        leftEye = createEllipse(5,5);
        rightEye = createEllipse(5,5);
        leftEye.setFill(Color.BLACK);
        rightEye.setFill(Color.BLACK);
        leftEar = createEllipse(5,10);
        rightEar = createEllipse(5,10);
        face.setFill(Color.TAN);
        leftEar.setFill(Color.TAN);
        rightEar.setFill(Color.TAN);
    }

    @Override
    public void createEntity() {

    }

    @Override
    public Weapon equipWeapon() {
        return null;
    }

    protected Ellipse createEllipse(double radiusX, double radiusY) {
        final Ellipse ellipse;
        ellipse = new Ellipse();
        ellipse.setRadiusX(radiusX);
        ellipse.setRadiusY(radiusY);
        ellipse.setStroke(Color.WHITE);
        controller.addChild(ellipse);
        return ellipse;
    }
}
