import java.io.*;
import java.util.*;

public class CreatureRegistry {
    private ArrayList<Creature> creatures;

    public CreatureRegistry(String filename) {
        creatures = new ArrayList<>();
        loadCreaturesFromFile(filename);
    }

    private void loadCreaturesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                creatures.add(new Creature(data[0], Double.parseDouble(data[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(int index) {
        creatures.remove(index);
    }

    public Creature getCreature(int index) {
        return creatures.get(index);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Creature c : creatures) {
                bw.write(c.name + "," + c.size);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCreatureCount() {
        return creatures.size();
    }
}
