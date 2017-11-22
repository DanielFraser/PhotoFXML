package stock;

import java.time.LocalDateTime;

import users.Album;
import users.Photo;
import users.User;
import users.UserDatabase;

/**
 * @author Daniel Fraser
 * @author Peter Laskai
 * 
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
		Album a = new Album("stock", user);
		Photo p;
		int id;
		for(int i = 0; i < 10; i++)
		{
			id = user.addPhoto("/stock/"+i+".jpg",LocalDateTime.parse("2017-11-10T15:12:22"));
			a.addPhoto(id);
			p = user.getPhoto(id);
			p.addCaption("cake is delicous");
		}
		user.addAlbum(a);
		UserDatabase.addUser(user);
		
		//temp
//		user = new User("stock2");
//		a = new Album("stock", user);
//		for(int i = 0; i < 10; i++)
//		{
//			p = new Photo("/stock/"+i+".jpg", LocalDateTime.parse("2017-11-10T15:12:22"));
//			p.addCaption(i+"!");
//			user.addPhoto(p);
//			a.addPhoto(p.getId());
//		}
//		user.addAlbum(a);
//		UserDatabase.addUser(user);
	}
}
