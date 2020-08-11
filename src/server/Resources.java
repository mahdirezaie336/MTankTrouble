package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;

import server.command.CommandException;

/**
 * Using Bill Pugh Singleton design pattern, i've created this class.
 * 
 * This class keeps all resources of the server like sessions, games and .... It alse has some
 * public static fields which are some resources directory in system. like USERS_PATH.
 * 
 * @author Mahdi
 *
 */
public class Resources
{
	public static final String USERS_PATH = "./data/server/users/";
	public static final String MAPS_PATH = "./maps/";
	public static final String IMAGES_PATH = "./images/";
	private HashMap<String, Game> games;
	private LinkedList<Session> sessions;
	
	/**
	 * Initializes the collections.
	 */
	private Resources()
	{
		games = new HashMap<>();
		sessions = new LinkedList<>();
	}
	
	/**
	 * The helper class that keeps the unique instance of the Resources class.
	 * 
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private static class Helper
	{
		private static final Resources INSTANCE = new Resources();
	}
	
	/**
	 * Gets the unique instance of this class
	 * @return The unique instance
	 */
	public static Resources getInstance()
	{
		return Helper.INSTANCE;
	}
	
	/**
	 * Checks whether a game is exists.
	 * @param s The game name
	 * @return If found returns true.
	 */
	public boolean gameExists(String s)
	{
		return games.containsKey(s);
	}
	
	/**
	 * Adds a new game to the game collection.
	 * @param name The game name
	 * @param game Tha game
	 */
	public void addGame(String name, Game game)
	{
		games.put(name, game);
	}
	
	/**
	 * Gets a game by its name.
	 * @param gameName The game name
	 * @return The desired game
	 */
	public Game getGame(String gameName)
	{
		return games.get(gameName);
	}
	
	/**
	 * Gets all games names.
	 * @return The games names array
	 */
	public String[] getGamesArray()
	{
		return games.keySet().toArray(new String[games.size()]);
	}
	
	/**
	 * Removes a game from games list.
	 * @param gameName The game name to remove
	 */
	public void removeGame(String gameName)
	{
		games.remove(gameName);
	}
	
	/**
	 * Starts a new session with the connection.
	 * 
	 * @param connection The session connection
	 */
	public void startNewSession(Socket connection)
	{
		Session s = new Session(connection);
		sessions.add(s);
		Thread t = new Thread(s);
		t.start();
	}
	
	/**
	 * Removes a session from sessions list.
	 * @param s The session to remove.
	 */
	public void removeSession(Session s)
	{
		sessions.remove(s);
	}
	
	/**
	 * Checks whether a user is registered.
	 * @param user The user to be checked
	 * @return If found returns true.
	 */
	public boolean userIsRegistered(User user)
	{
		if(user == null) return false;
		return userIsRegistered(user.getUsername());
	}
	
	/**
	 * Checks whether a user is registered.
	 * @param username Username of the user to be checked
	 * @return If found returns true.
	 */
	public boolean userIsRegistered(String username)
	{
		File file = new File(USERS_PATH + username + ".bin");
		return file.exists();
	}
	
	/**
	 * Adds a new users to the users list.
	 * @param user The new user
	 * @throws CommandException If user already exists throws this type of exception
	 * @throws ServerException On any other exceptions throws this type of exception
	 */
	public void addUser(User user) throws CommandException,ServerException
	{
		File file = new File(USERS_PATH + user.getUsername() + ".bin");
		
		if(!new File(USERS_PATH).exists())											// Checking existance of users path
			new File(USERS_PATH).mkdirs();
		
		if(userIsRegistered(user.getUsername()))									// Checking existance of user
			throw new CommandException("User is already exists.");
		
		try																			// Create new file
		{
			file.createNewFile();
		} catch (IOException e1)
		{
			throw new ServerException(e1.getMessage());
		}
		
		try(																		// Writing user to file
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				)
		{
			oos.writeObject(user);
		} 
		catch(IOException e)
		{
			throw new ServerException(e.getMessage());
		}
	}
	
	/**
	 * Gets a user specified with username.
	 * @param username The user name
	 * @return The desired user
	 * @throws ServerException On any problems excpet user not found, throws this. 
	 * @throws CommandException If user not found throws this type of exception.
	 */
	public User getUser(String username) throws ServerException, CommandException
	{
		try(
				FileInputStream fis = new FileInputStream(Resources.USERS_PATH + username + ".bin");
				ObjectInputStream ois = new ObjectInputStream(fis);
				)
		{
			return (User) ois.readObject();
		} 
		catch (FileNotFoundException e1)
		{
			throw new CommandException("User not found!");
		} 
		catch (ClassNotFoundException|IOException e)
		{
			throw new ServerException(e.getMessage());
		}
		
	}
	
	public void removeUser(String username) throws ServerException
	{
		try
		{
			Files.delete(new File(USERS_PATH + username + ".bin").toPath());
		} catch (IOException e)
		{
			throw new ServerException(e.getMessage());
		}
	}
}
