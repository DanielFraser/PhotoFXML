package users;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * each user has 0 or more albums
 * each user can will search its own list of albums given a name.
 *
 * @author Daniel Fraser
 */
public class user implements Serializable
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
	public user(String name) 
	{
		setUserName(name);
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
		return albums.get(albums.indexOf(name));
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
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
