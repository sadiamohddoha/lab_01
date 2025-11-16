package gui;

import ecosystem.World;
import ecosystem.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel {
    private World world;
    private JButton[][] buttons;
    private SidebarPanel sidebar;

    public GridPanel(World world, SidebarPanel sidebar) {
        this.world = world;
        this.sidebar = sidebar;

        setLayout(new GridLayout(world.getRows(), world.getCols()));

        buttons = new JButton[world.getRows()][world.getCols()];

        for (int r = 0; r < world.getRows(); r++) {
            for (int c = 0; c < world.getCols(); c++) {
                JButton b = new JButton();
                int rr = r, cc = c;

                b.addActionListener(e -> {
                    Cell cell = world.getCell(rr, cc);
                    sidebar.displayCell(cell);
                });

                buttons[r][c] = b;
                add(b);
            }
        }
    }

    public void refresh() {
      
        for (int r = 0; r < world.getRows(); r++) {
            for (int c = 0; c < world.getCols(); c++) {
                Cell cell = world.getCell(r, c);
                if (cell.getCreatures().size() > 0)
                    buttons[r][c].setBackground(Color.GREEN);
                else
                    buttons[r][c].setBackground(Color.LIGHT_GRAY);
            }
        }
    }
}
