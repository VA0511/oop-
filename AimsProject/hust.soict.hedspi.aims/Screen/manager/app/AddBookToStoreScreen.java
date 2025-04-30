package hust.soict.dsai.aims.screen.manager.app;

import javax.swing.JFrame;

import hust.soict.dsai.aims.screen.manager.controller.AddBookToStoreController;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddBookToStoreApp extends AddItemToStoreApp {
	public AddBookToStoreApp(Store store) {
		super(store);
		
		JFrame frame = this;
		JFXPanel panel = new JFXPanel();
		this.add(panel);
		this.setTitle("Add Book To Store");
		this.setVisible(true);
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/manager/view/AddBookToStoreScreen.fxml"));
					AddBookToStoreController addBookController = new AddBookToStoreController(store, frame);
					loader.setController(addBookController);
					Parent root = loader.load();
					panel.setScene(new Scene(root));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
