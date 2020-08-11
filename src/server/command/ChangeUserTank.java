package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * Changes the user tank image. The new tank name must be put in value of
 * "tankName" key.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ChangeUserTank implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		
		
		response.put("ok", true);
		return response;
	}

}
