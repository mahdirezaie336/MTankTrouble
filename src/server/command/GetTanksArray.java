package server.command;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;

import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command gets the array of names of existing tanks in an array of string. The
 * requested array will be in value of "tanksArray" key in response object.
 * 
 * To use this command, client must login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class GetTanksArray implements Command
{
	
	/**
	 * Command implementation
	 */
	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		File[] files = new File("./images/tank/").listFiles(new FilenameFilter()
		{
			
			@Override
			public boolean accept(File arg0, String arg1)
			{
				return arg1.endsWith(".png");
			}
		});
		
		HashSet<String> set = new HashSet<String>();
		for(File file : files)
			set.add(file.getName().substring(0, file.getName().lastIndexOf(".")));
		
		response.put("ok", true);
		response.put("tanksArray", set.toArray(new String[set.size()]));
		return response;
	}

}
