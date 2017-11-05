package user;

import java.util.ArrayList;

public class Album 
{
	private ArrayList<Photo> photos = new ArrayList<>();

	public ArrayList<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}
	
}
