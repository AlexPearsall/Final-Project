package battlesim;

import bow.Bow;

public class Archer implements Entity {
    private Bow weapon;
    public Archer(Bow bow) {
        weapon = bow;
    }
    @Override
    public int attack() {
        return 0;
    }
}
