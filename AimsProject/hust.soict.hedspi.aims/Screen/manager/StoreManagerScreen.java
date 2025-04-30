package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

interface Playable {}

abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField titleField;
    protected JTextField costField;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setLayout(new BorderLayout());
        
        add(createMenuBar(), BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(new JLabel("Title:"));
        titleField = new JTextField(20);
        mainPanel.add(titleField);
        
        mainPanel.add(new JLabel("Cost:"));
        costField = new JTextField(20);
        mainPanel.add(costField);
        
        addSpecificFields(mainPanel);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addItem());
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(addButton, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
        
        setTitle("Add Item");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
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

class AddBookToStoreScreen extends AddItemToStoreScreen {
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
        try {
            Book book = new Book(
                titleField.getText(),
                Double.parseDouble(costField.getText()),
                authorField.getText()
            );
            store.addItem(book);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            new StoreManagerScreen(store);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
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
        try {
            CompactDisc cd = new CompactDisc(
                titleField.getText(),
                Double.parseDouble(costField.getText()),
                artistField.getText()
            );
            store.addItem(cd);
            JOptionPane.showMessageDialog(this, "CD added successfully!");
            new StoreManagerScreen(store);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
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
        try {
            DigitalVideoDisc dvd = new DigitalVideoDisc(
                titleField.getText(),
                Double.parseDouble(costField.getText()),
                directorField.getText()
            );
            store.addItem(dvd);
            JOptionPane.showMessageDialog(this, "DVD added successfully!");
            new StoreManagerScreen(store);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class StoreManagerScreen extends JFrame {
    private Store store;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            new StoreManagerScreen(store);
            dispose();
        });
        menu.add(viewStore);
        
        JMenu smUpdateStore = new JMenu("Update Store");
        
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            dispose();
        });
        
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            dispose();
        });
        
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            dispose();
        });
        
        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < 9; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getName());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getClass() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                JDialog playDialog = new JDialog();
                playDialog.setTitle("Playing " + media.getName());
                
                JPanel content = new JPanel();
                content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                content.add(new JLabel("Now playing: " + media.getName()));
                
                playDialog.add(content);
                playDialog.setSize(300, 150);
                playDialog.setLocationRelativeTo(null);
                playDialog.setModal(true);
                playDialog.setVisible(true);
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}

