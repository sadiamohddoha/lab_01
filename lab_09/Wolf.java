import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Wolf {
    public double x;
    public double y;

    public int radius = 18;     // bigger required
    public double speed = 4.0;  // faster required

    // Sprite sheet animation
    private BufferedImage spriteSheet;
    private int frameW = 32;
    private int frameH = 32;
    private int totalFrames = 4;
    private int frameIndex = 0;
    private int frameTick = 0;
    private int frameDelay = 6;

    public Wolf(double x, double y, BufferedImage spriteSheet) {
        this.x = x;
        this.y = y;
        this.spriteSheet = spriteSheet;
    }

    public void update(int w, int h, InputHandler input) {
        if (input.left)  x -= speed;
        if (input.right) x += speed;
        if (input.up)    y -= speed;
        if (input.down)  y += speed;

        // Keep inside window
        if (x - radius < 0) x = radius;
        if (x + radius > w) x = w - radius;
        if (y - radius < 0) y = radius;
        if (y + radius > h) y = h - radius;

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
            // Color changed (not default)
            g.setColor(Color.CYAN);
            g.fillRect((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
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
