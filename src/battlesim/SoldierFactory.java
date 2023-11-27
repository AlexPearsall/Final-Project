package battlesim;

import sword.Sword;
import sword.SwordFactory;

import java.util.Random;

/**
 * Factory for creating Soldiers
 */
public class SoldierFactory implements EntityFactory {
    private SwordFactory swordFactory;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private Random random;

    /**
     * Constructor
     * @param factory type
     */
    public SoldierFactory(SwordFactory factory, int minX, int minY, int maxX, int maxY) {
        this.swordFactory = factory;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.random = new Random();
    }
    @Override
    public Entity createProduct() {
        Sword sword = swordFactory.createSword();
        int startX = random.nextInt(maxX - minX) + minX;
        int startY = random.nextInt(maxY - minY) + minY;
        return new Soldier(sword, startX, startY);
    }
}
