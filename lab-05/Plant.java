
public class Plant extends Creature {

    
    private static final java.util.Map<String, String> SPECIES_DEFINITIONS = new java.util.HashMap<>();
    static {
        SPECIES_DEFINITIONS.put("OakTree", "perennial, wooden, seed");
        SPECIES_DEFINITIONS.put("Moss", "seasonal, soft, spore");
    }

    private final String species;
    private final boolean isSeasonal;    
    private final boolean hasWoodenStem; 
    private final ReproductionStrategy reproducer; 

  
    private interface ReproductionStrategy {
        void reproduce();
    }
    
    
    private class SeedReproduction implements ReproductionStrategy {
        public void reproduce() { System.out.println("  [Plant Action]: Producing seeds (Seed Strategy)."); }
    }

    public Plant(String speciesName) {
        super(speciesName); 
        
        String properties = SPECIES_DEFINITIONS.get(speciesName);
        if (properties == null) {
            properties = "perennial, soft, seed"; 
        }

        this.species = speciesName;

        
        this.isSeasonal = properties.contains("seasonal");
        this.hasWoodenStem = properties.contains("wooden");
        
        
        if (properties.contains("spore")) {
            this.reproducer = () -> System.out.println("  [Plant Action]: Releasing spores (Spore Strategy).");
        } else {
            this.reproducer = new SeedReproduction();
        }
    }

    
    @Override
    public void takeTurn() {
        System.out.println("  <" + species + ">: Taking turn. Seasonal: " + isSeasonal);
        reproducer.reproduce(); 
    }
}
