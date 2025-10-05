import java.io.*;
import java.util.ArrayList;

public class ProcessCreatureFile {
    
    private ArrayList<Creature> creatures;
    
    private final String FILENAME = "creature-data.csv";

    public ProcessCreatureFile() {
        this.creatures = new ArrayList<>();
    }
    
    // Gets the current list 
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    
    public void loadCreatures() throws IOException, NumberFormatException {
        creatures.clear(); 
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                
                if (data.length == 3) {
                    creatures.add(new Creature(data[0], Double.parseDouble(data[1]), data[2]));
                }
            }
        }
    }

    
    public void saveCreatures() throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) { 
            for (Creature c : creatures) {
                
                bw.write(c.getName() + "," + c.getSize() + "," + c.getColor());
                bw.newLine();
            }
        }
    }

    
    public void addCreature(String name, double size, String color) throws IOException {
        Creature newCreature = new Creature(name, size, color);
        creatures.add(newCreature);
        saveCreatures(); 
    }

    
    public void removeCreature(Creature c) throws IOException {
        creatures.remove(c);
        saveCreatures(); 
    }
    
    
    public void updateAndSave() throws IOException {
        saveCreatures();
    }
}

