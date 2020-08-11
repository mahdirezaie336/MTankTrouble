package server;

import java.io.Serializable;

/**
 * This class is representation of a user or player in this game.
 * Instances of this class are used to authorize login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class User implements Serializable
{
	private static final long serialVersionUID = -8917489186739627359L;
	private String username;
	private String password;
	private String fullName;

	public User(String username, String password, String fullName) 
	{
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}
	
	/**
	 * Gets the password of the user.
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Gets the password of the user
	 * @return
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Changes the username
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * Gets the user full name.
	 * @return
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * Sets the user full name.
	 * @param fullName
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * Changes the user password.
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null)
		{
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return username ;
	}
}
