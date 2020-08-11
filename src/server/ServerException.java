package server;
/**
 * This exception is thrown when an exception related to server happens.
 * The client must not to know the message of this exception so its message
 * is hidden in CommandFactory.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ServerException extends Exception
{
	private static final long serialVersionUID = 909854949835771953L;
	
	public ServerException(String s)
	{
		super(s);
	}

}
