import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MultipleCreatureGUI extends JFrame implements ActionListener, ListSelectionListener {
    private ArrayList<Creature> creatureList;
    private Creature selectedCreature; 
    
    private JList<String> creatureJList; 
    private DefaultListModel<String> listModel; 
    private JTextField nameField, sizeField, colorField;
    private JTextArea creatureDisplay;
    private JButton saveButton;

    public MultipleCreatureGUI() {
        super("Program 3: Multiple Creature GUI");
        
        // 1. Create a few creatures to start
        creatureList = new ArrayList<>();
        creatureList.add(new Creature("Griffin", 12.0, "Gold"));
        creatureList.add(new Creature("Hydra", 30.0, "Red"));
        creatureList.add(new Creature("Imp", 1.0, "Purple"));

        // 2. Setup JList Panel (Far Left)
        listModel = new DefaultListModel<>();
        for (Creature c : creatureList) {
            listModel.addElement(c.getName());
        }
        creatureJList = new JList<>(listModel);
        creatureJList.addListSelectionListener(this); 
        JScrollPane listScrollPane = new JScrollPane(creatureJList);

        // 3. Editing 
        JPanel editPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        nameField = new JTextField(); editPanel.add(new JLabel("Name:")); editPanel.add(nameField);
        sizeField = new JTextField(); editPanel.add(new JLabel("Size:")); editPanel.add(sizeField);
        colorField = new JTextField(); editPanel.add(new JLabel("Color:")); editPanel.add(colorField);
        
        // 4. Display
        creatureDisplay = new JTextArea(10, 20);
        creatureDisplay.setEditable(false);
        JScrollPane displayScrollPane = new JScrollPane(creatureDisplay);

        // 5. Button Panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save Attributes");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        // 6.  List, Edit Fields, Display Box
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPanel, displayScrollPane);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, centerSplit);

        setLayout(new BorderLayout(10, 10));
        add(mainSplit, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        
        if (!creatureList.isEmpty()) {
            creatureJList.setSelectedIndex(0);
        }

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private void updateCreatureDisplay(Creature c) {
        if (c != null) {
            nameField.setText(c.getName());
            sizeField.setText(String.valueOf(c.getSize()));
            colorField.setText(c.getColor());
            creatureDisplay.setText(c.toString());
        } else { 
            nameField.setText("");
            sizeField.setText("");
            colorField.setText("");
            creatureDisplay.setText("No creature selected.");
        }
    }

    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && creatureJList.getSelectedIndex() != -1) {
            
            selectedCreature = creatureList.get(creatureJList.getSelectedIndex());
            updateCreatureDisplay(selectedCreature);
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedCreature == null) return;

        if (e.getSource() == saveButton) {
            try {
                
                selectedCreature.setName(nameField.getText());
                selectedCreature.setSize(Double.parseDouble(sizeField.getText()));
                selectedCreature.setColor(colorField.getText());

                // Refresh the displays
                updateCreatureDisplay(selectedCreature);
                listModel.set(creatureJList.getSelectedIndex(), selectedCreature.getName()); 
                JOptionPane.showMessageDialog(this, "Creature saved in memory!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Size must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultipleCreatureGUI());
    }
}
