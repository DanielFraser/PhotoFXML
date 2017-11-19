package users;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// TODO: Auto-generated Javadoc
/**
 * The Class Photo.
 */
public class Photo implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2788753881453693638L;

	/** The caption. */
	private String location = "", caption;
	
	/** The date created. */
	private LocalDateTime dateCreated;

	/** The tags. */
	private HashMap<String, String> tags = new HashMap<>();
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new photo.
	 *
	 * @param initLocation the init location
	 * @param date the date
	 */
	public Photo(String initLocation, LocalDateTime date, int userID) 
	{
		id = userID;
		location = initLocation;
		dateCreated = date;
	}
	
	/**
	 * Adds the tag.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addTag(String key, String value) 
	{
		if(!tags.containsKey(key))
			tags.put(key, value);
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setContentText("Sorry, tag already exists");

			alert.showAndWait();
		}
	}

	/**
	 * Adds the caption.
	 *
	 * @param initCaption the init caption
	 */
	public void addCaption(String initCaption)
	{
		caption = initCaption;
	}

	/**
	 * Gets the caption.
	 *
	 * @return the caption
	 */
	public String getCaption()
	{
		return caption;
	}

	/**
	 * Removes the tag.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void removeTag(String key, String value) 
	{
		tags.remove(key, value);
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		File f = new File(location);
		return f.getName();
	}

	/**
	 * Prints the tags.
	 *
	 * @return the string
	 */
	public String printTags()
	{
		String s = "";
		for (Entry<String,String> pair : tags.entrySet())
		{
			  s += pair.getKey()+": "+pair.getValue() + "\n";
		}
		return s;
	}

	/**
	 * Gets the date.
	 *
	 * @return the created
	 */
	public LocalDateTime getDate()
	{
		return dateCreated;
	}
	
	/**
	 * Gets the date S.
	 *
	 * @return the date S
	 */
	public String getDateS()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateCreated.format(formatter);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the tags.
	 *
	 * @param hm the hm
	 */
	public void setTags(HashMap<String, String> hm)
	{
		tags = hm;
	}
	
	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public HashMap<String, String> getTags()
	{
		return tags;
	}
	
}
