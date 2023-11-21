package battlesim;

import sword.SwordFactory;

/**
 * Factory for creating Soldiers
 */
public class SoldierFactory implements EntityFactory {
    private SwordFactory swordFactory;

    /**
     * Constructor
     * @param factory type
     */
    public SoldierFactory(SwordFactory factory) {
        this.swordFactory = factory;
    }
    @Override
    public Entity createProduct() {
        return new Soldier(swordFactory.createSword());
    }
}
