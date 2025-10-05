import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class HelloGUI extends JFrame implements ActionListener {
    private JLabel greetingLabel;
    private JButton greetButton;
    private String[] greetings = {
        "Hello there!", "Greetings, friend.", "Hi! Have a great day.",
        "Welcome to the GUI world!", "Salutations!"
    };
    private Random random = new Random();

    public HelloGUI() {
        super("Program 1: Hello GUI"); 
        
        
        greetingLabel = new JLabel("Click the button!", SwingConstants.CENTER); 
        
        // Setup the button
        greetButton = new JButton("Greet");
        greetButton.addActionListener(this); 
        
        
        setLayout(new BorderLayout());
        add(greetingLabel, BorderLayout.CENTER);
        add(greetButton, BorderLayout.SOUTH);
        
        
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // when the button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greetButton) {
            int index = random.nextInt(greetings.length);
            greetingLabel.setText(greetings[index]); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HelloGUI());
    }
}
