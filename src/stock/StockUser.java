package stock;

import users.Album;
import users.Photo;
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
	public static void createStockUser()
	{
		User user = new User("stock");
		Album a = new Album("stock");
		Photo p;
		for(int i = 0; i < 10; i++)
		{
			p = new Photo("/stock/"+i+".jpg", "11/10/2017 15:12:22");
			p.addCaption(i+"!");
			a.addPhoto(p);
		}
		user.addAlbum(a);
		UserDatabase.addUser(user);
	}
}
