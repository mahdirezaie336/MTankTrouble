package server.command;


import server.Resources;
import server.ServerException;
import server.Session;
import server.User;
import shared.APIObject;
/**
 * This class is user check command which gets the username in value of "username" key.
 * If command runs without any exceptions, sends a response object containing a "found" 
 * key. Its value is a boolean true if user exists.
 * 
 * It does not need to login.
 * 
 * It also has a static method which is used to check if user is logged in to server or
 * no. If user is not logged in or is not registered throws a CommandException.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CheckUser implements Command
{
	/**
	 * Runs the command code.
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		String username = api.get("username").toString();
		
		response.put("found", Resources.getInstance().userIsRegistered(username));
		response.put("ok", true);
		return response;
	}
	
	/**
	 * Checks whether user is logged in or user is registered.
	 * @param user The user to be checked
	 * @throws CommandException Throws this type of exception if user is invalid or not logged in.
	 * @throws ServerException
	 */
	public static void check(User user) throws CommandException,ServerException
	{
		if(user == null)
			throw new CommandException("Login required.");
		
		if(!Resources.getInstance().userIsRegistered(user))
			throw new CommandException("Invalid user.");
		
	}
}
