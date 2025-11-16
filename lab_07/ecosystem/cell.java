package ecosystem;

import java.util.ArrayList;

public class Cell {
    private int row, col;
    private ArrayList<Creature> creatures = new ArrayList<>();

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public void clear() {
        creatures.clear();
    }

    @Override
    public String toString() {
        return "Cell(" + row + ", " + col + ") Creatures: " + creatures;
    }
}
