package client;

import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import client.menu.MainFrame;
import client.menu.mcomponents.MImage;
/**
 * This class is implementation the Model class in MVC design pattern. It gets the required
 * data from frame and returns them in hash map. The key value of each part is described on
 * each method java docs.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
@SuppressWarnings("unchecked")
public class Model
{
	/**
	 * Gets the fields data from Server Choose page.
	 * 
	 * server address field --> "Address Field"
	 * selected server item --> "List Selected Address"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getChooseServerDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField field = (JTextField) MainFrame.getImportantComponent("Server Choose - Server Address Field");
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Server Choose - Server List");
		datas.put("Address Field", field.getText());
		datas.put("List Selected Address", list.getSelectedValue());
		
		return datas;
	}
	
	/**
	 * Gets the fields data from Login page.
	 * 
	 * username field --> "Username"
	 * password field --> "Password"
	 * remember check box --> "Remember Info"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getLoginDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField username = (JTextField) MainFrame.getImportantComponent("Login - Username Field");
		JTextField password = (JTextField) MainFrame.getImportantComponent("Login - Password Field");
		JCheckBox remember = (JCheckBox) MainFrame.getImportantComponent("Login - Remember CheckBox");
		datas.put("Username", username.getText());
		datas.put("Password", password.getText());
		datas.put("Remember Info", remember.isSelected());
		
		return datas;
	}
	
	/**
	 * Gets the fields data from Register page.
	 * 
	 * username field --> "Username"
	 * password field --> "Password"
	 * full name field --> "Full Name"
	 * profile image --> "Profile Image"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getRegisterDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField username = (JTextField) MainFrame.getImportantComponent("Register - Username");
		JTextField password = (JTextField) MainFrame.getImportantComponent("Register - Password");
		JTextField fullName = (JTextField) MainFrame.getImportantComponent("Register - Full Name");
		MImage image = (MImage) MainFrame.getImportantComponent("Register - Image Preview");
		datas.put("Username", username.getText());
		datas.put("Password", password.getText());
		datas.put("Full Name", fullName.getText());
		datas.put("Profile Image", image.getImage());
		
		return datas;
	}
	
	/**
	 * Gets the fields data from Create Game panel.
	 * 
	 * game name field --> "Game Name"
	 * maximum players field --> "Maximum Players"
	 * minimum players field --> "Minimum Players"
	 * selected item in map list --> "Map List Selected"
	 * every one is enemy check box --> "Single in Team"
	 * league mode check box --> "League Mode"
	 * tank health field --> "Tank Health"
	 * wall health field --> "Wall Health"
	 * bullet damage field --> "Bullet Damage"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getCreateGameDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField gameName = (JTextField) MainFrame.getImportantComponent("Main Menu - Create Game - Game Name Field");
		JSpinner min = (JSpinner) MainFrame.getImportantComponent("Main Menu - Create Game - Minimum Players");
		JSpinner max = (JSpinner) MainFrame.getImportantComponent("Main Menu - Create Game - Maximum Players");
		JRadioButton single = (JRadioButton) MainFrame.getImportantComponent("Main Menu - Create Game - Everyone is Enemy");
		JRadioButton league = (JRadioButton) MainFrame.getImportantComponent("Main Menu - Create Game - League Mode");
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Create Game - Map List");
		JSpinner tankHealth = (JSpinner) MainFrame.getImportantComponent("Main Menu - Create Game - Tank Health");
		JSpinner wallHealth = (JSpinner) MainFrame.getImportantComponent("Main Menu - Create Game - Wall Health");
		JSpinner bulletDamage = (JSpinner) MainFrame.getImportantComponent("Main Menu - Create Game - Bullet Damage");
		
		datas.put("Game Name", gameName.getText());
		datas.put("Minimum Players", min.getValue());
		datas.put("Maximum Players", max.getValue());
		datas.put("Map List Selected", list.getSelectedValue());
		datas.put("Single in Team", single.isSelected());
		datas.put("League Mode", league.isSelected());
		datas.put("Tank Health", tankHealth.getValue());
		datas.put("Wall Health", wallHealth.getValue());
		datas.put("Bullet Damage", bulletDamage.getValue());
		
		return datas;
	}

	/**
	 * Gets the fields data from Register page.
	 * 
	 * selected item in game list --> "Selected Game"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getJoinGameDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();

		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Join Game - Games List");
		datas.put("Selected Game", list.getSelectedValue());
		
		return datas;
	}
	
	/**
	 * Gets the fields data from Create Game panel.
	 * 
	 * players number field --> "Players Number"
	 * selected item in map list --> "Map List Selected"
	 * league mode check box --> "League Mode"
	 * tank health field --> "Tank Health"
	 * wall health field --> "Wall Health"
	 * bullet damage field --> "Bullet Damage"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getPlayWithAIDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JSpinner number = (JSpinner) MainFrame.getImportantComponent("Main Menu - Play with AI - Players Number");
		JRadioButton league = (JRadioButton) MainFrame.getImportantComponent("Main Menu - Play with AI - League Mode");
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Play with AI - Map List");
		JSpinner tankHealth = (JSpinner) MainFrame.getImportantComponent("Main Menu - Play with AI - Tank Health");
		JSpinner wallHealth = (JSpinner) MainFrame.getImportantComponent("Main Menu - Play with AI - Wall Health");
		JSpinner bulletDamage = (JSpinner) MainFrame.getImportantComponent("Main Menu - Play with AI - Bullet Damage");
		
		datas.put("Players Number", number.getValue());
		datas.put("Map List Selected", list.getSelectedValue());
		datas.put("League Mode", league.isSelected());
		datas.put("Tank Health", tankHealth.getValue());
		datas.put("Wall Health", wallHealth.getValue());
		datas.put("Bullet Damage", bulletDamage.getValue());
		
		return datas;
	}

	/**
	 * Gets the fields data from Register page.
	 * 
	 * selected item in tank list --> "Selected Tank"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getPlayerSettingsDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Settings - Tank Colors List");
		datas.put("Selected Tank", list.getSelectedValue());
		
		return datas;
	}

	/**
	 * Gets the fields data from Register page.
	 * 
	 * username field --> "Username"
	 * password field --> "Password"
	 * full name field --> "Full Name"
	 * profile image --> "Profile Image"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getUserSettingsField()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField username = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Username Field");
		JTextField password = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Password Field");
		JTextField fullName = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Full Name Field");
		MImage image = (MImage) MainFrame.getImportantComponent("Main Menu - Settings - Profile Image");
		
		datas.put("Username", username.getText());
		datas.put("Password", password.getText());
		datas.put("Full Name", fullName.getText());
		datas.put("Profile Image", image.getImage());
		
		return datas;
	}
	
	/**
	 * Gets the fields data from Change Server panel.
	 * 
	 * server address field --> "Address Field"
	 * selected server item --> "List Selected Address"
	 * 
	 * @return The data in hash map
	 */
	public static HashMap<String, Object> getChangeServerDatas()
	{
		HashMap<String, Object> datas = new HashMap<String, Object>();
		
		JTextField field = (JTextField) MainFrame.getImportantComponent("Main Menu - Settings - Server Address Field");
		JList<String> list = (JList<String>) MainFrame.getImportantComponent("Main Menu - Settings - Server List");
		datas.put("Address Field", field.getText());
		datas.put("List Selected Address", list.getSelectedValue());
		
		return datas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
