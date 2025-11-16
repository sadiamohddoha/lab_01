package gui;

import ecosystem.World;

import javax.swing.*;
import java.awt.*;

public class EcosystemGUI extends JFrame {

    private World world;
    private GridPanel grid;
    private SidebarPanel sidebar;
    private LogPanel log;

    public EcosystemGUI() {
        super("Ecosystem Simulation");

        world = new World("initial_state.txt");

        sidebar = new SidebarPanel();
        log = new LogPanel();
        grid = new GridPanel(world, sidebar);

        JButton stepBtn = new JButton("Step");
        JButton playBtn = new JButton("Play");
        JButton resetBtn = new JButton("Reset");

        stepBtn.addActionListener(e -> doTurn());
        resetBtn.addActionListener(e -> resetWorld());

        JPanel controls = new JPanel();
        controls.add(stepBtn);
        controls.add(playBtn);
        controls.add(resetBtn);

        setLayout(new BorderLayout());
        add(grid, BorderLayout.CENTER);
        add(sidebar, BorderLayout.EAST);
        add(log, BorderLayout.SOUTH);
        add(controls, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);

        grid.refresh();
    }

    public void doTurn() {
        world.takeTurn();
        log.append("Turn taken");
        grid.refresh();
    }

    public void resetWorld() {
        world = new World("initial_state.txt");
        grid.refresh();
        log.append("World reset");
    }

    public static void main(String[] args) {
        new EcosystemGUI();
    }
}
