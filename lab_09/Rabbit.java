import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Rabbit {
    private static final Random rand = new Random();

    public double x;
    public double y;
    public double vx;
    public double vy;

    public int radius = 10;

    // Sprite sheet animation
    private BufferedImage spriteSheet;
    private int frameW = 32;
    private int frameH = 32;
    private int totalFrames = 4;
    private int frameIndex = 0;
    private int frameTick = 0;
    private int frameDelay = 6;

    public Rabbit(double x, double y, double speed, BufferedImage spriteSheet) {
        this.x = x;
        this.y = y;
        this.spriteSheet = spriteSheet;

        vx = (rand.nextDouble() * 2 - 1) * speed;
        vy = (rand.nextDouble() * 2 - 1) * speed;

        if (Math.abs(vx) < 0.5) vx = speed * 0.8;
        if (Math.abs(vy) < 0.5) vy = speed * 0.8;
    }

    public void update(int w, int h) {
        x += vx;
        y += vy;

        // Bounce off walls
        if (x - radius < 0) { x = radius; vx *= -1; }
        if (x + radius > w) { x = w - radius; vx *= -1; }
        if (y - radius < 0) { y = radius; vy *= -1; }
        if (y + radius > h) { y = h - radius; vy *= -1; }

        animate();
    }

    private void animate() {
        if (spriteSheet == null) return;

        frameTick++;
        if (frameTick >= frameDelay) {
            frameTick = 0;
            frameIndex = (frameIndex + 1) % totalFrames;
        }
    }

    public void draw(Graphics2D g) {
        if (spriteSheet == null) {
            g.setColor(Color.PINK);
            g.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
            return;
        }

        int sx = frameIndex * frameW;
        int sy = 0;

        g.drawImage(
            spriteSheet,
            (int) (x - radius), (int) (y - radius),
            (int) (x - radius) + radius * 2, (int) (y - radius) + radius * 2,
            sx, sy, sx + frameW, sy + frameH,
            null
        );
    }
}
