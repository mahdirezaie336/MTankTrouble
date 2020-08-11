package server.command;

import server.Resources;
import server.ServerException;
import server.Session;
import server.User;
import shared.APIObject;
/**
 * This command creates a user object by calling Resources and adds it to the list of
 * valid users. Key and value of this command are described below:
 * 
 * value							key
 * 
 * user name (String)				"username"
 * password	(String)				"password"
 * full name (String)				"fullName"
 * 
 * Using of this command DOES NOT require client to log in.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CreateUser implements Command
{
	@Override
	public APIObject execute(APIObject api, Session session) throws ServerException,CommandException
	{
		APIObject response = new APIObject();
		
		String username = api.get("username").toString();
		String password = api.get("password").toString();
		String fullName = api.get("fullName").toString();
		User newUser = new User(username, password, fullName);
		
		Resources.getInstance().addUser(newUser);
		
		response.put("ok", true);
		return response;
	}
}
