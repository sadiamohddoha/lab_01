import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingleCreatureGUI extends JFrame implements ActionListener {
    private Creature creature;
    
    
    private JTextField nameField, sizeField, colorField;
    private JTextArea creatureDisplay;
    
    private JButton saveButton, eatButton;

    public SingleCreatureGUI() {
        super("Program 2: Single Creature GUI");
        
        // 1. Create one Creature to start with
        creature = new Creature("Fuzzy", 5.5, "Blue");
        
        // 2. Editing 
        JPanel editPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        editPanel.add(new JLabel("Name:"));
        nameField = new JTextField(creature.getName());
        editPanel.add(nameField);

        editPanel.add(new JLabel("Size:"));
        sizeField = new JTextField(String.valueOf(creature.getSize()));
        editPanel.add(sizeField);
        
        editPanel.add(new JLabel("Color:"));
        colorField = new JTextField(creature.getColor());
        editPanel.add(colorField);

        // 3. Display 
        creatureDisplay = new JTextArea(creature.toString());
        creatureDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(creatureDisplay);

        // 4. Button Panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save Attributes");
        saveButton.addActionListener(this);
        
        eatButton = new JButton("Eat Food"); 
        eatButton.addActionListener(this);
        
        buttonPanel.add(eatButton);
        buttonPanel.add(saveButton);
        
        // 5. Put everything into the window
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPanel, scrollPane);
        
        setLayout(new BorderLayout(10, 10));
        add(mainSplit, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try {
                
                String newName = nameField.getText();
                double newSize = Double.parseDouble(sizeField.getText());
                String newColor = colorField.getText();

                
                creature.setName(newName);
                creature.setSize(newSize);
                creature.setColor(newColor);

                
                creatureDisplay.setText(creature.toString());
                JOptionPane.showMessageDialog(this, "Creature attributes saved!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Size must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == eatButton) {
            String output = creature.eat("a big steak");
            System.out.println(output); 
            JOptionPane.showMessageDialog(this, output, "Creature Action", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SingleCreatureGUI());
    }
}
