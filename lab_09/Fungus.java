import java.awt.Color;
import java.awt.Graphics2D;

public class Fungus {
    public double x;
    public double y;
    public int radius = 12;

    public Fungus(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(150, 0, 200));
        g.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }
}
