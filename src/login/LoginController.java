package login;


import java.io.IOException;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import nonadmin.AlbumController;
import users.UserDatabase;
import utility.buttonUtility;

/**
 * @author Daniel Fraser
 * @author Peter Laskai
 * 
 * The Class LoginController.
 */
public class LoginController {
	
	/** The user name input. */
	@FXML 
	private TextField userNameInput; //input where user enters their user name credentials 
	
	/** The Login button. */
	@FXML
	private Button LoginButton; //Button to login and progress
	
	/** The Quit button. */
	@FXML
	private Button QuitButton; //Button to exit program
	
	/**
	 * Start.
	 *
	 * @param mainStage start called in main, since its the initial screen in the program, also contains key event for ENTER
	 */
	public void start(Stage mainStage) {
		userNameInput.setOnKeyPressed(new EventHandler<KeyEvent>() {  
			public void handle(KeyEvent key) {
				if (key.getCode() == KeyCode.ENTER) {
					LoginButton.fire();
				}
			}
		});
	}
	
	/**
	 * Login.
	 *
	 * @param E the e
	 * @throws IOException logins into application, checks the value to determine the path (admin vs non-admin)
	 */
	public void login(ActionEvent E) throws IOException  {
		
		String username = userNameInput.getText();
		System.out.println(username);

		if(username.equals("admin")) {// User is admin
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(buttonUtility.class.getResource("/admin/admin.fxml")); //gets the admin fxml and sets it to load there
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) E.getSource()).getScene().getWindow();
	
		
			AdminController controller = loader.getController();
			controller.start(stage); // calls AdminController.start()
			stage.setScene(scene);
			stage.show(); // shows admin screen
 		}
		else if(!UserDatabase.findUserB(username)) { //Username doesnt not exist
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("User does not exist");
			alert.setHeaderText("User does not appear to be in our system, contact admin to create a user for you!");
			alert.show();
			userNameInput.setText("");
				
		} else { //Username exists and not admin
 			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(buttonUtility.class.getResource("/nonadmin/album.fxml")); //gets the non-admin fxml and sets it to load there
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) E.getSource()).getScene().getWindow(); 
	
		
			AlbumController controller = loader.getController();
			controller.start(stage, username); //starts album controller(calls AlbumController's start() method)
			stage.setScene(scene);
			stage.show();  // shows non-admin window
		}
		
	}
	
	/**
	 * Quit.
	 *
	 * @param E the e
	 * @throws IOException quit from login, using quitButton
	 */
	public void quit(ActionEvent E) throws IOException {
		Stage stage = (Stage) QuitButton.getScene().getWindow();
		buttonUtility.quit(stage);
	}

}
