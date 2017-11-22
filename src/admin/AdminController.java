package admin;



import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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

/**
 * @author Daniel Fraser
 * @author Peter Laskai
 * 
 * The Class AdminController.
 */
public class AdminController {
	
	//All necessary FXML 

	/** The Create user input. */
	@FXML
	private TextField CreateUserInput;
	
	/** The Username label. */
	@FXML
	private Label UsernameLabel;
	
	/** The Num albums label. */
	@FXML
	private Label NumAlbumsLabel;
	
	/** The User list. */
	@FXML
	private ListView<String> UserList;
	
	/** The observable list. */
	@FXML
	private ObservableList<String> observableList;
	
	/** The Add button. */
	@FXML
	private Button AddButton;
	
	/** The delete button. */
	@FXML
	private Button deleteButton;
	
	/** The Clear button. */
	@FXML
	private Button ClearButton;
	
	/** The Log out button. */
	@FXML
	private Button LogOutButton;
	
	/** The Quit button. */
	@FXML
	private Button QuitButton;
	
	/** The admin stage. */
	public Stage adminStage;
	
	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public void setStage(Stage stage) {
		this.adminStage = stage;
	}
	
	/**
	 * Start. initialization method for the admin screen, called in LoginController if usernameInput is admin
	 *
	 * @param the main stage 
	 * 
	 */
	public void start(Stage MainStage) {
		
		UsernameLabel.setText("");
		NumAlbumsLabel.setText("");
		
		observableList = FXCollections.observableArrayList(UserDatabase.getUsernames()); //gets an Observable list of usernames
		
		Collections.sort(observableList, String.CASE_INSENSITIVE_ORDER); //sorts the observable list alphabetically
		
		UserList.setItems(observableList); //sets the items of the list into the listView
		
		UserList.getSelectionModel().select(0); //selects the first one on the list
		if(observableList.size() > 0)
		{
			setInfo(observableList.get(0));
		}

		
		UserList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> showUserDetails(MainStage)); //shows the details of selected user

		
	}
	
	/**
	 * Show user details.
	 *
	 * @param the main stage 
	 */
	private void showUserDetails(Stage mainStage) {
		
		String user = UserList.getSelectionModel().getSelectedItem();
		setInfo(user);
		
	}
	
	/**
	 * Sets the info. and display it in the right
	 *
	 * @param a string that is the User objects username
	 */
	private void setInfo(String user) {
		
			UsernameLabel.setText(user); //sets the currently selected username as the value in the label
			
			int numberOfAlbums;
			if(UserDatabase.findUser(user) == null) { //used if user was just created whilst admin is working
				numberOfAlbums = 0;
			}
			else
				 numberOfAlbums = UserDatabase.findUser(user).getAlbums().size(); //gets the number of albums
			
			NumAlbumsLabel.setText(String.valueOf(numberOfAlbums)); //converts teh number into a string and displays in label
	   
	}
	
	/**
	 * Adds the user.
	 *
	 * @param e
	 */
	public void addUser(ActionEvent e) {
		
		String username = CreateUserInput.getText();
		
		if(username.isEmpty()) { //check if user is empty
			Alert alert = new Alert(AlertType.ERROR); //creates alert to notify admin
			alert.setTitle("User name is invalid");
			alert.setHeaderText("User name is blank. Please enter a valid username!");
			alert.show();
		} else if(username.contains(" ")) { //no whitespaces allowed for usernames
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("User name is invalid");
			alert.setHeaderText("Cannot create username that contains whitespace. Please enter a valid username!");
			alert.show();
		}
		else if(username.equalsIgnoreCase("admin")) { //cannot create username "admin"
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("User name is invalid");
			alert.setHeaderText("Cannot create username of type admin. Please enter a valid username!");
			alert.show();
		}
		else if(UserDatabase.addUser(username)){ //creates user whilst alos checking if another user of same name exists
			CreateUserInput.setText("");
			observableList.add(username); //adds it to the list 
			Collections.sort(observableList, String.CASE_INSENSITIVE_ORDER); 
		} 

	}
	
	/**
	 * Delete selected user.
	 *
	 * @param e
	 */
	public void deleteSelectedUser(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION); // second-stage confirmation
		alert.setTitle("Confirm Dialog");
		alert.setHeaderText("Are you sure you want to delete this User");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			String username = UsernameLabel.getText(); //gets username from currently selected label
			int index = UserList.getSelectionModel().getSelectedIndex();
			
			int newIndex = 0;
			if(index == 0 && observableList.size() == 1)
			{
				newIndex = -1;
			}
			else if(index == 0 && observableList.size() > 1)
			{
				newIndex = 0;
			}
			else if(index == observableList.size() - 1)
			{
				newIndex = observableList.size() - 2;
			}
			else
				newIndex = index;
			
			UserDatabase.deleteUsername(username); //pases arg to deleteUsername()
			observableList.remove(index); //removes from observable list
			
			UserList.getSelectionModel().select(newIndex);
	
			if(newIndex > -1)
				setInfo(observableList.get(newIndex)); //selects next in list
			
			
			
		}
		
	}
	
	
	/**
	 * Clear.
	 *
	 * @param e
	 */
	public void clear(ActionEvent e) {
		CreateUserInput.setText("");
	}
	
	/**
	 * Admin log out. back to login screen
	 *
	 * @param e the e
	 * @throws IOException ActionEvent
	 */
	public void adminLogOut(ActionEvent e) throws IOException {
		Stage stage = (Stage) LogOutButton.getScene().getWindow();
		buttonUtility.logOut(stage);
		
	}
	
	/**
	 * Admin quit. uses ButtonUtility.quit();
	 *
	 * @param e the e
	 * @throws IOException 
	 */
	public void adminQuit(ActionEvent e) throws IOException {
		
		Stage stage = (Stage) QuitButton.getScene().getWindow();
		buttonUtility.quit(stage);
		
	}
	
	
}
