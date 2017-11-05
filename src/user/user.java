package user;

import java.util.ArrayList;

public class user 
{
	private ArrayList<Album> albums = new ArrayList<>();
	private String userName;
	
	public user(String name) 
	{
		setUserName(name);
	}

	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	
	public void deleteAlbum(Album a)
	{
		this.albums.remove(a);
	}
	
	public void addAlbum(Album a)
	{
		albums.add(a);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
