package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import nonadmin.AlbumController;
import users.Album;
import users.Photo;
import users.User;
import utility.buttonUtility;
import utility.customLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchController.
 */
public class SearchController 
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
	private Label username;
	
	/** The album display pane. */
	@FXML
	private ScrollPane searchDisplayPane;
	
	/** The album name. */
	@FXML
	private Label albumName;
	
	/** The num photos. */
	@FXML
	private Label numPhotos;
	
	/** The date created. */
	@FXML
	private Label dateCreated;
	
	/** The cur user. */
	private User curUser;

	/** The tile pane. */
	private TilePane tilePane;
	
	/** The dp T. */
	@FXML
	private DatePicker dpT;
	
	/** The dp F. */
	@FXML
	private DatePicker dpF;
	
	/** The tags. */
	@FXML
	private TextField tags;
	
	/** The photos. */
	private ArrayList<Integer> newAlbum = new ArrayList<>();
	
	/**
	 * Start.
	 *
	 * @param stage the stage
	 * @param currentUser the current user
	 */
	public void start(Stage stage, User currentUser) {
		curUser = currentUser;
		createTilePane();
		//fillScrollPane();
		username.setText(currentUser.getUserName());
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
		VBox vb = createTilePane();
		int imageSize = 128;
		for (Photo p : photos)
		{
			customLabel bt2 = new customLabel(p.getCaption(), p.getId());                        
			Image img2 = new Image(p.getLocation(), imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			bt2.setContentDisplay(ContentDisplay.TOP);
			bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickPhoto());
			bt2.setWrapText(true);
			tilePane.getChildren().add(bt2);
			newAlbum.add(p.getId());
		}

		searchDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		searchDisplayPane.setContent(vb); //add images to scrollpane
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
	 * The Class clickAlbum.
	 */
	private class clickPhoto implements EventHandler<MouseEvent>{

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			customLabel lbl = (customLabel) event.getSource();
			//setInfo(curUser.getPhoto(lbl.getIdI()));
		}

	}
	
	/**
	 * Search.
	 *
	 * @param e the e
	 */
	@FXML
	private void search(ActionEvent e)
	{
		if(dpT.getValue() != null && dpF.getValue()!= null)
		{
			fillScrollPane(curUser.search(dpF.getValue(), dpT.getValue()));
		}
		else if(!tags.getText().isEmpty())
		{
			ArrayList<Pair<String, String>> photos = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(tags.getText(), ",");
			StringTokenizer st2;
			while(st.hasMoreTokens())
			{
				st2 = new StringTokenizer(st.nextToken(), ":");
				String s1 = st2.nextToken().trim(), s2 = st2.nextToken().trim();
				if(!(s1.equals("*") && s2.equals("*")))
					photos.add(new Pair<String, String>(s1, s2));
			}
			fillScrollPane(curUser.search(photos));
		}
		else
		{
			fillScrollPane(new ArrayList<>());
			System.out.println("Error!");
		}
	}
	
	/**
	 * Home.
	 *
	 * @param e the e
	 */
	@FXML
	private void home(ActionEvent e)
	{
		System.out.println("home");
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
	 * To album.
	 *
	 * @param e the e
	 */
	@FXML
	private void toAlbum(ActionEvent e)
	{
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("New album name");
		dialog.setContentText("Enter name for album:");
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
			boolean b = curUser.sameName(result.get());
			if(!b)
			{
				curUser.addAlbum(new Album(result.get(), newAlbum));
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setContentText("Album name is already in use, please select a different name!");
				
				alert.showAndWait();
			}
		}
		
	}
}
