package battlesim;

import sword.Sword;

public class Soldier implements Entity {
    private Sword weapon;
    private int x, y;
    public Soldier(Sword sword, int startX, int startY) {
        weapon = sword;
        x = startX;
        y = startY;
    }
    @Override
    public int attack() {
        return 0;
    }

    public void setStart(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public void moveTo(int targetX, int targetY) {
        x = targetX;
        y = targetY;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }
}
