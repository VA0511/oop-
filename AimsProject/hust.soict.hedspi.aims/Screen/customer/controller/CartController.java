package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.NonExistingMediaException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
	
    private Cart cart;
    private Store store;
    private boolean filterByID = true;
    private FilteredList<Media> filteredCart;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;
    
    @FXML
    private Button placeOrderButton;
    
    @FXML
    private Label totalCostLabel;

    @FXML
    void btnPlayPressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	if (media instanceof Playable) {
    		try {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Playing media");
        		alert.setHeaderText("Media infomation: ");
    			alert.setContentText(((Playable) media).getInfo());
        		alert.showAndWait();
    		} catch (PlayerException e) {
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Playing failed");
        		alert.setHeaderText("Error infomation: ");
    			alert.setContentText(e.getMessage());
        		alert.showAndWait();
			}
    	}
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	try {
			cart.removeMedia(media);
		} catch (NonExistingMediaException e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Removing failed");
    		alert.setHeaderText("Error infomation: ");
			alert.setContentText(e.getMessage());
    		alert.showAndWait();
		}
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void placeOrderButtonPressed(ActionEvent event) {
    	if (cart.getItemsOrdered().size() > 0) {
			cart.empty();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notification");
			alert.setHeaderText("Success!");
			alert.setContentText("Your order has been placed.");
			alert.showAndWait();
    	} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Notification");
			alert.setHeaderText("ERROR: Failed to place order.");
			alert.setContentText("Your cart is empty");
			alert.showAndWait();
    	}
    }
    
    public CartController(Store store, Cart cart) {
    	this.store = store;
    	this.cart = cart;
    }
    
    @FXML
    public void initialize() {
    	filteredCart = new FilteredList<Media>(cart.getItemsOrdered(), media -> true);
		ObservableStringValue totalCostString = Bindings.createStringBinding(() -> 
			cart.getTotalCostProperty().get() + " $", cart.getTotalCostProperty());
		totalCostLabel.textProperty().bind(totalCostString);
    	
    	colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));		
    	colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
		colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost")); 
		tblMedia.setItems(filteredCart);
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			updateButtonBar(newValue);
		});
		
		tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			showFilteredMedia(newValue);
		});
	}

	void updateButtonBar(Media media) {
    	if (media == null) {
    		btnPlay.setVisible(false);
    		btnRemove.setVisible(false);
    	} else {
    		btnRemove.setVisible(true);
    		if (media instanceof Playable) {
    			btnPlay.setVisible(true);
    		} else {
    			btnPlay.setVisible(false);
    		}
    	}
    }
	
	private void showFilteredMedia(String filter) {
		if (filter == null || filter.length() == 0) {
			filteredCart.setPredicate(media -> true);
		} else {
			if (filterByID) {
				try {
					System.out.println(Integer.parseInt(filter));
					filteredCart.setPredicate(media -> media.getId() == Integer.parseInt(filter));
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Notification");
					alert.setHeaderText("ERROR: Failed to search by ID");
					alert.setContentText("Please input a number !!!");
					alert.showAndWait();
				}
			} else {
				filteredCart.setPredicate(media -> media.getTitle().toLowerCase().contains(filter));
			}
		}
	}
	
	@FXML
	private void setFilterById() {
		filterByID = true;
	}
	
	@FXML
	private void setFilterByTitle() {
		filterByID = false;
	}
    
}
