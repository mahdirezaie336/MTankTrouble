package client.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import client.Controller;
import client.menu.mcomponents.PageLoadRun;

/**
 * This class is the main frame of the client-side game. It keeps many panels calling "Pages".
 * Its content pane has a GridBagLayout that makes the page to be at the center of the frame.
 * Size of every page can be set with preferred size. However, many pages with some layouts
 * does not match with this settings.
 * 
 * This class also has a static field called important components which keeps the components
 * on this package that must be available to the event handler. The event handlers for all of 
 * this package components are in the Model, View and Controller classes.
 * 
 * Using Singleton design pattern, it keeps the pages and to change the pages, the event handler
 * must first call the method getInstance(). Therefore it has just one instance which is accessible
 * by this static method.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 7353005168491381773L;
	private static HashMap<String, Component> importantComponents;					// Important components that must be available to event handler 
	private HashMap<String,JPanel> pages;											// JPanel which we call pages
	private HashMap<String, PageLoadRun> events;									// Codes that will run when a page loads
	
	private int width = 1080;														// size of frame that has 16:9 aspect ratio
	private int height = width / 16 * 9;
	
	/**
	 * Create the frame with title of "Tank Trouble" and also initializes the
	 * every pages on this package.
	 */
	private MainFrame()
	{
		super("Tank Trouble");
		importantComponents = new HashMap<String, Component>();
		//importantComponents.put("Game Frame", new GameFrame("Game"));
		//importantComponents.put("Main Frame", this);
		init();
	}
	
	/**
	 * This class is a helper class for the Bill Pugh Singleton design pattern that keeps
	 * the instance of the frame.
	 * 
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private static class Helper
	{
		private static MainFrame INSTANCE = new MainFrame();
	}
	
	/**
	 * Gets the kept instance of this frame. 
	 * @return The kept instance
	 */
	public static MainFrame getInstance()
	{
		return Helper.INSTANCE;
	}
	
	/**
	 * Changes the instance and makes the new frame visible.
	 */
	public static void reset()
	{
		Helper.INSTANCE.dispose();
		Helper.INSTANCE = new MainFrame();
		Helper.INSTANCE.setVisible(true);
	}
	
	/**
	 * Initializes all components and pages.
	 */
	private void init()
	{
		setSize(width, height);												// Setting up frame options
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.gray);
		try
		{
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	
		pages = new HashMap<String,JPanel>();								// Creating panels
		pages.put("Server Choose", new ServerChoose());
		pages.put("Login", new LoginPanel());
		pages.put("Register", new RegisterPanel());
		pages.put("Main Menu", new MainMenu());
		pages.put("Game Intro", new GameIntro());
		pages.put("Loading", new Loading());
		//pages.put("Game", new Game());
		
		add(pages.get("Server Choose"));									// Adding panels to frame
		
		events = new HashMap<String, PageLoadRun>();						// Adding page change events
		events.put("Login", new PageLoadRun()
		{
			
			@Override
			public void run()
			{
				Controller.loadLoginInfo();
			}
			
		});
		events.put("Game Intro", new PageLoadRun()
		{
			
			@Override
			public void run()
			{
				GameIntro i = (GameIntro) pages.get("Game Intro");
				Thread t = new Thread(i);
				t.start();
			}
		});
	};
	
	/**
	 * Changes the page if page is available.
	 * @param pageName The new page name
	 */
	public void goToPage(String pageName)
	{
		if(!pages.containsKey(pageName))
			return;
		
		getContentPane().removeAll();
		add(pages.get(pageName));
		if(events.containsKey(pageName))
			events.get(pageName).run();
		
		((JPanel)getContentPane()).updateUI();
	}
	
	/**
	 * Gets an important component which is stored in the important components HashMap. 
	 * @param s The component key in hash map.
	 * @return The desired component.
	 */
	public static Component getImportantComponent(String s)
	{
		return importantComponents.get(s);
	}
	
	/**
	 * Adds a new component to the important components hash map.
	 * @param name The new component key
	 * @param c The component to be stored
	 */
	public static void addImportantComponent(String name, Component c)
	{
		importantComponents.put(name, c);
	}
	
	/**
	 * The Driver
	 * @param args
	 */
	public static void main(String[] args)
	{
		getInstance().setVisible(true);
	}

}
