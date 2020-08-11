package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command gets the game state object from server and sends it back to
 * client inside the response object in value of "gameState" key.
 * 
 * To use this command user must login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class UpdateGameUI implements Command
{
	
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		response.put("ok", true);
		return response;
	}

}
