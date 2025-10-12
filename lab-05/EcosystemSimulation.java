import java.util.ArrayList;

public class EcosystemSimulation {
    private static class InitialConditions {
        int width = 3;
        int height = 2;
        
        String[] creatures = {
            "OakTree, 0, 0", 
            "Cottontail, 0, 0",
            "Moss, 1, 1",
            "Eagle, 2, 0",
            "Shark, 1, 0"
        };
    }

    public static void main(String[] args) {

        System.out.println("[SYSTEM]: Loading initial conditions (simulated JSON read)...");
        InitialConditions config = new InitialConditions(); 

        World world = new World(config.width, config.height);

        for (String entry : config.creatures) {
            try {
                String[] parts = entry.split(", ");
                String species = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                Creature newCreature = null;

                if (species.equals("OakTree") || species.equals("Moss")) {
                    newCreature = new Plant(species);
                } else if (species.equals("Cottontail")) {
                    newCreature = new Mammal(species);
                } else if (species.equals("Eagle")) {
                    newCreature = new Bird(species);
                } else if (species.equals("Shark")) {
                    newCreature = new Fish(species);
                } else {
                    System.err.println("  [ERROR]: Unknown species: " + species);
                    continue;
                }
                
                world.grid[x][y].addCreature(newCreature);

            } catch (Exception e) {
                System.err.println("  [ERROR] Failed to populate world. " + e.getMessage());
            }
        }
        
        System.out.println("\n[SYSTEM]: Initial population complete. Starting simulation.");

        for (int turn = 1; turn <= 100; turn++) {
            System.out.println("\n==================================");
            System.out.println("           SIMULATION TURN " + turn);
            System.out.println("==================================");

            world.takeTurn();
        }
        
        System.out.println("\nSimulation finished after 100 turns.");
    }
}
