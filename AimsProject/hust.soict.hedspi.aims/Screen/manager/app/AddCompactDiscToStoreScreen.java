package hust.soict.hedspi.aims.screen.manager.app;

import javax.swing.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField artistField;
    
    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }
    
    @Override
    protected void addSpecificFields(JPanel panel) {
        panel.add(new JLabel("Artist:"));
        artistField = new JTextField(20);
        panel.add(artistField);
    }
    
    @Override
    protected void addItem() {
        CompactDisc cd = new CompactDisc(
            titleField.getText(),
            Double.parseDouble(costField.getText()),
            artistField.getText()
        );
        store.addItem(cd);
        JOptionPane.showMessageDialog(this, "CD added successfully!");
        new StoreManagerScreen(store);
        dispose();
    }
}
