package ecosystem;

public class Creature {
    private String name;
    private int energy;

    public Creature(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public void changeEnergy(int delta) {
        energy += delta;
    }

    @Override
    public String toString() {
        return name + " (energy=" + energy + ")";
    }
}
