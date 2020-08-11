package server.command;

import server.Resources;
import server.ServerException;
import server.Session;
import server.User;
import shared.APIObject;

/**
 * This command logs the client in server. By logging in, the user field of session will
 * be field. The user information must be sent in value of "username" and "password" keys
 * in request API object.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Login implements Command
{
	/**
	 * Command implementation
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		String username = api.get("username").toString();
		String password = api.get("password").toString();
		
		User user = Resources.getInstance().getUser(username);
		
		if(!user.getPassword().equals(password))
			throw new CommandException("Password wrong!");
		
		s.setUser(user);
		
		
		response.put("ok", true);
		return response;
	}

}
