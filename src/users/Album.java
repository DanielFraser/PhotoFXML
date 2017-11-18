package users;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


// TODO: Auto-generated Javadoc
/**
 * The Class Album.
 */
public class Album implements Serializable 
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8132144142964125790L;

	/** The photos. */
	private ArrayList<Integer> photos = new ArrayList<>();
	
	/** The name. */
	private String name;
	
	/** The date format. */
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/** The cal. */
	private Calendar cal;
	
	/** The date created. */
	private Date dateCreated;
	
	User owner;
	/**
	 * Instantiates a new album.
	 *
	 * @param initName the init name
	 */
	public Album(String initName, User initOwner) {
		setName(initName);
		cal = Calendar.getInstance();
		owner = initOwner;
	}
	
	
	/**
	 * Instantiates a new album.
	 *
	 * @param initName the init name
	 * @param initPhotos the init photos
	 */
	public Album(String initName,  ArrayList<Integer> initPhotos) {
		setName(initName);
		photos = initPhotos;
	}
	
	/**
	 * Gets the photos.
	 *
	 * @return the photos
	 */
	public ArrayList<Integer> getPhotos() {
		return photos;
	}

	/**
	 * Sets the photos.
	 *
	 * @param photos the new photos
	 */
	public void setPhotos(ArrayList<Integer> photos) {
		this.photos = photos;
	}
	
	/**
	 * Song's equal method.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Album))
			return false;
		else
		{
			Album newAlbum = (Album) obj;
			if(getName().equalsIgnoreCase(newAlbum.getName()))
				return true;
			return false;
		}
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) 
	{
		if(owner != null)
		{
			if(!owner.sameName(name))
				this.name = name;
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Album name already exists");
				alert.showAndWait();
			}
		}
		this.name = name;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size of photo album
	 */
	public int getSize()
	{
		return photos.size();
	}


	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public String getDateCreated() {
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * Adds the photo.
	 *
	 * @param i the i
	 */
	public void addPhoto(Integer i)
	{
		photos.add(i);
	}
}
