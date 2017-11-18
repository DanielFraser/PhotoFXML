package login;


import java.io.IOException;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nonadmin.AlbumController;
import users.UserDatabase;
import utility.buttonUtility;

public class LoginController {
	
	@FXML 
	private TextField userNameInput;
	
	@FXML
	private Button LoginButton;
	
	@FXML
	private Button QuitButton;
	
	public void start(Stage mainStage) {
		
	}
	
	public void login(ActionEvent E) throws IOException  {
		
		String username = userNameInput.getText();
		System.out.println(username);

		if(username.equals("admin")) {// User is admin
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(buttonUtility.class.getResource("/admin/admin.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) E.getSource()).getScene().getWindow();
	
		
			AdminController controller = loader.getController();
			controller.start(stage);
			stage.setScene(scene);
			stage.show();
 		}
		else {
 			
 			if(!UserDatabase.findUserB(username)) {
 				UserDatabase.addUser(username);	
 				System.out.println("username debug");
 			}
 			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(buttonUtility.class.getResource("/nonadmin/album.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) E.getSource()).getScene().getWindow();
	
		
			AlbumController controller = loader.getController();
			controller.start(stage, username);
			stage.setScene(scene);
			stage.show();
		}
		
	}

}
