package hust.soict.hedspi.aims.screen.manager.app;

import javax.swing.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField authorField;
    
    public AddBookToStoreScreen(Store store) {
        super(store);
    }
    
    @Override
    protected void addSpecificFields(JPanel panel) {
        panel.add(new JLabel("Author:"));
        authorField = new JTextField(20);
        panel.add(authorField);
    }
    
    @Override
    protected void addItem() {
        Book book = new Book(
            titleField.getText(),
            Double.parseDouble(costField.getText()),
            authorField.getText()
        );
        store.addItem(book);
        JOptionPane.showMessageDialog(this, "Book added successfully!");
        new StoreManagerScreen(store);
        dispose();
    }
}
