package server.command;

import java.util.HashMap;

import server.Session;
import shared.APIObject;
/**
 * This class is created using Command and Factory design patterns.
 * 
 * It keeps a collection of commands and run them by the APIObject sent to doCommand method.
 * The APIObject must have a "method" key and the value of this key is the method which runs.
 * 
 * The commands classes must implement the Command interface. The code to be run must be put in 
 * the execute method. The response method must have a key "ok" which has a boolean true or
 * false value. All commands must put response data in a response APIObject which will be sent
 * to the client. Whenever the command faces with an exception, CommandFactory creates a response
 * object and puts the "ok":false and "exception":(message) into it. If the exception is a
 * CommandException puts the exception message into that. Otherwise sets the value of "exception"
 * to "Server Error!".
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CommandFactory
{
	private static HashMap<String, Command> commands;
	
	/**
	 * Creates commands collection and commands.
	 */
	static
	{
		commands = new HashMap<String, Command>();
		
		commands.put("createUser", new CreateUser());
		commands.put("checkUser", new CheckUser());
		commands.put("login", new Login());
		commands.put("logout", new Logout());
		commands.put("createGame", new CreateGame());						// Not complete
		commands.put("createGameAI", new CreateGameAI());					// Not complete
		commands.put("checkGame", new CheckGame());
		commands.put("getMapsArray", new GetMapsArray());
		commands.put("getTanksArray", new GetTanksArray());
		commands.put("getGamesArray", new GetGamesArray());
		commands.put("changeUserInfo", new ChangeUserInfo());				// Not complete
		commands.put("loadImage", new LoadImage());
		commands.put("joinGame", new Join());
		commands.put("getUserPlayingStats", new getUserStats());			// Not complete
		commands.put("updateGameUI", new UpdateGameUI());					// Not complete
		commands.put("tankAction", new TankAction());						// Not complete
		commands.put("updateGameIntro", new UpdateGameIntro());				// Not complete
		commands.put("beginGame", new BeginGame());
		commands.put("changeUser", null);
	}
	
	/**
	 * Runs a command.
	 * @param api The APIObject contains the command and its arguments.
	 * @param session The session which sends this command.
	 * @return The response object
	 */
	public static APIObject doCommand(Object api, Session session)
	{
		APIObject apiObject = (APIObject) api;
		APIObject response = null;
		try
		{
			String method = apiObject.get("method").toString();
			response = commands.get(method).execute(apiObject, session);
			
		} 
		catch (Exception e)
		{
			response = new APIObject();
			String message = null;
			
			if(e instanceof CommandException)
				message = e.getMessage();
			else
				message = "Server Error!";
			
			response.put("ok", false);
			response.put("exception", e.getClass().getName() + ": " + message);
			
			e.printStackTrace();
		}
		
		return response;
	}
}
