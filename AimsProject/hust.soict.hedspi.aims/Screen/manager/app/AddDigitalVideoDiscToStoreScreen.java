package hust.soict.hedspi.aims.screen.manager.app;

import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField directorField;
    
    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
    }
    
    @Override
    protected void addSpecificFields(JPanel panel) {
        panel.add(new JLabel("Director:"));
        directorField = new JTextField(20);
        panel.add(directorField);
    }
    
    @Override
    protected void addItem() {
        DigitalVideoDisc dvd = new DigitalVideoDisc(
            titleField.getText(),
            Double.parseDouble(costField.getText()),
            directorField.getText()
        );
        store.addItem(dvd);
        JOptionPane.showMessageDialog(this, "DVD added successfully!");
        new StoreManagerScreen(store);
        dispose();
    }
}
