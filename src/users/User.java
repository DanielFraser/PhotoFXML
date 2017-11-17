package users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

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
		this.albums.remove(alb);
	}

	/**
	 * Adds the album.
	 *
	 * @param name the name
	 */
	public void addAlbum(String name)
	{
		albums.add(new Album(name));
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
	 */
	public void addPhoto(String s, LocalDateTime ld)
	{
		userPhotos.add(new Photo(s, ld));
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
		return userPhotos.get(photoInt);
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
}
