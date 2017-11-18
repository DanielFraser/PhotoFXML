package users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// TODO: Auto-generated Javadoc
/**
 * each user has 0 or more albums
 * each user can will search its own list of albums given a name.
 *
 * @author Daniel Fraser
 */
public class User implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4353768237255205291L;

	/** The albums. */
	private ArrayList<Album> albums = new ArrayList<>();

	/** The user photos. */
	private ArrayList<Photo> userPhotos = new ArrayList<>();
	
	/** The user name. */
	private String userName;

	/**
	 * Instantiates a new user.
	 *
	 * @param name the name
	 */
	public User(String name) 
	{
		userName = name;
	}

	/**
	 * Gets the albums.
	 *
	 * @return the albums
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	/**
	 * Search albums.
	 *
	 * @param name the name
	 * @return the album
	 */
	public Album searchAlbums(String name) {
		Predicate<Album> predicate = c-> c.getName().equals(name);
		Album obj = albums.stream().filter(predicate).findFirst().get();
		return obj;
	}

	/**
	 * Delete album.
	 *
	 * @param name the name
	 */
	public void deleteAlbum(String name)
	{
		Predicate<Album> predicate = c-> c.getName().equals(name);
		Album alb = albums.stream().filter(predicate).findFirst().get();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setContentText("Are you sure you want to delete " + name);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			this.albums.remove(alb);
		} 
		
	}

	/**
	 * Adds the album.
	 *
	 * @param name the name
	 */
	public void addAlbum(String name)
	{
		albums.add(new Album(name, this));
	}
	
	/**
	 * Adds the album.
	 *
	 * @param a the a
	 */
	public void addAlbum(Album a)
	{
		albums.add(a);
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Adds the photo.
	 *
	 * @param p the p
	 */
	public void addPhoto(Photo p)
	{
		userPhotos.add(p);
	}
	
	/**
	 * Adds the photo.
	 *
	 * @param s the s
	 * @param ld the ld
	 * @return the int
	 */
	public int addPhoto(String s, LocalDateTime ld)
	{
		Photo p = new Photo(s, ld);
		userPhotos.add(p);
		return p.getId();
	}
	
	/**
	 * Edits the photo.
	 *
	 * @param i the i
	 * @param p the p
	 */
	public void editPhoto(Integer i, Photo p)
	{
		Predicate<Photo> predicate = c-> c.getId() == i;
		Photo photo = userPhotos.stream().filter(predicate).findFirst().get();
		photo.addCaption(p.getCaption());
		photo.setTags(p.getTags());
	}
	
	/**
	 * Gets the photo.
	 *
	 * @param photoInt the photo int
	 * @return the photo
	 */
	public Photo getPhoto(Integer photoInt)
	{
		for(Photo p : userPhotos)
		{
			if(p.getId() == photoInt)
				return p;
		}
		return null;
	}
	
	/**
	 * Gets the photo.
	 *
	 * @param album the album
	 * @return the photo
	 */
	public ArrayList<Photo> getPhoto(Album album)
	{
		ArrayList<Photo> photos = new ArrayList<>();
		for(int i : album.getPhotos())
			photos.add(getPhoto(i));
		return photos;
	}
	
	/**
	 * Same name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean sameName(String name)
	{
		for(Album a : albums)
		{
			if(a.getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	/**
	 * Sets the album name.
	 *
	 * @param name the name
	 * @param album the album
	 * @return true, if successful
	 */
	public boolean setAlbumName(String name, String album)
	{
		for(Album a : albums)
		{
			if(!sameName(album))
			{
				a.setName(name);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the album.
	 *
	 * @param name the name
	 * @return the album
	 */
	public Album getAlbum(String name)
	{
		for(Album a : albums)
		{
			if(a.getName().equalsIgnoreCase(name))
			{
				return a;
			}
		}
		return null;
	}

	/**
	 * Gets the num photos.
	 *
	 * @return the num photos
	 */
	public int getNumPhotos() {
		return userPhotos.size();
	}
}
