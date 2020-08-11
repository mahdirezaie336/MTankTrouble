package server.command;

import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * The classes which implements this interface can be executed as commands of CommandFactory.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public interface Command
{
	/**
	 * The implementation of the command.
	 * @param api The command arguments.
	 * @param s The session which sends the command
	 * @return The response object
	 * @throws ServerException If any exception related to server happens, throws this.
	 * @throws CommandException If any exception related to the client input happens, throws this.
	 */
	public APIObject execute(APIObject api, Session s) throws ServerException,CommandException;
}
