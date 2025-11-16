package gui;

import ecosystem.Cell;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private JTextArea info;

    public SidebarPanel() {
        setLayout(new BorderLayout());
        info = new JTextArea();
        info.setEditable(false);
        add(new JScrollPane(info), BorderLayout.CENTER);
    }

    public void displayCell(Cell cell) {
        info.setText(cell.toString());
    }
}
