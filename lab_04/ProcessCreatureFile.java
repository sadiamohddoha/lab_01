import java.io.*;
import java.util.*;

public class ProcessCreatureFile {
    public static void main(String[] args) {
        // Step 1
        ArrayList<Creature> creatures = new ArrayList<>();

        // Step 2
        try (BufferedReader br = new BufferedReader(new FileReader("creature-data.csv"))) {
            String line;
            // Read each line in the file
            while ((line = br.readLine()) != null) {
                // Split the line by commas (name, size, color)
                String[] data = line.split(",");
                // Create a new Creature object from the data and add it to the list
                creatures.add(new Creature(data[0], Double.parseDouble(data[1]), data[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 3
        // Example
        creatures.add(new Creature("Dragon", 500, "Green"));
        
        
        if (creatures.size() > 2) {
            creatures.remove(2);
        }

        
        if (!creatures.isEmpty()) {
            Creature firstCreature = creatures.get(0);
            firstCreature.name = "NewName";  
            firstCreature.size = 1.5;        
        }

        // Step 4
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/sdoha/Documents/creature-data.csv"))) {
            
            for (Creature c : creatures) {
                bw.write(c.name + "," + c.size + "," + c.color);  
                bw.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 5
        for (Creature c : creatures) {
            c.display();  // Display creature details using the display() method
        }
    }
}

