package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command takes an action to the user tank. The actions are moving, turning and shooting
 * which are coded in integer numbers from 0 to 4. These numbers are in an array of integers in
 * value of "actions" key.
 * 
 * To use this command, the user must log in.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class TankAction implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		
		
		response.put("ok", true);
		return response;
	}

}
