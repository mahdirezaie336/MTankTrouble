package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command is used to begin the game. This command works only when
 * the game creator begins the game.
 *  
 * @author Mahdi Rezaie 9728040
 *
 */
public class BeginGame implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		if( s.getUser().equals( s.getGame().getOwner() ) )
			s.getGame().beginGame();
		else
			throw new CommandException("Only creator can begin the game");
		
		response.put("ok", true);
		return response;
	}

}
