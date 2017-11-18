package admin;



import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import users.User;
import users.UserDatabase;

public class AdminController {

	@FXML
	private TextField CreateUserInput;
	
	@FXML
	private Label UsernameLabel;
	
	@FXML
	private Label NumAlbumsLabel;
	
	@FXML
	private ListView<String> UserList;
	
	@FXML
	private ObservableList<String> observableList;
	
	@FXML
	private Button AddButton;
	
	@FXML
	private Button deleteButton;
	@FXML
	private Button ClearButton;
	
	@FXML
	private Button LogOutButton;
	
	@FXML
	private Button QuitButton;
	
	public Stage adminStage;
	
	public void setStage(Stage stage) {
		this.adminStage = stage;
	}
	
	public void start(Stage MainStage) {
		
//		observableList = FXCollections.observableArrayList(UserDatabase.getUserNames());
//		UserList.setItems(observableList);
		
		
	}
	
	private void showUserDetails(Stage mainStage) {
		
		String user = UserList.getSelectionModel().getSelectedItem();
		setInfo(user);
	}
	
	private void setInfo(String user) {
	   
	}
	
	public void addUser(ActionEvent e) {
		
		String username = CreateUserInput.getText();
		//Add a user in User/UserDatabase
		
	}
	
	public void deleteSelectedUser(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION); 
		alert.setTitle("Confirm Dialog");
		alert.setHeaderText("Are you sure you want to delete this User");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			
		}else {
			
		}
		
	}
	
	public void adminLogOut(ActionEvent e) {
		
	}
	
	public void adminQuit(ActionEvent e) {
		
	}
	
	
}
