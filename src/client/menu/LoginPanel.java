package client.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import client.Controller;
import utils.SpringUtilities;
/**
 * This class is the login page with will be displayed after the Choose Server
 * ran it codes successfully.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class LoginPanel extends JPanel
{
	private static final long serialVersionUID = 7512650703130373748L;
	
	/**
	 * Creates all component and event handlers.
	 */
	public LoginPanel()
	{
		super();
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Login"));
		setPreferredSize(new Dimension(300,150));
		
		JPanel loginFields = new JPanel(new SpringLayout());							// Creating fields and labels
		JLabel usernameLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");
		JTextField usernameField = new JTextField();
		JTextField passwordField = new JTextField();
		usernameLabel.setLabelFor(usernameField);
		passwordLabel.setLabelFor(passwordField);
		JCheckBox remember = new JCheckBox("Remember my login info");
		loginFields.add(usernameLabel);
		loginFields.add(usernameField);
		loginFields.add(passwordLabel);
		loginFields.add(passwordField);
		loginFields.add(new JLabel(""));
		loginFields.add(remember);
		SpringUtilities.makeCompactGrid(loginFields, 3, 2, 2, 2, 5, 5);
		add(loginFields,BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));					// Creating buttons
		JButton login = new JButton("Login");
		JButton exit = new JButton("Exit");
		JButton register = new JButton("Register");
		buttons.add(login);
		buttons.add(register);
		buttons.add(exit);
		add(buttons,BorderLayout.SOUTH);
		
		String prefix = "Login - ";
		MainFrame.addImportantComponent(prefix + "Username Field", usernameField);		// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Password Field", passwordField);
		MainFrame.addImportantComponent(prefix + "Remember CheckBox", remember);
		
		EventHandler h = new EventHandler();											// Adding event handlers
		login.addActionListener(h);
		exit.addActionListener(h);
		register.addActionListener(h);
		
	}
	
	/**
	 * Event handlers of this page.
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			switch( ((JButton)arg0.getSource()).getText() )
			{
			case "Exit":
				System.exit(0);
				break;
			case "Register":
				Controller.goToPage("Register");
				System.out.println();
				break;
			case "Login":
				boolean done = Controller.login();
				if(done) Controller.goToPage("Main Menu");
				break;
			}
		}
		
	}
}
