package admin;



import java.io.IOException;
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
import utility.buttonUtility;

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
		
		UsernameLabel.setText("");
		NumAlbumsLabel.setText("");
		
		observableList = FXCollections.observableArrayList(UserDatabase.getUsernames());
		UserList.setItems(observableList);
		
		UserList.getSelectionModel().select(0);
		if(observableList.size() > 0)
		{
			setInfo(observableList.get(0));
		}

		
		UserList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> showUserDetails(MainStage));

		
	}
	
	private void showUserDetails(Stage mainStage) {
		
		String user = UserList.getSelectionModel().getSelectedItem();
		setInfo(user);
		
	}
	
	private void setInfo(String user) {
		
			UsernameLabel.setText(user);
	   
	}
	
	public void addUser(ActionEvent e) {
		
		String username = CreateUserInput.getText();
		
		if(username.isEmpty()) {
			System.out.println("Username is blank");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("User name is invalid");
			alert.setHeaderText("User name is blank. Please enter a valid username!");
			alert.show();
		}
		else if(UserDatabase.addUser(username)){
			CreateUserInput.setText("");
			observableList.add(username);
		} 
//		else
//		{
////			UserDatabase.addUser(username);
//			UserDatabase.saveUsernames();
//			CreateUserInput.setText("");
//			observableList.add(username);
//		}
		//Add a user in User/UserDatabase
		
	}
	
	public void deleteSelectedUser(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION); 
		alert.setTitle("Confirm Dialog");
		alert.setHeaderText("Are you sure you want to delete this User");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			String username = UsernameLabel.getText();
			int index = UserList.getSelectionModel().getSelectedIndex();
			UserDatabase.deleteUsername(username);
			observableList.remove(index);
			
		}
		
	}
	
	public void clear(ActionEvent e) {
		CreateUserInput.setText("");
	}
	
	public void adminLogOut(ActionEvent e) throws IOException {
		Stage stage = (Stage) LogOutButton.getScene().getWindow();
		buttonUtility.logOut(stage);
		
	}
	
	public void adminQuit(ActionEvent e) throws IOException {
		
		Stage stage = (Stage) QuitButton.getScene().getWindow();
		buttonUtility.quit(stage);
		
	}
	
	
}
