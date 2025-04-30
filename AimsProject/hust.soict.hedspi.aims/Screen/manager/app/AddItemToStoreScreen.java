package hust.soict.hedspi.aims.screen.manager.app;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField titleField;
    protected JTextField costField;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setLayout(new BorderLayout());
        
        // Add the menu bar
        add(createMenuBar(), BorderLayout.NORTH);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Common fields
        mainPanel.add(new JLabel("Title:"));
        titleField = new JTextField(20);
        mainPanel.add(titleField);
        
        mainPanel.add(new JLabel("Cost:"));
        costField = new JTextField(20);
        mainPanel.add(costField);
        
        // Add specific fields
        addSpecificFields(mainPanel);
        
        // Add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItem());
        
        // Layout
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(addButton, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
        
        setTitle("Add Item");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
    
    protected abstract void addSpecificFields(JPanel panel);
    protected abstract void addItem();
    
    protected JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose();
        });
        menu.add(viewStore);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }
}
