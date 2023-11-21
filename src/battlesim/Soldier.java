package battlesim;

import sword.Sword;

public class Soldier implements Entity {
    private Sword weapon;
    public Soldier(Sword sword) {
        weapon = sword;
    }
    @Override
    public int attack() {
        return 0;
    }
}
