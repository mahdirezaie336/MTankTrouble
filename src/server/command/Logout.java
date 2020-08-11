package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command logs the user out of its session.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Logout implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		s.setUser(null);
		
		response.put("ok", true);
		return response;
	}

}
