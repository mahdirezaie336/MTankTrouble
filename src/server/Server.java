package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * This class has a welcoming socket on port 16800 and has an infinite loop
 * which listens on the port and for every connections creates a new session.
 *
 * @author Mahdi Rezaie 9728040
 *
 */
public class Server
{
	private ServerSocket welcomingSocket;
	
	/**
	 * Initializes the welcoming socket.
	 * @throws IOException
	 */
	public Server() throws IOException
	{
		welcomingSocket = new ServerSocket(16800);
	}
	
	/**
	 * Runs the loop.
	 */
	public void Run()
	{
		while(true)
		{
			try
			{
				System.out.println("Waiting for connection ...");
				Socket connection = welcomingSocket.accept();
				System.out.println("Connection accepted! Running Session...");
				Resources.getInstance().startNewSession(connection);
				
			} catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Server Driver
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			Server server = new Server();
			server.Run();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
