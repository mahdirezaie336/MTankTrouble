package server.command;

import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command checks whether a game is exists  or not. The request command must have a
 * "gameName" key with value of game name. The response command will have a "found" key
 * with a boolean value that is true when game exists.
 * 
 * Using this command requires client to log in.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CheckGame implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		String gameName = api.get("gameName").toString();
		
		CheckUser.check(s.getUser());
		
		response.put("found", Resources.getInstance().gameExists(gameName));
		response.put("ok", true);
		return response;
	}

}
