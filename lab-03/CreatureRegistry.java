import java.io.*;
import java.util.*;

public class CreatureRegistry {

    private ArrayList<Creature> creatures = new ArrayList<>();
    private String filename;

    public CreatureRegistry(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                String name = parts[0];
                double size = Double.parseDouble(parts[1]);
                String color = parts[2];

                creatures.add(new Creature(name, size, color));
            }
        } catch (Exception e) {
            System.err.println("Error loading file");
        }
    }

    public int getCount() {
        return creatures.size();
    }

    public Creature getCreatureCopy(int index) {
        Creature c = creatures.get(index);
        return new Creature(c.name, c.size, c.color);
    }

    public void updateCreature(int index, Creature newC) {
        creatures.set(index, newC);
    }

    public void deleteCreature(int index) {
        creatures.remove(index);
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("name,size,color");
            for (Creature c : creatures) {
                pw.println(c.name + "," + c.size + "," + c.color);
            }
        } catch (Exception e) {
            System.err.println("Could not save");
        }
    }

    public static void main(String[] args) {
        CreatureRegistry reg = new CreatureRegistry("creature-data.csv");
        System.out.println("Total creatures: " + reg.getCount());
    }
}

