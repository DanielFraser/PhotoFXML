package photo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nonadmin.AlbumController;
import users.Album;
import users.Photo;
import users.User;
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
	
	/** The date. */
	@FXML
	private Label date;
	
	/** The num photos. */
	@FXML
	private Label caption;
	
	/** The date created. */
	@FXML
	private Label tags;

	/** The prev. */
	@FXML
	private Button prev;
	
	/** The next. */
	@FXML
	private Button next;
	
	/** The tile pane. */
	private TilePane tilePane;
	
	/** The current album. */
	private Album currentAlbum;
	
	/** The prev P. */
	private int nextP, prevP;
	private User curUser;
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @param album the album
	 */
	public void start(Stage mainStage, Album album, User user) 
	{
		currentAlbum = album;
		curUser = user;
		fillScrollPane();
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
	private void fillScrollPane()
	{
		VBox vb = createTilePane();
		int imageSize = 128;
		for (Photo p : currentAlbum.getPhotos())
		{
			Label bt2 = new Label();                        
			Image img2 = new Image(p.getLocation(), 
					imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			//bt2.setText(p.);
			bt2.setContentDisplay(ContentDisplay.TOP);
			bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickPhoto());
			bt2.setWrapText(true);
			//AnchorPane ap = new AnchorPane(bt2);
			tilePane.getChildren().add(bt2);  
		}
		
		photoDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		photoDisplayPane.setContent(vb); //add images to scrollpane

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
	 * Home.
	 *
	 * @param e the e
	 */
	@FXML
	private void home(ActionEvent e)
	{
		Stage stage = (Stage) quit.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/nonadmin/album.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		AlbumController controller = loader.getController();
		controller.start(stage, curUser.getUserName());
		
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	/**
	 * Next photo.
	 *
	 * @param e the e
	 */
	@FXML
	private void nextPhoto(ActionEvent e)
	{
		if(nextP != -1)
			setInfo(currentAlbum.getPhotos().get(nextP));
	}
	
	/**
	 * Prev photo.
	 *
	 * @param e the e
	 */
	@FXML
	private void prevPhoto(ActionEvent e)
	{
		if(prevP != -1)
			setInfo(currentAlbum.getPhotos().get(prevP));
	}
	
	/**
	 * Adds the photo.
	 *
	 * @param e the e
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	@FXML
	private void addPhoto(ActionEvent e) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int selection = fc.showOpenDialog(fc);
		
		if (selection == JFileChooser.APPROVE_OPTION) 
		{
            File file = fc.getSelectedFile();
            photoDisplay.setImage(new Image(file.toURI().toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            currentAlbum.addPhoto(file.toURI().toString(), sdf.format(file.lastModified()));
            
            fillScrollPane();
        }
	}
	
	/**
	 * Sets the info.
	 *
	 * @param p the new info
	 */
	private void setInfo(Photo p)
	{
		photoDisplay.setImage(new Image(p.getLocation()));
		tags.setText(p.printTags());
		caption.setText(p.getCaption());
		date.setText(p.getDate());
		int length = currentAlbum.getPhotos().size();
		int i = currentAlbum.getPhotos().indexOf(p);
		prevP = -1;
		nextP = -1;
		if(i > 0)
			prevP = i-1;
		if(i < length - 1)
			nextP = i+1;
	}
	
//	Stage secondStage = new Stage();
//  secondStage.setScene(new Scene(root));
//  secondStage.show();
	
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
			setInfo(currentAlbum.findPhoto(lbl.getText()));
		}
		
	}
}
