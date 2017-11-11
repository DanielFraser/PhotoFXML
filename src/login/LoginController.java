package login;


import java.io.IOException;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.buttonUtility;

public class LoginController {
	
	@FXML 
	private TextField userNameInput;
	
	@FXML
	private Button LoginButton;
	
	@FXML
	private Button QuitButton;
	
	public void start(Stage mainStage) {
		userNameInput.setText("test");
	}
	
	public void login(ActionEvent E) throws IOException  {
		
		String username = userNameInput.getText();
		buttonUtility.logIn(username);
		
	}

}
