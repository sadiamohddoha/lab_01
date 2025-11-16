package ecosystem;

import java.io.File;
import java.util.Scanner;

public class World {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public World(String filename) {
        loadFromFile(filename);
    }

    public void loadFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            rows = sc.nextInt();
            cols = sc.nextInt();
            sc.nextLine();

            grid = new Cell[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = new Cell(r, c);
                }
            }

            // Example file format:
            // creatureName row col energy
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.equals("")) continue;

                String[] parts = line.split(" ");
                String name = parts[0];
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                int energy = Integer.parseInt(parts[3]);

                Creature cr = new Creature(name, energy);
                grid[r][c].addCreature(cr);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("ERROR loading initial file: " + e.getMessage());
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public Cell getCell(int r, int c) {
        return grid[r][c];
    }

    public void takeTurn() {
        // **Very simple example rule**
        // Every creature loses 1 energy
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (Creature cr : grid[r][c].getCreatures()) {
                    cr.changeEnergy(-1);
                }
            }
        }
        System.out.println("Turn completed.");
    }
}
