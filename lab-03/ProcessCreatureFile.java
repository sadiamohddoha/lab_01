import java.io.*;
import java.util.*;

public class ProcessCreatureFile {
    public static void main(String[] args) {
        ArrayList<Creature> creatures = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("creature-data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                creatures.add(new Creature(data[0], Double.parseDouble(data[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        creatures.add(new Creature("Dragon", 500));
        creatures.remove(2); 

        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("creature-data.csv"))) {
            for (Creature c : creatures) {
                bw.write(c.name + "," + c.size);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
