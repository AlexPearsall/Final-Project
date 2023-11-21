package battlesim;

import bow.BowFactory;

/**
 * Factory for creating Archers
 */
public class ArcherFactory implements EntityFactory {
    private BowFactory bowFactory;

    /**
     * Constructor
     * @param factory type
     */
    public ArcherFactory(BowFactory factory) {
        this.bowFactory = factory;
    }
    @Override
    public Entity createProduct() {
        return new Archer(bowFactory.createBow());
    }
}
