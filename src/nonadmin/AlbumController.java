package nonadmin;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import photo.PhotoController;
import users.Album;
import users.User;
import users.UserDatabase;
import utility.buttonUtility;

/**
 * The Class AlbumController.
 */
public class AlbumController
{
	
	/** The tile pane. */
	private TilePane tilePane;

	/** The album name. */
	/*
	 * fxml objects
	 */
	@FXML
	private Label albumName;

	/** The num photos. */
	@FXML
	private Label numPhotos;

	/** The date created. */
	@FXML
	private Label dateCreated;

	/** The logout. */
	@FXML
	private Button logout;

	/** The quit. */
	@FXML
	private Button quit;

	/** The album display. */
	@FXML
	private ImageView albumDisplay;

	/** The username. */
	@FXML
	private Label username;

	/** The album display pane. */
	@FXML
	private ScrollPane albumDisplayPane;//will contain the thumbnails of photo albums

	private User currentUser;
	/**
	 * sets properties to buttons and a few of the labels
	 * loads list from file.
	 *
	 * @param mainStage the main stage
	 * @param user the user
	 */
	public void start(Stage mainStage, String user) { // Initialization of FXapp
		currentUser = UserDatabase.findUser(user);
		VBox vb = createTilePane(); //will hold images
		fillScrollPane(currentUser.getAlbums()); //add images
		albumDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		albumDisplayPane.setContent(vb); //add images to scrollpane
		username.setText(user); //debug
	}

	/**
	 * Creates the tile pane.
	 *
	 * @return the v box
	 */
	private VBox createTilePane()
	{
		VBox vbox = new VBox(20);

		tilePane = new TilePane();
		tilePane.setHgap(10); //horizontal gap
		tilePane.setVgap(10); //vertical gap
		tilePane.setPadding(new Insets(10)); //padding
		vbox.getChildren().addAll(tilePane);
		VBox.setMargin(vbox, new Insets(10));

		return vbox;
	}

	/**
	 * Fill scroll pane.
	 */
	private void fillScrollPane(ArrayList<Album> albums)
	{
		int imageSize = 128;
		for (Album a : albums)
		{
			Label bt2 = new Label();                        
			Image img2 = new Image(currentUser.getPhoto(a.getPhotos().get(0)).getLocation(),imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			bt2.setText(a.getName());
			bt2.setContentDisplay(ContentDisplay.TOP);
			bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickAlbum());
			tilePane.getChildren().add(bt2);  
		}

	}

	/**
	 * in debugging mode.
	 *
	 * @param e the e
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void quitAct(ActionEvent e) throws IOException
	{
		Stage stage = (Stage) albumDisplay.getScene().getWindow();
		buttonUtility.quit(stage);
	}

	/**
	 * in debugging mode.
	 *
	 * @param e the e
	 * @throws IOException 
	 */
	@FXML
	private void logOutAct(ActionEvent e) throws IOException
	{
		Stage stage = (Stage) albumDisplay.getScene().getWindow();
		buttonUtility.logOut(stage);
	}

	/**
	 * The Class clickAlbum.
	 */
	private class clickAlbum implements EventHandler<MouseEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			Label lbl = (Label) event.getSource();
			albumName.setText(lbl.getText());
			if(event.getClickCount() == 2)
			{
				Stage stage = (Stage) quit.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/photo/photo.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PhotoController controller = loader.getController();
				controller.start(stage, currentUser.searchAlbums(lbl.getText()), currentUser);
				
				stage.setScene(new Scene(root));
				stage.show();
			}
		}
		
	}
}
