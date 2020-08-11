package server.command;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;

import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;

/**
 * This command gets an array of existing maps inside an array of string. The array will
 * be put in value of "mapArray" key in response object.
 * 
 * To use this command, client must login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class GetMapsArray implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		CheckUser.check(s.getUser());
		
		File mapsFolder = new File(Resources.MAPS_PATH);
		mapsFolder.mkdirs();
		
		HashSet<String> filesNames = new HashSet<String>();
		File[] files = mapsFolder.listFiles(new FilenameFilter()
		{
			
			@Override
			public boolean accept(File arg0, String arg1)
			{
				return arg1.endsWith(".txt");
			}
		});
		for(File file : files)
		{
			String name = file.getName();
			filesNames.add(name.substring(0, name.lastIndexOf(".")));
		}
		response.put("mapsArray", filesNames.toArray(new String[filesNames.size()]));
		response.put("ok", true);
		return response;
	}

}
