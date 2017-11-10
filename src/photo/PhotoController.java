package photo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import nonadmin.AlbumController;
import users.Album;
import users.Photo;
import utility.buttonUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class PhotoController.
 */
public class PhotoController 
{
	/** The logout. */
	@FXML
	private Button logout;

	/** The quit. */
	@FXML
	private Button quit;
	
	/** The album display. */
	@FXML
	ImageView photoDisplay;
	
	/** The add photo. */
	@FXML
	private Button addPhoto;

	/** The quit. */
	@FXML
	private Button home;
	
	/** The username. */
	@FXML
	private Label albumName;
	
	/** The quit. */
	@FXML
	private ScrollPane photoDisplayPane;
	
	/** The username. */
	@FXML
	private Label username;
	
	/** The num photos. */
	@FXML
	private Label numPhotos;
	
	/** The date created. */
	@FXML
	private Label dateCreated;

	/** The tile pane. */
	private TilePane tilePane;
	
	private Album currentAlbum;
	
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @param album the album
	 */
	public void start(Stage mainStage, Album album) 
	{
		currentAlbum = album;
		VBox vb = createTilePane();
		fillScrollPane(album.getPhotos());
		photoDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		photoDisplayPane.setContent(vb); //add images to scrollpane
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
	 *
	 * @param photos the photos
	 */
	private void fillScrollPane(ArrayList<Photo> photos)
	{
		int imageSize = 128;
		for (Photo p : photos)
		{
			Label bt2 = new Label();                        
			Image img2 = new Image(AlbumController.class.getResourceAsStream(p.getLocation()), 
					imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			bt2.setText(p.getName());
			bt2.setContentDisplay(ContentDisplay.TOP);
			bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickPhoto());
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
		Stage stage = (Stage) quit.getScene().getWindow();
		buttonUtility.quit(stage);
	}

	/**
	 * in debugging mode.
	 *
	 * @param e the e
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void logOutAct(ActionEvent e) throws IOException
	{
		Stage stage = (Stage) quit.getScene().getWindow();
		buttonUtility.logOut(stage);
	}
	
	/**
	 * Adds the photo.
	 *
	 * @param e the e
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void addPhoto(ActionEvent e) throws IOException
	{
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int selection = fc.showOpenDialog(fc);
		
		if (selection == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            photoDisplay.setImage(new Image(file.toURI().toString()));
            System.out.println(file);
        }
	}
	
	/**
	 * The Class clickAlbum.
	 */
	private class clickPhoto implements EventHandler<MouseEvent>{
		
		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			Label lbl = (Label) event.getSource();
			albumName.setText(lbl.getText());
			photoDisplay.setImage(new Image(currentAlbum.findPhoto(lbl.getText())));
		}
		
	}
}
