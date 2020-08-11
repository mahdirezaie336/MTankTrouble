package server.command;

import server.Game;
import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command joins a player or user to a game. The game name is specified in
 * value of "gameName" key. Also the team that user wanted to join is in value
 * of "teamNumber" which can be 1 or 2 as integer.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Join implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		String gameName = api.get("gameName").toString();
		int team = 1;
		Game game = Resources.getInstance().getGame(gameName);
		if(!game.isSingleMode())
			team = (Integer) api.get("team");
		
		game.joinPlayer(s.getUser(), team);
		s.setGame(game);
		
		response.put("ok", true);
		return response;
	}

}
