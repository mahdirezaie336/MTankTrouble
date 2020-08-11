package server.command;


import server.Resources;
import server.ServerException;
import server.Session;
import server.User;
import shared.APIObject;
/**
 * This command changes the information of user of caller session. The information is sent in
 * value of "username", "password" and "fullName" keys.
 * 
 * Using this command requires client to log in.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ChangeUserInfo implements Command
{
	/**
	 * Implementation of command.
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());																		// Checking user login
		
		User user = s.getUser();
		
		String username = api.get("username").toString();
		
		if(Resources.getInstance().userIsRegistered(api.get("username").toString()))						// Checking valid username
			throw new CommandException("This username already taken.");
		
		String password = api.get("password").toString();
		String fullName = api.get("fullName").toString();
		
		
		
		User newUser = new User(username, password, fullName);
		Resources.getInstance().addUser(newUser);
		Resources.getInstance().removeUser(user.getUsername());
		s.setUser(newUser);
		
		response.put("ok", true);
		return response;
	}

}
