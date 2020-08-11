package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import server.command.CommandFactory;

/**
 * For any connections on welcoming socket, a new session will be created. Every sessions
 * runs on a separated thread. They have a User field which is null at first. When user
 * logs in, this field will be filled with the user object.
 * 
 * When a session runs, an infinite loop will begin which reads an APIObject. Using Command-
 * Factory it runs the object command and gets the response object from CommandFactory. The
 * response object will send to client from connection output stream and listens again to
 * read another object.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Session implements Runnable
{
	private Socket connection;
	private User user;
	private boolean endSession;
	private Game game;
	
	/**
	 * Sets the connection field.
	 * @param connection
	 */
	public Session(Socket connection)
	{
		this.connection = connection;
		try
		{
			connection.setKeepAlive(true);
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
		user = null;
		endSession = false;
		game = null;
	}
	
	/**
	 * The session loop.
	 */
	@Override
	public void run()
	{
			try(
					ObjectInputStream oIS = new ObjectInputStream(connection.getInputStream());
					ObjectOutputStream oOS = new ObjectOutputStream(connection.getOutputStream())
					)
			{
				while(!endSession)
				{
					Object object = oIS.readObject();
					Object respone = CommandFactory.doCommand(object,this);
					oOS.writeUnshared(respone);
					oOS.reset();
				}
				
			} catch (ClassNotFoundException|IOException e)
			{
				//e.printStackTrace();
				System.out.println(e.getMessage() + "\nClosing Session ...");
			}
			
			if(game != null)
			if(game.getOwner().equals(user))															// Removes game from game list
				Resources.getInstance().removeGame(game.getName());										// Removes session from session list
			Resources.getInstance().removeSession(this);
	}
	
	/**
	 * Sets the user of this session.
	 * @param u The user
	 * @return this object
	 */
	public Session setUser(User u)
	{
		user = u;
		return this;
	}
	
	/**
	 * Gets the user of this session.
	 * @return The user
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * Changes the end session field.
	 * @param value The new value for the end session field
	 */
	public void setEndSession(boolean value)
	{
		endSession = value;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}

}