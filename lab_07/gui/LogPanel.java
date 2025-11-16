package gui;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {

    private JTextArea log;

    public LogPanel() {
        setLayout(new BorderLayout());
        log = new JTextArea();
        log.setEditable(false);
        add(new JScrollPane(log), BorderLayout.CENTER);
    }

    public void append(String message) {
        log.append(message + "\n");
    }
}
