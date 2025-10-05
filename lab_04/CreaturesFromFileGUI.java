import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CreaturesFromFileGUI extends JFrame implements ActionListener, ListSelectionListener {
    
    private ProcessCreatureFile processor;
    private Creature selectedCreature;
    
    private JList<String> creatureJList;
    private DefaultListModel<String> listModel;
    private JTextField nameField, sizeField, colorField;
    private JTextArea creatureDisplay;
    // New buttons 
    private JButton saveButton, addButton, removeButton; 

    public CreaturesFromFileGUI() {
        super("Program 4: Creatures From File GUI");
        
        // 1. Initialize and Load from File 
        processor = new ProcessCreatureFile();
        try {
            processor.loadCreatures(); 
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error loading creature-data.csv. Starting with empty list.", "File Error", JOptionPane.WARNING_MESSAGE);
        }

        // 2. Setup JList 
        listModel = new DefaultListModel<>();
        refreshJList();
        
        creatureJList = new JList<>(listModel);
        creatureJList.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(creatureJList);

        // 3. Editing 
        JPanel editPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        nameField = new JTextField(); editPanel.add(new JLabel("Name:")); editPanel.add(nameField);
        sizeField = new JTextField(); editPanel.add(new JLabel("Size:")); editPanel.add(sizeField);
        colorField = new JTextField(); editPanel.add(new JLabel("Color:")); editPanel.add(colorField);
        
        // 4. Display Area
        creatureDisplay = new JTextArea(10, 20);
        creatureDisplay.setEditable(false);
        JScrollPane displayScrollPane = new JScrollPane(creatureDisplay);

        // 5. Button Panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveButton = new JButton("Save Edits to File");
        saveButton.addActionListener(this);
        addButton = new JButton("Add New Creature");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(removeButton);

        // 6. Assemble the window
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editPanel, displayScrollPane);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, centerSplit);

        setLayout(new BorderLayout(10, 10));
        add(mainSplit, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initial selection
        if (!processor.getCreatures().isEmpty()) {
            creatureJList.setSelectedIndex(0);
        } else {
            updateCreatureDisplay(null);
        }

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private void refreshJList() {
        listModel.clear();
        for (Creature c : processor.getCreatures()) {
            listModel.addElement(c.getName());
        }
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
            creatureDisplay.setText("No creature selected or available.");
            selectedCreature = null;
        }
    }

    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && creatureJList.getSelectedIndex() != -1) {
            
            selectedCreature = processor.getCreatures().get(creatureJList.getSelectedIndex());
            updateCreatureDisplay(selectedCreature);
        }
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == saveButton) {
                if (selectedCreature == null) return;
                
                //1
                selectedCreature.setName(nameField.getText());
                selectedCreature.setSize(Double.parseDouble(sizeField.getText()));
                selectedCreature.setColor(colorField.getText());
                
                //2
                processor.updateAndSave(); 

                //3
                updateCreatureDisplay(selectedCreature);
                listModel.set(creatureJList.getSelectedIndex(), selectedCreature.getName());
                JOptionPane.showMessageDialog(this, "Creature saved to file!");

            } else if (e.getSource() == addButton) {
                
                String newName = nameField.getText();
                double newSize = Double.parseDouble(sizeField.getText());
                String newColor = colorField.getText();

                processor.addCreature(newName, newSize, newColor);
                
                refreshJList();
                creatureJList.setSelectedIndex(listModel.getSize() - 1); 
                JOptionPane.showMessageDialog(this, "New creature added to file!");

            } else if (e.getSource() == removeButton) {
                if (selectedCreature == null) return;
                
                int selectedIndex = creatureJList.getSelectedIndex();
              
                processor.removeCreature(selectedCreature);
                
                refreshJList();
                
                if (listModel.getSize() > 0) {
                    int nextIndex = Math.min(selectedIndex, listModel.getSize() - 1);
                    creatureJList.setSelectedIndex(nextIndex);
                } else {
                    updateCreatureDisplay(null);
                }
                JOptionPane.showMessageDialog(this, "Creature removed from file!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Size must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreaturesFromFileGUI());
    }
}
