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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.Controller;
import client.menu.mcomponents.GlassButton;
import client.menu.mcomponents.MImage;
import client.menu.mcomponents.PeriodicSpinnerModel;
import utils.SpringUtilities;

/**
 * This class is a panel that show on the Main Menu panel when user clicks on
 * Create Game button. This shows a form which is the information of the game
 * to be created.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class CreateGamePanel extends JPanel
{
	private static final long serialVersionUID = -2172159330891234482L;
	
	/**
	 * Creates all components.
	 */
	public CreateGamePanel()
	{
		super(new SpringLayout());
		setBackground(new Color(0,0,0,150));
		setBorder(BorderFactory.createTitledBorder("Create Game"));
		
		JPanel gameNameFields = new JPanel(new SpringLayout());								// Game name part
		JLabel l1 = new JLabel("Game name");
		JTextField gameNameField = new JTextField();
		JLabel l2 = new JLabel("Minimum Players");
		JSpinner minPlayersField = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		JLabel l3 = new JLabel("Maximum Players");
		JSpinner maxPlayersField = new JSpinner(new PeriodicSpinnerModel(1,100,1));
		gameNameFields.add(l1);
		gameNameFields.add(gameNameField);
		gameNameFields.add(l2);
		gameNameFields.add(minPlayersField);
		gameNameFields.add(l3);
		gameNameFields.add(maxPlayersField);
		SpringUtilities.makeCompactGrid(gameNameFields, 3, 2, 5, 5, 10, 5);

		JPanel teamSetting = new JPanel(new SpringLayout());								// Team Options
		teamSetting.add(new JLabel("Team options:"));
		JRadioButton single = new JRadioButton("Everyone is enemy");
		single.setSelected(true);
		JRadioButton twoTeams = new JRadioButton("Two teams");
		ButtonGroup teamRadioButtonsGroup = new ButtonGroup();
		teamRadioButtonsGroup.add(single);
		teamRadioButtonsGroup.add(twoTeams);
		teamSetting.add(single);
		teamSetting.add(twoTeams);
		SpringUtilities.makeCompactGrid(teamSetting, 1, 3, 5, 5, 10, 5);
		
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
		mapChoosePanel.setPreferredSize(new Dimension(100,150));
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
		
		
		GlassButton createGameButton = new GlassButton("Create",Color.DARK_GRAY);
		
		SpringUtilities.add(this, gameNameFields, 5);										// Adding to panel
		SpringUtilities.addToBottonOf(this, gameNameFields, teamSetting, 5);
		SpringUtilities.addToBottonOf(this, teamSetting, gameMode, 5);
		SpringUtilities.addToBottonOf(this, gameMode, mapChoosePanel, 5);
		SpringUtilities.addToBottonOf(this, mapChoosePanel, gameOptions, 5);
		SpringUtilities.addToBottonOf(this, gameOptions, createGameButton, 5);
		
		String prefix = "Main Menu - Create Game - ";
		MainFrame.addImportantComponent(prefix + "Game Name Field", gameNameField);			// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Minimum Players", minPlayersField);
		MainFrame.addImportantComponent(prefix + "Maximum Players", maxPlayersField);
		MainFrame.addImportantComponent(prefix + "Everyone is Enemy", single);
		MainFrame.addImportantComponent(prefix + "League Mode", league);
		MainFrame.addImportantComponent(prefix + "Map List", mapList);
		MainFrame.addImportantComponent(prefix + "Map Preview", mapPreview);
		MainFrame.addImportantComponent(prefix + "Tank Health", tankHealth);
		MainFrame.addImportantComponent(prefix + "Wall Health", wallHealth);
		MainFrame.addImportantComponent(prefix + "Bullet Damage", bulletDamage);
		
		EventHandler h = new EventHandler();												// Adding event handlers
		createGameButton.addActionListener(h);
		mapList.addListSelectionListener(h);
	}
	
	/**
	 * Event handler of this page components
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener, ListSelectionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			boolean done = Controller.createGame();
			if(done) 
			{
				GameIntro.setCreatorMode(true);
				Controller.goToPage("Game Intro");
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0)
		{
			Thread th = new Thread(new Controller("loadCreateGameMapPreview"));
			th.start();
		}
		
	}
}
