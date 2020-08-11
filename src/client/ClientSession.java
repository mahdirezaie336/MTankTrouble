package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.APIObject;
/**
 * This class is a connection between server and client. It has a field named connection
 * which is instance of Socket class. After the connection connected to the server the
 * output and input streams will be initialized.
 * 
 * The architecture used to create this class is Singleton design pattern.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ClientSession
{
	private Socket connection;
	private ObjectInputStream oIS;
	private ObjectOutputStream oOS;
	
	private ClientSession() {}																// Useless constructor
	
	/**
	 * This class is a static class keeps the unique instance of the connection.
	 * 
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private static class Helper
	{
		private static ClientSession INSTANCE = new ClientSession();
	}
	
	/**
	 * Gets the unique instance of this class.
	 * @return The instance
	 */
	public static ClientSession getInstance()
	{
		return Helper.INSTANCE;
	}
	
	/**
	 * Tries to connect to the specified host.
	 * @param host The host address to be connected
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void setConnection(String host) throws UnknownHostException, IOException
	{
		connection = new Socket(host, 16800);
		connection.setKeepAlive(true);
		oOS = new ObjectOutputStream(connection.getOutputStream());
		oIS = new ObjectInputStream(connection.getInputStream());
	}
	
	/**
	 * Writes an object into the output stream.
	 * @param o The object to be written
	 * @throws IOException
	 */
	public void sendObject(APIObject o) throws IOException
	{
		oOS.writeUnshared(o);
		oOS.reset();
	}
	
	/**
	 * Reads an object from the input stream
	 * @return The read object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public APIObject getObject() throws ClassNotFoundException, IOException
	{
		return (APIObject) oIS.readObject();
	}
}
