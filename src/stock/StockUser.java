package stock;

import users.Album;
import users.User;
import users.UserDatabase;

/**
 * The Class StockUser.
 */
public class StockUser
{
	
	/**
	 * Creates the stock user.
	 */
	public void createStockUser()
	{
		User user = new User("stock");
		Album a = new Album("stock");
		a.addPhoto(""); //add 10 photos
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		a.addPhoto("");
		user.addAlbum(a);
		UserDatabase.addUser(user);
	}
}
