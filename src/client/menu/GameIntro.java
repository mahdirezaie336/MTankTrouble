package client.menu;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.Controller;



public class GameIntro extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static boolean creatorMode;
	private static boolean loopStop;

	public GameIntro()
	{
		super(new BorderLayout());
		setPreferredSize(new Dimension(480,320));
		setBorder(BorderFactory.createTitledBorder("List of players"));
		
		JList<String> listOfPlayers = new JList<String>();
		JButton ready = new JButton("Ready");
		ready.setEnabled(false);
		
		add(new JScrollPane(listOfPlayers), BorderLayout.CENTER);
		add(ready, BorderLayout.SOUTH);
		
		String prefix = "Game Intro - ";
		MainFrame.addImportantComponent(prefix + "List of Players", listOfPlayers);
		MainFrame.addImportantComponent(prefix + "Ready Button", ready);
		
		EventHandler h = new EventHandler();
		ready.addActionListener(h);
	}
	
	public static void setStopLoop(boolean state)
	{
		loopStop = state;
	}
	
	public static void setCreatorMode(boolean value)
	{
		creatorMode = value;
	}
	
	public static boolean getCreatorMode()
	{
		return creatorMode;
	}

	@Override
	public void run()
	{
		loopStop = false;
		while(!loopStop)
		{
			Controller.updateGameIntro();
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public class EventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			/*if(creatorMode)
			{
				boolean done = Controller.beginGame();
				if(!done) return;
			}
			
			loopStop = true;
			Thread t = new Thread(new Controller("runUpdateLoop"));
			Controller.goToPage("Main Menu");
			GameFrame frame = (GameFrame) MainFrame.getImportantComponent("Game Frame");
			MainFrame.getInstance().setVisible(false);
			frame.setVisible(true);
			frame.initBufferStrategy();
			t.start();*/
		}
		
	}

}
