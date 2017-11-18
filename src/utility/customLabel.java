package utility;

import javafx.scene.control.Label;

// TODO: Auto-generated Javadoc
/**
 * The Class customLabel.
 */
public class customLabel extends Label
{
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new custom label.
	 *
	 * @param s the s
	 * @param a the a
	 */
	public customLabel(String s, int newId) 
	{
		super(s);
		id = newId;
	}
	
	/**
	 * Gets the id I.
	 *
	 * @return the id I
	 */
	public int getIdI() 
	{
		return id;
	}
}
