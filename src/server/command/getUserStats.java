package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command gets the user statistics. The response object has keys and values like this:
 * 
 * key										value description
 * 
 * "playingHours" (long)					playing hours
 * "winningTimesM" (int)					number of total winnings in multi player
 * "winningTimesS" (int)					number of total winnings in single player
 * 
 * To use this command, client must login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class getUserStats implements Command
{
	
	/**
	 * Command implementation
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		
		return response;
	}
	
}
