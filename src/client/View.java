package client;

import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import client.menu.GameIntro;
import client.menu.MainFrame;
import client.menu.mcomponents.MImage;
/**
 * This class is implementation of the View class in MVC design pattern. It puts
 * the given data into frame.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
@SuppressWarnings("unchecked")
public class View
{
	/**
	 * Puts the given items into the server list in Server Choose page.
	 * @param items The items to be put
	 */
	public static void setFirstServerList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Server Choose - Server List");
		list.setListData(items);
	}
	
	/**
	 * Puts a text into the server address field in Server Choose page.
	 * @param text The text to be put
	 */
	public static void setFirstServerAddressField(String text)
	{
		JTextField field = (JTextField) MainFrame.getImportantComponent("Server Choose - Server Address Field");
		field.setText(text);
	}
	
	/**
	 * Puts saved username and password into the Login page.
	 * @param username The saved username
	 * @param password The saved password
	 */
	public static void setLoginSavedInfo(String username, String password)
	{
		JTextField uname = (JTextField) MainFrame.getImportantComponent("Login - Username Field");
		JTextField paswd = (JTextField) MainFrame.getImportantComponent("Login - Password Field");
		JCheckBox remember = (JCheckBox) MainFrame.getImportantComponent("Login - Remember CheckBox");
		
		uname.setText(username);
		paswd.setText(password);
		remember.setSelected(true);
	}
	
	/**
	 * Sets preview image in the Register page.
	 * @param image The image to be put
	 */
	public static void setRegisterImagePreview(BufferedImage image)
	{
		MImage imagePreview = (MImage) MainFrame.getImportantComponent("Register - Image Preview");
		imagePreview.setImage(image);
	}
	
	/**
	 * Puts the given items into the map list in Create Game panel.
	 * @param items The items to be put
	 */
	public static void setCreateGameMapList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Create Game - Map List");
		list.setListData(items);
		list.updateUI();
	}
	
	/**
	 * Puts an image to the map preview in Create Game panel.
	 * @param image The image to be put
	 */
	public static void setMapPreview(BufferedImage image)
	{
		MImage mapPreview = (MImage) MainFrame.getImportantComponent("Main Menu - Create Game - Map Preview");
		mapPreview.setImage(image);
	}
	
	/**
	 * Puts the given items into the games list in Join Game panel.
	 * @param items The items to be put
	 */
	public static void setGamesList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Join Game - Games List");
		list.setListData(items);
	}
	
	/**
	 * Puts the given items into the maps list in Play with AI panel.
	 * @param items The items to be put
	 */
	public static void setPlayWithAIMapList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Play with AI - Map List");
		list.setListData(items);
		list.updateUI();
	}
	
	/**
	 * Puts the given image into the map preview in Play with AI panel.
	 * @param image The image to be put
	 */
	public static void setPlayWithAIMapPreview(BufferedImage image)
	{
		MImage preview = (MImage) MainFrame.getImportantComponent("Main Menu - Play with AI - Map Preview");
		preview.setImage(image);
	}
	
	/**
	 * Sets the Settings - Stats fields.
	 * @param hour Playing hours
	 * @param winS Winning times in single player
	 * @param winM Winning times in multi player
	 */
	public static void setPlayingStats(String hour, String winS, String winM)
	{
		JLabel hours = (JLabel) MainFrame.getImportantComponent("Main Menu - Settings - Playing Hours");
		JLabel winnS = (JLabel) MainFrame.getImportantComponent("Main Menu - Settings - Winning Times in Singleplayer");
		JLabel winnM = (JLabel) MainFrame.getImportantComponent("Main Menu - Settings - Winning Times in Multiplayer");
		
		hours.setText(hour);
		winnS.setText(winS);
		winnM.setText(winM);
	}
	
	/**
	 * Puts the given items into the tank color list in Settings - Player Settings.
	 * @param items The items to be put
	 */
	public static void setTankColorsList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Settings - Tank Colors List");
		list.setListData(items);
		list.updateUI();
	}
	
	/**
	 * Puts the given image into tank preview in Settings - Player Settings
	 * @param image The image to be put
	 */
	public static void setTankPreview(BufferedImage image)
	{
		MImage preview = (MImage) MainFrame.getImportantComponent("Main Menu - Settings - Tank Preview");
		preview.setImage(image);
	}
	
	/**
	 * Puts the given items into the server list in Settings - Server.
	 * @param items The items to be put
	 */
	public static void setChangeServerList(String[] items)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Settings - Server List");
		list.setListData(items);
	}
	
	/**
	 * Puts the given text into the text field in Settings - Server.
	 * @param text The text to be put
	 */
	public static void setChangeServerField(String text)
	{
		JTextField field = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Server Address Field");
		field.setText(text);
	}
	
	/**
	 * Sets the user info in the Settings - User Settings.
	 * @param username The username
	 * @param password The password
	 * @param fullName The user full name
	 */
	public static void setUserIDsSettings(String username, String password, String fullName)
	{
		JTextField uname = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Username Field");
		JTextField paswd = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Password Field");
		JTextField fname = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Full Name Field");
		
		uname.setText(username);
		paswd.setText(password);
		fname.setText(fullName);
	}
	
	/**
	 * Puts the given image into profile preview in the Settings - User Settings.
	 * @param image The image to be put
	 */
	public static void setUserProfileSetting(BufferedImage image)
	{
		MImage preview = (MImage) MainFrame.getImportantComponent("Main Menu - Settings - Profile Image");
		preview.setImage(image);
	}
	
	/**
	 * Puts the given items into Game Intro page list.
	 * @param items The list items
	 * @param buttonEnabled If it is true sets the ready button enabled.
	 */
	public static void sentGameIntroListAndButtons(String[] items, boolean buttonEnabled, boolean gameIsStarted)
	{
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Game Intro - List of Players");
		JButton button = (JButton) MainFrame.getImportantComponent("Game Intro - Ready Button");
		
		list.setListData(items);
		boolean cmode = GameIntro.getCreatorMode();
		if(cmode && buttonEnabled) button.setEnabled(buttonEnabled);
		else if(!cmode && buttonEnabled && gameIsStarted) button.setEnabled(true);
	}
}
