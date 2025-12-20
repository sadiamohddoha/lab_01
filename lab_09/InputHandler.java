import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        // Inverted controls required by lab:
        // Left arrow moves right, Right arrow moves left
        // Up arrow moves down, Down arrow moves up
        if (k == KeyEvent.VK_LEFT) right = true;
        if (k == KeyEvent.VK_RIGHT) left = true;
        if (k == KeyEvent.VK_UP) down = true;
        if (k == KeyEvent.VK_DOWN) up = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();

        if (k == KeyEvent.VK_LEFT) right = false;
        if (k == KeyEvent.VK_RIGHT) left = false;
        if (k == KeyEvent.VK_UP) down = false;
        if (k == KeyEvent.VK_DOWN) up = false;
    }
}
