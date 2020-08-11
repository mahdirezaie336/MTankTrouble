package shared;

import java.io.Serializable;
import java.util.HashMap;
/**
 * This class is the way client and server speak to each other. The client sends some commands
 * by putting a key "method". Its value is about which command to run. Other arguments of the
 * command can be sent in key-value form inside objects of this class. Also the server can send
 * its responses to client by an instance of this class. It is important to say that each value
 * can be any Serializable object. 
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class APIObject implements Serializable
{
	private static final long serialVersionUID = 2748008187362957752L;
	private HashMap<String, Serializable> objects;
	
	/**
	 * Initializes the collection keeping the key-value information.
	 */
	public APIObject()
	{
		objects = new HashMap<String, Serializable>();
	}
	
	/**
	 * Puts a new key value information into this object.
	 * @param key The key
	 * @param value The value
	 * @return
	 */
	public APIObject put(String key, Serializable value)
	{
		objects.put(key, value);
		return this;
	}
	
	/**
	 * Gets the value of the given key.
	 * @param key The key of value desired
	 * @return The desired value
	 */
	public Serializable get(String key)
	{
		return objects.get(key);
	}
	
	/**
	 * Shows information about the object.
	 */
	@Override
	public String toString()
	{
		return "APIObject [objects=" + objects + "]";
	}
	
	
}
