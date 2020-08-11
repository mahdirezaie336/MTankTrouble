package client.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.Controller;
import client.menu.mcomponents.ButtonTabbedPane;
import client.menu.mcomponents.GlassButton;
import client.menu.mcomponents.MImage;
import utils.SpringUtilities;
/**
 * This class is the setting menu TabbedPane.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class Settings extends ButtonTabbedPane
{
	private static final long serialVersionUID = 7136892895609245729L;
	
	/**
	 * Creates all components.
	 */
	public Settings()
	{
		super(4,BorderLayout.EAST,120);
		
		JPanel stats = new JPanel(new SpringLayout()); 												// Creating settings buttons
		addTab("Stats", stats);
		JPanel playerSettings = new JPanel(new SpringLayout()); 
		addTab("Player Settings", playerSettings);
		JPanel serverSettings = new JPanel(new SpringLayout());
		addTab("Server Setting", serverSettings);
		JPanel userSettings = new JPanel(new SpringLayout());
		addTab("User Settings", userSettings);
		
		
		//stats.setOpaque(false);
		stats.setBackground(new Color(0,0,0,150));
		JPanel statsContent = new JPanel(new SpringLayout());										// Creating stats content
		//statsContent.setBackground(new Color(0,0,0,150));
		statsContent.setBorder(new TitledBorder("Player Stats"));
		JLabel l1 = new JLabel("Playing Hours:");
		JLabel l2 = new JLabel("Times Winning/Losing in Multiplayer:");
		JLabel l3 = new JLabel("Times Winning/Losing in Play with AI");
		JLabel hours = new JLabel("-1"); 
		JLabel winningTimesM = new JLabel("-1/-1");
		JLabel winningTimesS = new JLabel("-1/-1");
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		l3.setForeground(Color.white);
		hours.setForeground(Color.yellow);
		winningTimesM.setForeground(Color.yellow);
		winningTimesS.setForeground(Color.yellow);
		statsContent.add(l1); statsContent.add(hours);
		statsContent.add(l2); statsContent.add(winningTimesM);
		statsContent.add(l3); statsContent.add(winningTimesS);
		SpringUtilities.makeCompactGrid(statsContent, 3, 2, 10, 10, 25, 5);
		SpringUtilities.add(stats, statsContent, 5);
		
		
		//playerSettings.setOpaque(false);
		playerSettings.setBackground(new Color(0,0,0,150));
		JPanel tankInfo = new JPanel(new BorderLayout());											// Creating player settings content
		tankInfo.setBorder(BorderFactory.createTitledBorder("Player Tank"));
		JList<String> colorsList = new JList<>();
		JPanel tankPreviewPanel = new JPanel(new GridBagLayout());
		tankPreviewPanel.setPreferredSize(new Dimension(150,1));
		MImage tankPreview = new MImage(100);
		tankPreview.setPreferredSize(new Dimension(300,400));
		tankPreviewPanel.add(tankPreview);
		tankInfo.add(new JScrollPane(colorsList),BorderLayout.CENTER);
		tankInfo.add(tankPreviewPanel,BorderLayout.EAST);
		SpringUtilities.add(playerSettings, tankInfo, 5);
		GlassButton applyPlayerSettings = new GlassButton("Apply",Color.DARK_GRAY);
		SpringUtilities.addToBottonOf(playerSettings, tankInfo, applyPlayerSettings, 5);
		
		
		//serverSettings.setOpaque(false);
		serverSettings.setBackground(new Color(0,0,0,150));
		JPanel serverChoose = new JPanel(new BorderLayout());										// Creating server settings content
		serverChoose.setBorder(new TitledBorder("Change Server"));
		serverChoose.setPreferredSize(new Dimension(100,300));
		JList<String> serverList = new JList<String>();
		JPanel connectionFields = new JPanel(new SpringLayout());
		JTextField serverAddress = new JTextField();
		GlassButton refresh = new GlassButton("Refresh",Color.DARK_GRAY);
		GlassButton connect = new GlassButton("Connect",Color.DARK_GRAY);
		connectionFields.add(new JLabel("Address"));
		connectionFields.add(serverAddress);
		connectionFields.add(connect);
		connectionFields.add(refresh);
		SpringUtilities.makeCompactGrid(connectionFields, 1, 4, 5, 5, 5, 5);
		serverChoose.add(new JScrollPane(serverList),BorderLayout.CENTER);
		serverChoose.add(connectionFields,BorderLayout.SOUTH);
		SpringUtilities.add(serverSettings, serverChoose, 5);
		
		
		//userSettings.setOpaque(false);
		userSettings.setBackground(new Color(0,0,0,150));
		JPanel userInformation = new JPanel(new BorderLayout());									// Creating user settings content
		userInformation.setBorder(new TitledBorder("User Information"));
		//userInformation.setPreferredSize(new Dimension(100,100));
		JPanel userFields = new JPanel(new SpringLayout());
		JPanel userPicturePanel = new JPanel(new GridBagLayout());
		userPicturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		userFields.setOpaque(false);
		userFields.add(new JLabel("Full name:"));
		JTextField fName = new JTextField();
		userFields.add(fName);
		userFields.add(new JLabel("Username"));
		JTextField uName = new JTextField();
		userFields.add(uName);
		userFields.add(new JLabel("Password"));
		JTextField passwd = new JTextField();
		userFields.add(passwd);
		GlassButton applyUserSettings = new GlassButton("Apply Changes",Color.DARK_GRAY);
		SpringUtilities.makeCompactGrid(userFields, 3, 2, 5, 5, 25, 5);
		MImage profilePicture = new MImage(100);
		userPicturePanel.add(profilePicture);
		userInformation.add(userFields,BorderLayout.CENTER);
		userInformation.add(userPicturePanel,BorderLayout.EAST);
		SpringUtilities.add(userSettings, userInformation, 5);
		SpringUtilities.addToBottonOf(userSettings, userInformation, applyUserSettings, 5);
		
		String prefix = "Main Menu - Settings - ";
		MainFrame.addImportantComponent(prefix + "Playing Hours", hours);							// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Winning Times in Multiplayer", winningTimesM);
		MainFrame.addImportantComponent(prefix + "Winning Times in Singleplayer", winningTimesS);
		MainFrame.addImportantComponent(prefix + "Tank Colors List", colorsList);
		MainFrame.addImportantComponent(prefix + "Tank Preview", tankPreview);
		MainFrame.addImportantComponent(prefix + "Server List", serverList);
		MainFrame.addImportantComponent(prefix + "Server Address Field", serverAddress);
		MainFrame.addImportantComponent(prefix + "Full Name Field", fName);
		MainFrame.addImportantComponent(prefix + "Username Field", uName);
		MainFrame.addImportantComponent(prefix + "Password Field", passwd);
		MainFrame.addImportantComponent(prefix + "Profile Image", profilePicture);
		
		EventHandler h = new EventHandler();														// Adding event handlers
		applyPlayerSettings.addActionListener(h);
		applyUserSettings.addActionListener(h);
		refresh.addActionListener(h);
		connect.addActionListener(h);
		getButtons()[0].addActionListener(h);
		getButtons()[1].addActionListener(h);
		colorsList.addListSelectionListener(h);
		
	}
	
	/**
	 * Event handler of this class components
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener, ListSelectionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String text = ((JButton)e.getSource()).getText();
			switch(text)
			{
			case "Apply":
				Controller.changePlayerSettings();
				break;
			case "Apply Changes":
				Controller.changeUserInformation();
				break;
			case "Refresh":
				Thread th = new Thread(new Controller("refreshServerList"));
				th.start();
				break;
			case "Connect":
				boolean done = Controller.changeServer();
				if(done) 
				{
					MainFrame.reset();
					Controller.goToPage("Login");
				}
				break;
			case "Stats":
				Controller.updatePlayingStats();
				break;
			case "Player Settings":
				Controller.updateTankList();
				break;
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if(e.getSource() == MainFrame.getImportantComponent("Main Menu - Settings - Tank Colors List"))
			{
				Thread th = new Thread(new Controller("loadTankPreview"));
				th.start();
			}else if(e.getSource() == MainFrame.getImportantComponent("Main Menu - Settings - Server List"))
			{
				Controller.loadTextFieldOfChangeServerPanel();
			}
		}
		
	}
}
