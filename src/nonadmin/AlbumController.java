package nonadmin;

import java.io.IOException;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.buttonUtility;

public class AlbumController
{
	private TilePane tilePane;

	/*
	 * fxml objects
	 */
	@FXML
	private Label albumName;

	@FXML
	private Label numPhotos;

	@FXML
	private Label dateCreated;

	@FXML
	private Button logout;

	@FXML
	private Button quit;

	@FXML
	private ImageView albumDisplay;

	@FXML
	private Label username;

	@FXML
	private ScrollPane albumDisplayPane;//will contain the thumbnails of photo albums


	/**
	 * sets properties to buttons and a few of the labels
	 * loads list from file
	 * 
	 * @param mainStage
	 */
	public void start(Stage mainStage, String user) { // Initialization of FXapp
		//find all user albums
		//get all their thumbnails and names
		VBox vb = createTilePane(); //will hold images
		fillScrollPane(); //add images
		albumDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		albumDisplayPane.setContent(vb); //add images to scrollpane
		username.setText(user); //debug
	}

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

	private void fillScrollPane()
	{
		int imageSize = 128;
		for (int i = 1; i <= 8; i++)
		{
			Label bt2 = new Label();                        
			Image img2 = new Image(AlbumController.class.getResourceAsStream("" + ((i%4) + 1) + ".jpg"), 
					imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			bt2.setText("aaaa");
			bt2.setContentDisplay(ContentDisplay.TOP);
			AnchorPane ap2 = new AnchorPane(bt2);
			//ap.getChildren().add(new Label("aaaa"));
			tilePane.getChildren().add(ap2);  
		}

	}

	/**
	 * in debugging mode
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML
	private void quitAct(ActionEvent e) throws IOException
	{
		Stage stage = (Stage) albumDisplay.getScene().getWindow();
		buttonUtility.logOut(stage);
	}

	/**
	 * in debugging mode
	 * 
	 * @param e
	 */
	@FXML
	private void logOutAct(ActionEvent e)
	{

	}
}
