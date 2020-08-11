package client.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.Controller;
import client.menu.mcomponents.GlassButton;
import client.menu.mcomponents.MImage;
import client.menu.mcomponents.PeriodicSpinnerModel;
import utils.SpringUtilities;
/**
 * This class in a panel including a form to create a game to play with AI
 * and is show on the Main Menu page when user clicks on the Play with AI.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class PlayWithAIPanel extends JPanel
{
	private static final long serialVersionUID = 6302480464244115266L;
	
	/**
	 * Creates all components.
	 */
	public PlayWithAIPanel()
	{
		super(new SpringLayout());
		setBackground(new Color(0,0,0,150));
		setBorder(BorderFactory.createTitledBorder("Play with AI"));
		
		JPanel playersNumber = new JPanel(new SpringLayout());								// Number of players
		playersNumber.setMaximumSize(new Dimension(100,100));
		playersNumber.add(new JLabel("Number of players:"));
		JSpinner playersNumberField = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		playersNumber.add(playersNumberField);
		SpringUtilities.makeCompactGrid(playersNumber, 1, 2, 5, 5, 10, 5);
		
		JPanel gameMode = new JPanel(new SpringLayout());									// Game mode
		gameMode.add(new JLabel("Game mode:"));
		JRadioButton tilDeath = new JRadioButton("Play until death");
		tilDeath.setSelected(true);
		JRadioButton league = new JRadioButton("League");
		ButtonGroup gameModeRadioGroup = new ButtonGroup();
		gameModeRadioGroup.add(tilDeath);
		gameModeRadioGroup.add(league);
		gameMode.add(tilDeath);
		gameMode.add(league);
		SpringUtilities.makeCompactGrid(gameMode, 1, 3, 5, 5, 10, 5);

		JPanel mapChoosePanel = new JPanel(new BorderLayout());								// Map List
		mapChoosePanel.setPreferredSize(new Dimension(100,200));
		mapChoosePanel.setBorder(BorderFactory.createTitledBorder("Map"));
		JList<String> mapList = new JList<>();
		JPanel mapPreviewPanel = new JPanel(new GridBagLayout());
		mapPreviewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		MImage mapPreview = new MImage(100);
		mapPreviewPanel.add(mapPreview);
		mapChoosePanel.add(new JScrollPane(mapList),BorderLayout.CENTER);
		mapChoosePanel.add(mapPreviewPanel,BorderLayout.EAST);
		
		JPanel gameOptions = new JPanel(new SpringLayout());								// Game options
		gameOptions.setMaximumSize(new Dimension(100,100));
		JSpinner tankHealth = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		JSpinner wallHealth = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		JSpinner bulletDamage = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		gameOptions.add(new JLabel("Tank health:"));
		gameOptions.add(tankHealth);
		gameOptions.add(new JLabel("Breakable wall health:"));
		gameOptions.add(wallHealth);
		gameOptions.add(new JLabel("Bullets damage:"));
		gameOptions.add(bulletDamage);
		SpringUtilities.makeCompactGrid(gameOptions, 3, 2, 5, 5, 10, 4);
		
		GlassButton start = new GlassButton("Start",Color.DARK_GRAY);
		
		add(playersNumber);																	// Adding components to the panel
		add(gameMode);
		add(mapChoosePanel);
		add(gameOptions);
		SpringUtilities.add(this, playersNumber, 5);
		SpringUtilities.addToBottonOf(this, playersNumber, gameMode, 5);
		SpringUtilities.addToBottonOf(this, gameMode, mapChoosePanel, 5);
		SpringUtilities.addToBottonOf(this, mapChoosePanel, gameOptions, 5);
		SpringUtilities.addToBottonOf(this, gameOptions, start, 5);
		
		String prefix = "Main Menu - Play with AI - ";										// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Players Number", playersNumberField);
		MainFrame.addImportantComponent(prefix + "League Mode", league);
		MainFrame.addImportantComponent(prefix + "Map List", mapList);
		MainFrame.addImportantComponent(prefix + "Map Preview", mapPreview);
		MainFrame.addImportantComponent(prefix + "Tank Health", tankHealth);
		MainFrame.addImportantComponent(prefix + "Wall Health", wallHealth);
		MainFrame.addImportantComponent(prefix + "Bullet Damage", bulletDamage);
		
		EventHandler h = new EventHandler();												// Adding event handlers
		mapList.addListSelectionListener(h);
		start.addActionListener(h);
	}
	
	private class EventHandler implements ActionListener, ListSelectionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			/*boolean done = Controller.playWithAI();
			if(!done) return;
			
			done = Controller.beginGame();
			if(!done) return;
			
			Thread t = new Thread(new Controller("runUpdateLoop"));
			Controller.goToPage("Main Menu");
			GameFrame frame = (GameFrame) MainFrame.getImportantComponent("Game Frame");
			MainFrame.getInstance().setVisible(false);
			frame.setVisible(true);
			frame.initBufferStrategy();
			t.start();*/
		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			Thread th = new Thread(new Controller("loadAIGameMapPreview"));
			th.start();
		}
		
	}
}
