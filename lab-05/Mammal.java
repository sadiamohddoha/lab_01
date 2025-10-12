public class Mammal extends Creature {

    public Mammal(String speciesName) {
        super("Mammal: " + speciesName);
    }

    @Override
    public void takeTurn() {
        System.out.println("  [" + getName() + "] is searching for food on land.");
    }
}
