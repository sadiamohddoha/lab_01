import java.io.*;
import java.util.*;

public class ProcessCreatureFile {

    public static void main(String[] args) {
        ArrayList<Creature> creatures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("creature-data.csv"))) {
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
            e.printStackTrace();
        }

        
        creatures.add(new Creature("Goblin", 30.0, "Green"));
        creatures.remove(0);
        creatures.get(1).color = "Purple";

        
        try (PrintWriter pw = new PrintWriter(new FileWriter("creature-data.csv"))) {
            pw.println("name,size,color");
            for (Creature c : creatures) {
                pw.println(c.name + "," + c.size + "," + c.color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
