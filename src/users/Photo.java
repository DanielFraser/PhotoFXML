package users;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class Photo.
 */
public class Photo implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2788753881453693638L;

	/** The caption. */
	private String location = "", created, caption;

	/** The tags. */
	private HashMap<String, String> tags = new HashMap<>();

	/**
	 * Instantiates a new photo.
	 *
	 * @param initLocation the init location
	 */
	public Photo(String initLocation, String date) 
	{
		location = initLocation;
		created = date;
	}
	
	/**
	 * Adds the tag.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addTag(String key, String value) 
	{
		tags.put(key, value);
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
			  s += pair.getKey()+": "+pair.getValue();
		}
		return s;
	}

	/**
	 * @return the created
	 */
	public String getDate()
	{
		return created;
	}
}
