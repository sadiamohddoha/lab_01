import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Ecosystem {
    private final Random rand = new Random();

    public final ArrayList<Carrot> carrots = new ArrayList<>();
    public final ArrayList<Rabbit> rabbits = new ArrayList<>();

    public Wolf wolf;
    public Fungus fungus;

    private int width;
    private int height;

    public Ecosystem(int width, int height, BufferedImage rabbitSheet, BufferedImage wolfSheet) {
        this.width = width;
        this.height = height;

        wolf = new Wolf(width / 2.0, height / 2.0, wolfSheet);
        fungus = new Fungus(width * 0.25, height * 0.25);

        for (int i = 0; i < 30; i++) spawnCarrot();
        for (int i = 0; i < 10; i++) spawnRabbit(rabbitSheet);
    }

    public void update(InputHandler input, BufferedImage rabbitSheet) {
        wolf.update(width, height, input);

        for (Rabbit r : rabbits) {
            r.update(width, height);
        }

        rabbitEatCarrots();
        wolfEatRabbits();
        fungusEatCarrotsAndWolf();

        // Keep some carrots present
        if (carrots.size() < 15) {
            for (int i = 0; i < 5; i++) spawnCarrot();
        }

        // Keep some rabbits present
        if (rabbits.size() < 6) {
            spawnRabbit(rabbitSheet);
        }
    }

    public void draw(Graphics2D g) {
        for (Carrot c : carrots) c.draw(g);
        for (Rabbit r : rabbits) r.draw(g);

        fungus.draw(g);
        wolf.draw(g);
    }

    private void spawnCarrot() {
        double x = 10 + rand.nextDouble() * (width - 20);
        double y = 10 + rand.nextDouble() * (height - 20);
        carrots.add(new Carrot(x, y));
    }

    private void spawnRabbit(BufferedImage rabbitSheet) {
        double x = 20 + rand.nextDouble() * (width - 40);
        double y = 20 + rand.nextDouble() * (height - 40);
        rabbits.add(new Rabbit(x, y, 2.2, rabbitSheet));
    }

    private void rabbitEatCarrots() {
        Iterator<Carrot> itC = carrots.iterator();
        while (itC.hasNext()) {
            Carrot c = itC.next();

            for (Rabbit r : rabbits) {
                if (dist(r.x, r.y, c.x, c.y) < r.radius + c.size / 2.0) {
                    itC.remove();
                    break;
                }
            }
        }
    }

    private void wolfEatRabbits() {
        Iterator<Rabbit> itR = rabbits.iterator();
        while (itR.hasNext()) {
            Rabbit r = itR.next();
            if (dist(wolf.x, wolf.y, r.x, r.y) < wolf.radius + r.radius) {
                itR.remove();
            }
        }
    }

    // Requirement: Fungus eats wolves and carrots
    private void fungusEatCarrotsAndWolf() {
        // Eat carrots near fungus
        Iterator<Carrot> itC = carrots.iterator();
        while (itC.hasNext()) {
            Carrot c = itC.next();
            if (dist(fungus.x, fungus.y, c.x, c.y) < fungus.radius + c.size / 2.0) {
                itC.remove();
            }
        }

        // Eat wolf if wolf touches fungus (simple rule)
        if (dist(fungus.x, fungus.y, wolf.x, wolf.y) < fungus.radius + wolf.radius) {
            // Respawn wolf somewhere else as "eaten" effect
            wolf.x = width * 0.8;
            wolf.y = height * 0.8;
        }
    }

    private double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
