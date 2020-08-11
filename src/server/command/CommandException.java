package server.command;
/**
 * This exception is symbol of exceptions that happen because of the client's request.
 * The message of this exception will be sent by response in the value of "exception"
 * key.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CommandException extends Exception
{
	private static final long serialVersionUID = 6488788402505581231L;

	public CommandException(String arg0)
	{
		super(arg0);
	}

}
