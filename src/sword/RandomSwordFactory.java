package sword;

public class RandomSwordFactory implements SwordFactory {
    @Override
    public Sword createSword() {
        return new ShortSword();
    }
}
