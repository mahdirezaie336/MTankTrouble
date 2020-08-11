package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command gets the players list on the running game and sends it back to the
 * client. Also if the players reach minimum number to play, sends a boolean value
 * in value of "enableButton" so that the client understand that it can run the game.
 * The array of players is in value of "playersArray".
 * 
 * To user this command, the user must login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class UpdateGameIntro implements Command
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
