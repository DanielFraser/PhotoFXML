package search;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utility.buttonUtility;

public class SearchController 
{
	
	/** The logout. */
	@FXML
	private Button logout;

	/** The quit. */
	@FXML
	private Button quit;
	
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
		buttonUtility.logOut(stage);
	}

	/**
	 * in debugging mode.
	 *
	 * @param e the e
	 */
	@FXML
	private void logOutAct(ActionEvent e)
	{

	}
}
