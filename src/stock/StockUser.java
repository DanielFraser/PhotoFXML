package stock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import users.Album;
import users.Photo;
import users.User;
import users.UserDatabase;

// TODO: Auto-generated Javadoc
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
			p = new Photo("/stock/"+i+".jpg", LocalDateTime.parse("11/10/2017 15:12:22"));
			p.addCaption(i+"!");
			user.addPhoto(p);
			a.addPhoto(p.getId());
		}
		user.addAlbum(a);
		UserDatabase.addUser(user);
	}
}
