public class Bird extends Creature {

    public Bird(String speciesName) {
        super("Bird: " + speciesName);
    }

    @Override
    public void takeTurn() {
        System.out.println("  [" + getName() + "] is flying and looking for a nest site.");
    }
}
