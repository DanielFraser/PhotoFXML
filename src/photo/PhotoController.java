package photo;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import nonadmin.AlbumController;
import users.Album;
import users.Photo;
import users.User;
import utility.buttonUtility;
import utility.customLabel;

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
	private TextArea caption;

	/** The date created. */
	@FXML
	private Label tags;

	/** The prev. */
	@FXML
	private Button prev;

	/** The next. */
	@FXML
	private Button next;

	/** The edit capt. */
	@FXML
	private Button editCapt;

	/** The tile pane. */
	private TilePane tilePane;

	/** The current album. */
	private Album currentAlbum;

	/** The prev P. */
	private int nextP, prevP;

	/** The cur user. */
	private User curUser;

	/** The id. */
	private int id;

	/** The old text. */
	private String oldText;

	/** The edit cap. */
	private boolean editCap = true;
	/**
	 * Start.
	 *
	 * @param mainStage the main stage
	 * @param album the album
	 * @param user the user
	 */
	public void start(Stage mainStage, Album album, User user) 
	{
		caption.setEditable(false);
		currentAlbum = album;
		albumName.setText(album.getName());
		curUser = user;
		fillScrollPane();
		username.setText(user.getUserName());
		caption.setOnKeyPressed(new EventHandler<KeyEvent>() {  
			public void handle(KeyEvent key) {
				if (key.getCode() == KeyCode.ESCAPE) {
					caption.setEditable(false);
					editCap = true;
					caption.setText(oldText);
					editCapt.setText("Edit Caption");
				}
			}
		});
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
		for (Photo p : curUser.getPhoto(currentAlbum))
		{
			customLabel bt2 = new customLabel(p.getCaption(), p.getId());                        
			Image img2 = new Image(p.getLocation(), imageSize, 0, true, false);
			ImageView view2 = new ImageView(img2);
			bt2.setGraphic(view2);
			bt2.setContentDisplay(ContentDisplay.TOP);
			bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickPhoto());
			bt2.setWrapText(true);
			tilePane.getChildren().add(bt2);
			System.out.println(p.getLocation() + " " + p.getId());
		}

		photoDisplayPane.setFitToWidth(true); //prevent horizontal scrolling
		photoDisplayPane.setContent(vb); //add images to scrollpane
	}



	/**
	 * calls the quit function in button utility
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
	 * Next photo.
	 *
	 * @param e the e
	 */
	@FXML
	private void nextPhoto(ActionEvent e)
	{
		if(nextP != -1)
			setInfo(curUser.getPhoto(currentAlbum.getPhotos().get(nextP)));
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
			setInfo(curUser.getPhoto(currentAlbum.getPhotos().get(prevP)));
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
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
			int id = curUser.addPhoto(file.toURI().toString(),  LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()));

			currentAlbum.addPhoto(id);
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
		id = p.getId();
		photoDisplay.setImage(new Image(p.getLocation()));
		tags.setText(p.printTags());
		caption.setText(p.getCaption());
		date.setText(p.getDateS());
		int length = currentAlbum.getPhotos().size();
		int i = currentAlbum.getPhotos().indexOf(p.getId());
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
			customLabel lbl = (customLabel) event.getSource();
			setInfo(curUser.getPhoto(lbl.getIdI()));
		}

	}

	/**
	 * Delete.
	 *
	 * @param e the e
	 */
	@FXML
	private void delete(ActionEvent e)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Deletion");
		alert.setContentText("Are you sure you want to delete this photo?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			currentAlbum.getPhotos().remove(new Integer(id));
			fillScrollPane();
		}
	}

	/**
	 * Move.
	 *
	 * @param e the e
	 */
	@FXML
	private void move(ActionEvent e)
	{
		ArrayList<String> choices = curUser.getAlbumNames();
		choices.remove(currentAlbum.getName());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Move Photo");
		dialog.setContentText("Choose album:");
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
			currentAlbum.getPhotos().remove(new Integer(id));
			Album a = curUser.getAlbum(result.get());
			a.addPhoto(id);
			fillScrollPane();
		}
	}

	/**
	 * Copy.
	 *
	 * @param e the e
	 */
	@FXML
	private void copy(ActionEvent e)
	{
		ArrayList<String> choices = curUser.getAlbumNames();
		choices.remove(currentAlbum.getName());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Copy Photo");
		dialog.setContentText("Choose album:");
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent())
		{
			Album a = curUser.getAlbum(result.get());
			a.addPhoto(id);
		}
	}

	/**
	 * Edits the caption.
	 *
	 * @param e the e
	 */
	@FXML
	private void editCaption(ActionEvent e)
	{
		if(editCap)
		{
			caption.setEditable(true);
			editCap = !editCap;
			editCapt.setText("Save caption");
			oldText = caption.getText();
		}
		else
		{
			curUser.getPhoto(id).addCaption(caption.getText());
			editCap = !editCap;
			editCapt.setText("Edit Caption");
		}
	}

	/**
	 * Adds the tag.
	 *
	 * @param e the e
	 */
	@FXML
	private void addTag(ActionEvent e)
	{
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("add Tag");

		ButtonType addTBtn = new ButtonType("Add tag", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addTBtn, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField type = new TextField();
		type.setPromptText("Enter type");
		TextField value = new TextField();
		value.setPromptText("Enter value");

		grid.add(new Label("Enter type:"), 0, 0);
		grid.add(type, 1, 0);
		grid.add(new Label("Enter value:"), 0, 1);
		grid.add(value, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(addTBtn);
		loginButton.setDisable(true);

		type.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == addTBtn) {
				return new Pair<>(type.getText(), value.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(valueAndType -> {
			curUser.getPhoto(id).addTag(valueAndType.getKey(), valueAndType.getValue());
			fillScrollPane();
		});
	}

	/**
	 * Rm tag.
	 *
	 * @param e the e
	 */
	@FXML
	private void rmTag(ActionEvent e)
	{
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Remove Tag(s)");

		ButtonType rmBtn = new ButtonType("Remove tag", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(rmBtn, new ButtonType("Done", ButtonData.CANCEL_CLOSE));

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Map<String, String> map = curUser.getPhoto(id).getTags();

		TableColumn<Map.Entry<String, String>, String> value = new TableColumn<Map.Entry<String, String>, String>("Value");
		value.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getValue());
			}
		});
		TableColumn<Map.Entry<String, String>, String> type = new TableColumn<Map.Entry<String, String>, String>("Type");
		type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getKey());
			}
		});

		ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
		final TableView<Map.Entry<String,String>> tags = new TableView<>(items);
		tags.setEditable(false);
		tags.getColumns().setAll(value, type);

		grid.add(tags, 0, 0);

		Node rmT = dialog.getDialogPane().lookupButton(rmBtn);
		rmT.disableProperty().bind(Bindings.isEmpty(tags.getSelectionModel().getSelectedItems()));
		rmT.addEventFilter(ActionEvent.ACTION, (event) -> {
			Photo p = curUser.getPhoto(id);
			Entry<String, String> temp = tags.getSelectionModel().getSelectedItem();
			p.removeTag(temp.getKey(), temp.getValue());
			items.remove(temp);
			event.consume(); 
		}); 
		
		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == rmBtn) {
				Photo p = curUser.getPhoto(id);
				Entry<String, String> temp = tags.getSelectionModel().getSelectedItem();
				p.removeTag(temp.getKey(), temp.getValue());
			}
			return null;
		});

		dialog.showAndWait();
		
	}
}
