public class Fish extends Creature {

    public Fish(String speciesName) {
        super("Fish: " + speciesName);
    }

    @Override
    public void takeTurn() {
        System.out.println("  [" + getName() + "] is swimming and breathing water.");
    }
}
