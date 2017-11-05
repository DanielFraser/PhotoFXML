package main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nonadmin.AlbumController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/nonadmin/album.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		
		AlbumController controller = loader.getController();
		controller.start(primaryStage, "aa");
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Non-admin homepage");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		    	//List<Consultation> showing = provider.getItems();
		    	//controller.saveList();
		        Platform.exit();
		        System.exit(0);
		    }
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
