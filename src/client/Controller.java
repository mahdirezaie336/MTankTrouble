package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import client.menu.GameIntro;
import client.menu.MainFrame;
import shared.APIObject;
import utils.FileUtils;
/**
 * This package contains the classes to handle events of the client menu frame.
 * It works using MVC Design pattern and this class is the Controller class of
 * this pattern. It gathers information from Model, sends the requested objects
 * and calls the View to show the processed information.
 * 
 * This class contacts to the server using APIObejct which is described in its class.
 * 
 * All methods of this class are static that when user call these, they will run
 * on the caller thread. If developer needs to run methods on another thread, he/she
 * can use constructor and the "command" field.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Controller implements Runnable
{
	private String command;															// The method to be run on another thread
	private int[] args;
	private static final String LOGIN_INFO = "./data/client/login.txt";
	
	/**
	 * By setting command field, it commands the run method to execute a specific method.
	 * @param command The method to be run.
	 */
	public Controller(String command)
	{
		this.command = command;
	}
	
	public Controller(String command, int...args)
	{
		this.command = command;
		this.args = args;
	}
	
	/**
	 * Runs a method specified with command field.
	 */
	public void run()
	{
		switch(command)
		{
		case "loadCreateGameMapPreview":
			loadCreateGameMapPreview();
			break;
		case "loadAIGameMapPreview":
			loadAIGameMapPreview();
			break;
		case "refreshServerList":
			refreshServerList();
			break;
		case "loadTankPreview":
			loadTankPreview();
			break;
		case "runUpdateLoop":
			//runUpdateLoop();
			break;
		case "tankAction":
			tankAction(args);
			break;
		}
	}
	
	/**
	 * On the server choose page, it gets the selected item from list and
	 * displays it into the text field.
	 */
	public static void loadTextFieldOfFirstServerPage()
	{
		HashMap<String, Object> datas = Model.getChooseServerDatas();
		
		String address = datas.get("List Selected Address").toString();
		
		View.setFirstServerAddressField(address);
	}
	
	/**
	 * On the server change panel, it gets the selected item from list and
	 * displays it into the text field.
	 */
	public static void loadTextFieldOfChangeServerPanel()
	{
		HashMap<String, Object> datas = Model.getChangeServerDatas();
		
		String address = datas.get("List Selected Address").toString();
		
		View.setChangeServerField(address);
	}
	
	/**
	 * Refreshes both server lists in server choose page and server change panel.
	 */
	public static void refreshServerList()
	{
		HashSet<String> items = new HashSet<String>();
		items.add("localhost");
		
		try(
				FileInputStream fis = new FileInputStream("./servers.txt");
				Scanner scanner = new Scanner(fis);
				)
		{
			String line = scanner.nextLine();
			items.add(line);
		} 
		catch(Exception e)
		{
			showError(e.getMessage());
		}
		
		String[] i = items.toArray(new String[items.size()]);
		View.setFirstServerList(i);
		View.setChangeServerList(i);
	}
	
	/**
	 * On the server choose page, it tries to connect to the address on the text field. 
	 * @return If succeed returns true.
	 */
	public static boolean connectToServer()
	{
		HashMap<String, Object> datas = Model.getChooseServerDatas();
		
		try
		{
			if(datas.get("Address Field") == null)
				throw new IOException("Select a server");
			ClientSession.getInstance().setConnection(datas.get("Address Field").toString());
		} 
		catch (IOException e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * On the login page, tries to login.
	 * @return If succeed returns true.
	 */
	public static boolean login()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getLoginDatas();
		
		request.put("method", "login");
		request.put("username", (String)datas.get("Username"));
		request.put("password", (String)datas.get("Password"));
		
		if((Boolean)datas.get("Remember Info"))
		{
			String text = "true\n" + datas.get("Username") + "\n" + datas.get("Password");
			FileUtils.TextFileWrite(LOGIN_INFO, text);
		}
		else
			FileUtils.TextFileWrite(LOGIN_INFO, "false");
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			//e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Loads the saved login info from its file.
	 */
	public static void loadLoginInfo()
	{
		File file = new File(LOGIN_INFO);
		
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			try
			{
				file.createNewFile();
			} catch (IOException e) {}
		}
		
		String[] parts = FileUtils.TextFileRead(LOGIN_INFO).trim().split("\n");
		if(parts.length != 3) return;
		
		if(parts[0].equals("false"))
			return;
		
		View.setLoginSavedInfo(parts[1], parts[2]);
	}
	
	/**
	 * Tries to logout.
	 * @return If succeed returns true.
	 */
	public static boolean logout()
	{
		APIObject request = new APIObject();
		
		request.put("method", "logout");
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * On the register page, tries to register with given information.
	 * @return If succeed returns true.
	 */
	public static boolean register()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getRegisterDatas();
		
		request.put("method", "createUser");
		request.put("username", datas.get("Username").toString());
		request.put("password", datas.get("Password").toString());
		request.put("fullName", datas.get("Full Name").toString());
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tries to create a game with given information from the Create Game panel.
	 * @return If succeed returns true.
	 */
	public static boolean createGame()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getCreateGameDatas();
		
		request.put("method", "createGame");
		request.put("gameName", datas.get("Game Name").toString());
		request.put("maximumPlayers", datas.get("Maximum Players").toString());
		request.put("minimumPlayers", datas.get("Minimum Players").toString());
		request.put("map", datas.get("Map List Selected").toString());
		request.put("single", (Boolean)datas.get("Single in Team"));
		request.put("leagueMode", (Boolean)datas.get("League Mode"));
		request.put("tankHealth", datas.get("Tank Health").toString());
		request.put("wallHealth", datas.get("Wall Health").toString());
		request.put("bulletDamage", datas.get("Bullet Damage").toString());

		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		GameIntro.setCreatorMode(true);
		return true;
	}
	
	/**
	 * Tries to join to the selected game in the Join Game panel.
	 * @return If succeed returns true.
	 */
	public static boolean joinGame()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getJoinGameDatas();
		
		request.put("method", "joinGame");
		request.put("gameName", datas.get("Selected Game").toString());
		request.put("team", (new Random().nextInt(2)) + 1);
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		GameIntro.setCreatorMode(false);
		return true;
	}
	
	/**
	 * Tries to create a game with given information from the Play with AI panel.
	 * @return If succeed returns true.
	 */
	public static boolean playWithAI()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getPlayWithAIDatas();
		
		request.put("method", "createGameAI");
		request.put("playersNumber", datas.get("Players Number").toString());
		request.put("map", datas.get("Map List Selected").toString());
		request.put("leagueMode", (Boolean) datas.get("League Mode"));
		request.put("tankHealth", datas.get("Tank Health").toString());
		request.put("wallHealth", datas.get("Wall Health").toString());
		request.put("bulletDamage", datas.get("Bullet Damage").toString());

		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tries to change the user information from in Setting - User Settings.
	 */
	public static void changeUserInformation()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getUserSettingsField();
		
		request.put("method", "changeUserInfo");
		request.put("username", datas.get("Username").toString());
		request.put("password", datas.get("Password").toString());
		request.put("fullName", datas.get("Full Name").toString());
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return;
		}
	}
	
	/**
	 * Tries to change the player information from in Setting - Player Settings.
	 */
	public static void changePlayerSettings()
	{
		APIObject request = new APIObject();
		HashMap<String, Object> datas = Model.getPlayWithAIDatas();
		
		request.put("method", "changePlayerStats");
		request.put("tank", datas.get("Selected Tank").toString());
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return;
		}
	}
	
	/**
	 * Tries to change the server of the ClientSession connection.
	 * @return If succeed returns true.
	 */
	public static boolean changeServer()
	{
		HashMap<String, Object> datas = Model.getChangeServerDatas();
		
		try
		{
			ClientSession.getInstance().setConnection(datas.get("Address Field").toString());

		} catch (IOException e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Perform a request by sending an APIObject and gets the response.
	 * @param request The request APIObject
	 * @return The response APIObject
	 * @throws Exception If server returns {"ok":false} throws an exception. Also throws IO exceptions.
	 */
	public synchronized static APIObject sendSimpleRequest(APIObject request) throws Exception
	{
		APIObject response = null;
		
		ClientSession.getInstance().sendObject(request);
		response = ClientSession.getInstance().getObject();
		
		if( !((Boolean)response.get("ok")) )
			throw new Exception(response.get("exception").toString());
		
		return response;
	}
	
	/**
	 * On the main frame, open a specified page.
	 * @param name The page name
	 */
	public static void goToPage(String name)
	{
		MainFrame.getInstance().goToPage(name);
	}
	
	/**
	 * On the Create Game panel, when user clicks on a map list item, displays
	 * the map preview taken from server.
	 */
	public static void loadCreateGameMapPreview()
	{
		HashMap<String, Object> datas = Model.getCreateGameDatas();
		APIObject request = new APIObject();
		
		request.put("method", "loadImage");
		request.put("type", "map");
		request.put("name", datas.get("Map List Selected").toString());
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		byte[] image = (byte[]) response.get("image");
		String name = request.get("name").toString();
		View.setMapPreview(FileUtils.convertByteArrayToImage(image, name));
	}
	
	/**
	 * On the Play with AI panel, when user clicks on a map list item, displays
	 * the map preview taken from server.
	 */
	public static void loadAIGameMapPreview()
	{
		HashMap<String, Object> datas = Model.getPlayWithAIDatas();
		APIObject request = new APIObject();
		
		request.put("method", "loadImage");
		request.put("type", "map");
		request.put("name", datas.get("Map List Selected").toString());
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}

		byte[] image = (byte[]) response.get("image");
		String name = request.get("name").toString();
		View.setPlayWithAIMapPreview(FileUtils.convertByteArrayToImage(image, name));
	}
	
	/**
	 * On the Player Settings panel, when user clicks on a tank list item, displays
	 * the tank preview taken from server.
	 */
	public static void loadTankPreview()
	{
		HashMap<String, Object> datas = Model.getPlayerSettingsDatas();
		APIObject request = new APIObject();
		
		request.put("method", "loadImage");
		request.put("type", "tank");
		request.put("name", datas.get("Selected Tank").toString());
		
		System.out.println(request);
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		System.out.println(response);
		byte[] image = (byte[]) response.get("image");
		String name = request.get("name").toString();
		View.setTankPreview(FileUtils.convertByteArrayToImage(image, name));
	}
	
	/**
	 * Refreshes the game list in the Join Game panel.
	 */
	public static void refreshGameList()
	{
		APIObject request = new APIObject();
		
		request.put("method", "getGamesArray");
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
		}
		
		View.setGamesList((String[])response.get("gamesArray"));
	}
	
	/**
	 * Updates the playing stats in the Stats Settings.
	 */
	public static void updatePlayingStats()
	{
		APIObject request = new APIObject();
		
		request.put("method", "getUserPlayingStats");
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		String hours = response.get("playingHours").toString();
		String winningTimesM = response.get("winningTimesM").toString();
		String winningTimesS = response.get("winningTimesS").toString();
		View.setPlayingStats(hours, winningTimesS, winningTimesM);
	}
	
	/**
	 * Fetches the list of available tanks from server.
	 */
	public static void updateTankList()
	{
		APIObject request = new APIObject();
		
		request.put("method", "getTanksArray");
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		String[] items = (String[]) response.get("tanksArray");
		View.setTankColorsList(items);
	}
	
	/**
	 * Fetches the list of available maps from server.
	 */
	public static void updateMapList()
	{
		APIObject request = new APIObject();
		
		request.put("method", "getMapsArray");
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		String[] items = (String[]) response.get("mapsArray");
		View.setCreateGameMapList(items);
		View.setPlayWithAIMapList(items);
	}
	
	/**
	 * Displays an error message box with the given message.
	 * @param message The message to be displayed in message box.
	 */
	public static void showError(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Begins the game loop which sends requests and gets responses.
	 */
	/*public static void runUpdateLoop()
	{

		APIObject request = new APIObject();
	
		request.put("method", "updateGameUI");
		
		APIObject response = null;
		try
		{
			while(true)
			{
				response = sendSimpleRequest(request);
				GameState state = (GameState) response.get("gameState");
				Model.getGameFrame().render(state);
				Thread.sleep(50);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Sends a tank action request.
	 */
	public static void tankAction(int... actions)
	{
		APIObject request = new APIObject();
		
		request.put("method", "tankAction");
		request.put("actions", actions);
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{}
	}
	
	public static boolean beginGame()
	{
		APIObject request = new APIObject();
		
		request.put("method", "beginGame");
		
		try
		{
			sendSimpleRequest(request);
		} catch (Exception e)
		{
			showError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Gets a list of players in the joined or created game.
	 */
	public static void updateGameIntro()
	{
		APIObject request = new APIObject();
		
		
		
		request.put("method", "updateGameIntro");
		
		APIObject response = null;
		try
		{
			response = sendSimpleRequest(request);
		} 
		catch (Exception e)
		{
			return;
		}
		
		String[] items = (String[]) response.get("playersArray");
		boolean enable = (Boolean) response.get("enableButton");
		boolean gameIsStarted = (Boolean) response.get("gameIsStarted");
		View.sentGameIntroListAndButtons(items, enable, gameIsStarted);
	}

}
