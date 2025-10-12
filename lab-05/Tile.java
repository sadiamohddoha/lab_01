import java.util.ArrayList;
public class Tile implements TurnTaker {

    private final ArrayList<Creature> creatures; 
    
    private int water;
    private int temperature; 
    private int nutrients;

    public Tile(int water, int temperature, int nutrients) {
        this.creatures = new ArrayList<>();
        this.water = water;
        this.temperature = temperature;
        this.nutrients = nutrients;
    }
    
    public void addCreature(Creature c) {
        this.creatures.add(c);
    }
    @Override
    public void takeTurn() {
        System.out.println("  --- Tile Status: Temp=" + temperature + ", Water=" + water + " ---");
        
       
        for (Creature creature : creatures) {
            
            if (temperature > 30) {
                System.out.println("  [Tile Effect]: It's very hot! Creature movement slows.");
            }
            
            if (creatures.size() > 1) {
                creature.interact(creatures); 
            }
            creature.takeTurn();
        }
    }
}
