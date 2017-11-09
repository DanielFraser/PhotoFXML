package utility;

import java.io.IOException;

import drawing.drawingController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nonadmin.AlbumController;

public class buttonUtility
{
	/*
	 * creates method for all guis to log out and to safely quit
	 */
	
	/**
	 * edit to login screen when done
	 * 
	 * @param stage
	 * @param root
	 * @throws IOException
	 */
	public static void logOut(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(buttonUtility.class.getResource("/drawing/drawing.fxml"));
		Parent root = loader.load();

		drawingController controller = loader.getController();
		controller.start(stage);
		
		//create a new scene with root and set the stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * have someone implement save on quit
	 * 
	 * @param stage
	 * @param root
	 * @throws IOException
	 */
	public static void quit(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(buttonUtility.class.getResource("/photoDisplay/photo.fxml"));
		Parent root = loader.load();
		
		//create a new scene with root and set the stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
