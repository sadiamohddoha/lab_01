import java.awt.Color;
import java.awt.Graphics2D;

public class Carrot {
    public double x;
    public double y;
    public int size = 10;

    public Carrot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillOval((int) (x - size / 2.0), (int) (y - size / 2.0), size, size);
    }
}
