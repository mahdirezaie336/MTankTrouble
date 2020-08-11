package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command creates a game with AI.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CreateGameAI implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		int number = Integer.parseInt(api.get("playersNumber").toString());
		double tankHealth = Double.parseDouble(api.get("tankHealth").toString());
		double wallHealth = Double.parseDouble(api.get("wallHealth").toString());
		double bullDamage = Double.parseDouble(api.get("bulletDamage").toString());
		boolean league = (Boolean) api.get("leagueMode");
		
		
		response.put("ok", true);
		return response;
	}

}
