import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOfLife extends JFrame {

    private final int rows = 20;
    private final int cols = 20;
    private boolean[][] grid = new boolean[rows][cols];
    private JButton[][] buttons = new JButton[rows][cols];

    private Timer timer;

    public GameOfLife() {
        super("Game of Life");

        JPanel gridPanel = new JPanel(new GridLayout(rows, cols));

        // Create grid buttons
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JButton b = new JButton();
                b.setBackground(Color.WHITE);

                int rr = r, cc = c;
                b.addActionListener(e -> {
                    grid[rr][cc] = !grid[rr][cc];
                    updateColors();
                });

                buttons[r][c] = b;
                gridPanel.add(b);
            }
        }

        JButton stepButton = new JButton("Step");
        JButton playButton = new JButton("Play");

        stepButton.addActionListener(e -> step());

        playButton.addActionListener(e -> {
            if (timer != null && timer.isRunning()) {
                timer.stop();
                playButton.setText("Play");
            } else {
                timer = new Timer(300, event -> step());
                timer.start();
                playButton.setText("Pause");
            }
        });

        JPanel controls = new JPanel();
        controls.add(stepButton);
        controls.add(playButton);

        setLayout(new BorderLayout());
        add(gridPanel, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateColors() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                buttons[r][c].setBackground(grid[r][c] ? Color.BLACK : Color.WHITE);
            }
        }
    }

    private int countNeighbors(int r, int c) {
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                int nr = r + dr;
                int nc = c + dc;

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (grid[nr][nc]) count++;
                }
            }
        }

        return count;
    }

    public void step() {
        boolean[][] next = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int neighbors = countNeighbors(r, c);

                if (grid[r][c]) {
                    next[r][c] = (neighbors == 2 || neighbors == 3);
                } else {
                    next[r][c] = (neighbors == 3);
                }
            }
        }

        grid = next;
        updateColors();
    }

    public static void main(String[] args) {
        new GameOfLife();
    }
}
