package users;

import java.io.Serializable;
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
	 * Sets the albums.
	 *
	 * @param albums the new albums
	 */
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	/**
	 * Delete album.
	 *
	 * @param a the a
	 */
	public void deleteAlbum(Album a)
	{
		this.albums.remove(a);
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
	 * @param name the name
	 */
	public void addAlbum(ArrayList<Photo> a, String name)
	{
		albums.add(new Album(name, a));
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
}
