import java.util.ArrayList;

public abstract class Creature implements TurnTaker {

    private final String name;

   

    // Constructor 1
    public Creature(String name) {
        
        this.name = name;
        System.out.println("  [System] Creature created: " + this.name);
    }

    // Constructor 2
    public Creature() {
        
        this("Unnamed_Creature");
    }

    
    @Override
    public abstract void takeTurn();

    // Getter 
    protected String getName() {
        return name;
    }

   
    // Overloaded Method 1
    public void interact(Creature other) {
        System.out.println("  " + this.name + " observes " + other.getName() + " on the tile.");
    }

    // Overloaded Method 2
    public void interact(ArrayList<Creature> others) {
        if (!others.isEmpty()) {
            System.out.println("  " + this.name + " interacts with a group of " + others.size() + " other life forms.");
        }
    }
}
