package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
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
	
	@FXML
	private DatePicker dpT;
	
	@FXML
	private DatePicker dpF;
	
	@FXML
	private TextField tags;
	
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
				photos.add(new Pair<String, String>(st2.nextToken(), st2.nextToken()));
			}
			fillScrollPane(curUser.search((Pair<String, String>[]) photos.toArray()));
		}
		else
		{
			System.out.println("Error!");
		}
	}
}
