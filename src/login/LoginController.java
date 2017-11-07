package login;

import java.awt.TextField;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import utility.buttonUtility;

public class LoginController {
	
	@FXML 
	private TextField userNameInput;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Button quitButton;
	
	public void start(Stage mainStage) {
		
	}
	
	public void login() {
		
		String username = userNameInput.getText();
		
		if(username == "admin") {
			
		}
		else {
			
		}
	}

	public LoginController() {
		
		// TODO Auto-generated constructor stub
	}

}
