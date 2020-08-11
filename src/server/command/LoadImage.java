package server.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import server.Resources;
import server.ServerException;
import server.Session;
import shared.APIObject;
/**
 * This command gets an image with given name and type. The key-value inputs of
 * this command is described below:
 * 
 * value								key
 * 
 * name of image (String)				"name"
 * type of image (String)				"type"
 * 
 * NOTE: Type of image can be "tank", "map" or "user".
 * 
 * The response object will have "image" key with value of the requested image byte array.
 * 
 * To use this command, client DOES NOT need to login.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class LoadImage implements Command
{

	@Override
	public APIObject execute(APIObject api, Session s) throws ServerException, CommandException
	{
		APIObject response = new APIObject();
		
		//CheckUser.check(s.getUser());
		
		String type = api.get("type").toString();
		String name = api.get("name").toString();
		
		byte[] image = null;
		try
		{
			switch(type)
			{
			case "tank":
				image = Files.readAllBytes(new File(Resources.IMAGES_PATH + "tank/" + name + ".png").toPath());
				break;
			case "map":
				image = Files.readAllBytes(new File(Resources.MAPS_PATH + name + ".jpg").toPath());
				break;
			case "user":
				//image = Resources.getInstance().getUser(name).getImage();
				break;
			default:
				throw new CommandException("Invalid requested type");
			}
		}
		catch(FileNotFoundException e)
		{
			throw new CommandException(name + " does not exist.");
		}
		catch(IOException e)
		{
			throw new ServerException(e.getMessage());
		}
		
		response.put("image", image);
		response.put("ok", true);
		return response;
	}


}
