package client.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import client.Controller;
import client.menu.mcomponents.GlassButton;
import utils.SpringUtilities;
/**
 * This class is panel which will be shown in Main Menu page when user clicks
 * on the Join Game Button. It shows an available games list on the connected
 * server.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class JoinGamePanel extends JPanel
{
	private static final long serialVersionUID = 4233094076210619944L;
	
	/**
	 * Creates all components on this page.
	 */
	public JoinGamePanel()
	{
		super(new SpringLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(0,0,0,150));
		
		JPanel options = new JPanel(new BorderLayout());										// Creating objects panel
		options.setBorder(BorderFactory.createTitledBorder("Games list in this sever"));
		JList<String> gamesList = new JList<String>();
		gamesList.setPreferredSize(new Dimension(100,200));
		JPanel buttons = new JPanel(new GridLayout(1,2));
		GlassButton join = new GlassButton("Join",Color.DARK_GRAY);
		GlassButton refersh = new GlassButton("Refresh",Color.DARK_GRAY);
		buttons.add(refersh);
		buttons.add(join);
		options.add(gamesList,BorderLayout.CENTER);
		options.add(buttons,BorderLayout.SOUTH);
		
		SpringUtilities.add(this, options, 5);
		
		String prefix = "Main Menu - Join Game - ";
		MainFrame.addImportantComponent(prefix + "Games List", gamesList);						// Making components accessible to event handler
		
		EventHandler h = new EventHandler();													// Add event handlers
		refersh.addActionListener(h);
		join.addActionListener(h);
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
			String text = ((JButton) e.getSource()).getText();
			
			switch(text)
			{
			case "Refresh":
				Controller.refreshGameList();
				break;
			case "Join":
				boolean done = Controller.joinGame();
				if(done)
				{
					GameIntro.setCreatorMode(false);
					Controller.goToPage("Game Intro");
				}
				break;
			}
		}
		
	}
}
