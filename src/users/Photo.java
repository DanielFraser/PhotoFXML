package users;

import java.io.Serializable;
import java.util.HashMap;

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
	public Photo(String initLocation) 
	{
		location = initLocation;
	}
}
