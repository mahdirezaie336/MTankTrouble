package client.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import client.Controller;
import client.menu.mcomponents.ButtonTabbedPane;
import client.menu.mcomponents.GlassButton;

/**
 * This class is the main menu page which has a background image loads from
 * the path "./images/background.jpg". The size of this page is fixed on 
 * 850x480. It has a ButtonTabbedPane that is representation of the options
 * on the main menu.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class MainMenu extends JPanel
{
	private static final long serialVersionUID = -8508173416799862033L;
	
	/**
	 * Creates all components.
	 */
	public MainMenu()
	{
		super();
		setLayout(new BorderLayout(20,20));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		ButtonTabbedPane mainMenu = new ButtonTabbedPane(6,BorderLayout.EAST,200);					// Creating main menu buttons
		mainMenu.addTab("Create Game", new CreateGamePanel());
		mainMenu.addTab("Join Game", new JoinGamePanel());
		mainMenu.addTab("Play with AI", new PlayWithAIPanel());
		mainMenu.addTab("Settings", new Settings());
		mainMenu.addTab("About", new About());
		GlassButton exit = new GlassButton("Logout & Exit");
		mainMenu.addSingleButton(exit);
		add(mainMenu,BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(850,480));
		
		EventHandler h = new EventHandler();
		exit.addActionListener(h);
		mainMenu.getButtons()[0].addActionListener(h);
		mainMenu.getButtons()[2].addActionListener(h);
	}
	
	/**
	 * Sets the background image.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("./images/background.jpg"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, 853, 480, null);
	}
	
	/**
	 * Event handler of this page components.
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String text = ( (GlassButton) e.getSource() ).getText();
			
			switch(text)
			{
			case "Play with AI":
			case "Create Game":
				Controller.updateMapList();
				break;
			case "Logout & Exit":
				Controller.logout();
				System.exit(0);
				break;
			}
		}
		
	}
}
