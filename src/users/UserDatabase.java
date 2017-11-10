package users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDatabase.
 */
public class UserDatabase 
{
	
	/** The users. */
	private static ArrayList<User> users = new ArrayList<>(); //list of current loaded users
	
	/** The user names. */
	private static ArrayList<String> userNames = new ArrayList<>();
	
	/**
	 * Save usernames.
	 */
	public static void saveUsernames()
	{
		try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("usernames.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(userNames);
             
            out.close();
            file.close();
            
            while(!users.isEmpty()) //save remaining users then clear them from list
            {
            	saveUser(users.get(0).getUserName());
            	users.remove(0);
            }
            
            System.out.println("usernames has been serialized");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	/**
	 * Load user names.
	 */
	public static void loadUserNames()
	{
		try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream("usernames.ser");
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            userNames = (ArrayList<String>)in.readObject();
             
            in.close();
            file.close();
            
            System.out.println("usernames has been deserialized");
        }
        catch(FileNotFoundException e)
		{
        	//do nothing
		}
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	/**
	 * Save user.
	 *
	 * @param name the name
	 */
	public static void saveUser(String name)
	{
		try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(name+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(findUser(name));
             
            out.close();
            file.close();
             
            System.out.println(name + " has been serialized");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
 
	}
	
	/**
	 * Load user.
	 *
	 * @param name the name
	 * @return the user
	 */
	public static User loadUser(String name)
	{
		User user = null;
		try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(name+".ser");
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            user = (User)in.readObject();
             
            in.close();
            file.close();
             
            System.out.println(name + " has been deserialized ");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		users.add(user); //give it to list of loaded users
        return user;
	}

	/**
	 * Adds the user.
	 *
	 * @param name the name
	 */
	public static void addUser(String name)
	{
		userNames.add(name);
		users.add(new User(name));
	}
	
	public static void addUser(User user)
	{
		users.add(user);
	}
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Adds the users.
	 *
	 * @param name the name
	 */
	public static void addUsers(String name) {
		users.add(new User(name));
	}
	
	/**
	 * Find user.
	 *
	 * @param name the name
	 * @return the user
	 */
	private static User findUser(String name)
	{
		Predicate<User> predicate = c-> c.getUserName().equals(name);
		User obj = users.stream().filter(predicate).findFirst().get();
		return obj;
	}
}
