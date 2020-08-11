package server;


import server.User;

/**
 * This class holds information about a game.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Game
{
	private User owner;
	private String gameName;
	
	public Game(String name)
	{
		gameName = name;
	}
	
	public String getName()
	{
		return gameName;
	}
	
	public User getOwner()
	{
		return owner;
	}
	
	public void beginGame()
	{
		
	}
	
	public boolean isSingleMode()
	{
		return false;
	}
	
	public void joinPlayer(User user, int team)
	{
		
	}
}
