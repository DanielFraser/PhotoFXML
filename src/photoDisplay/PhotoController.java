package photoDisplay;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
	ImageView albumDisplay;
	
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
	private ScrollPane albumDisplayPane;
	
	/** The username. */
	@FXML
	private Label username;
	
	/** The num photos. */
	@FXML
	private Label numPhotos;
	
	/** The date created. */
	@FXML
	private Label dateCreated;
	
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
            albumDisplay.setImage(new Image(file.toURI().toString()));
            System.out.println(file);
        }
	}
}
