package server.command;

import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command gets the list of all existing games in an array of string. The array
 * is in the value of "gamesArray" key in response.
 * 
 * Using of this command requires client to login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class GetGamesArray implements Command
{
	/**
	 * Command implementation
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		response.put("gamesArray", Resources.getInstance().getGamesArray());
		response.put("ok", true);
		return response;
	}

}
