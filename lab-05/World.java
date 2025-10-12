public class World implements TurnTaker {

    public final Tile[][] grid; 
    private final int width;
    private final int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];

        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                this.grid[i][j] = new Tile(50, 25, 40); 
            }
        }
        System.out.println("\n[SYSTEM]: World created with size " + width + "x" + height + ".");
    }

    @Override
    public void takeTurn() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.println("Tile [" + i + "," + j + "]:");
                grid[i][j].takeTurn();
            }
        }
    }
}
