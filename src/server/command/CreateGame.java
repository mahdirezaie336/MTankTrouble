package server.command;

import server.Game;
import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command adds a new game to the Resources. The keys and values for this command are
 * described below:
 * 
 * value 								key
 *  
 * game name (String)					"gameName"
 * maximum players (String)				"maximumPlayers"
 * minimum players (String)				"minimumPlayers"
 * map name (String)					"map"
 * everyone is enemy mode (Boolean)		"single"
 * league mode (Boolean)				"leagueMode"
 * tank health (String)					"tankHealth"
 * breakable wall health (String)		"wallHealth"
 * Bullet Damage (String)				"bulletDamage"
 * 
 * Using of this command requires client to login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CreateGame implements Command
{
	/**
	 * Command implementation
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		String gameName = api.get("gameName").toString();
		
		if(Resources.getInstance().gameExists(gameName))								// Checking same names
			throw new CommandException("A game with this name already exists.");
		
		int maxPlayer = Integer.parseInt(api.get("maximumPlayers").toString());
		int minPlayer = Integer.parseInt(api.get("minimumPlayers").toString());
		double tankHealth = Double.parseDouble(api.get("tankHealth").toString());
		double wallHealth = Double.parseDouble(api.get("wallHealth").toString());
		double bullDamage = Double.parseDouble(api.get("bulletDamage").toString());
		boolean single = (Boolean) api.get("single");
		boolean league = (Boolean) api.get("leagueMode");
		
		Game newGame = new Game(gameName);
		Resources.getInstance().addGame(gameName, newGame);
		s.setGame(newGame);
		
		response.put("ok", true);
		return response;
	}

}
