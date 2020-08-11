package client.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import client.Controller;
import client.menu.mcomponents.MImage;
import utils.SpringUtilities;

/**
 * This page includes the game registration form including username, password
 * and full name.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class RegisterPanel extends JPanel
{
	private static final long serialVersionUID = -8040152443079811368L;
	
	/**
	 * Creates all components on this page.
	 */
	public RegisterPanel()
	{
		super();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,180));
		setBorder(new TitledBorder("Register"));
		
		JPanel fields = new JPanel(new SpringLayout());									// Creating Fields
		JTextField username = new JTextField();
		JTextField password = new JTextField();
		JTextField fullName = new JTextField();
		fields.add(new JLabel("Username:"));
		fields.add(username);
		fields.add(new JLabel("Password:"));
		fields.add(password);
		fields.add(new JLabel("Full Name:"));
		fields.add(fullName);
		SpringUtilities.makeCompactGrid(fields, 3, 2, 5, 5, 5, 5);
		add(fields,BorderLayout.CENTER);
		
		JPanel imagePreviewPanel = new JPanel(new GridBagLayout());
		imagePreviewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		MImage imagePreview = new MImage(100);
		imagePreviewPanel.add(imagePreview);
		add(imagePreviewPanel, BorderLayout.EAST);
		
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));					// Creating buttons
		JButton register = new JButton("Register");
		JButton cancel = new JButton("Cancel");
		buttons.add(register);
		buttons.add(cancel);
		add(buttons,BorderLayout.SOUTH);
		
		String prefix = "Register - ";
		MainFrame.addImportantComponent(prefix + "Username", username);					// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Password", password);
		MainFrame.addImportantComponent(prefix + "Full Name", fullName);
		MainFrame.addImportantComponent(prefix + "Image Preview", imagePreview);
		
		EventHandler h = new EventHandler();											// Adding event handlers
		register.addActionListener(h);
		cancel.addActionListener(h);
	}
	
	/**
	 * Event handlers of this page.
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			switch( ((JButton)e.getSource()).getText() )
			{
			case "Register":
				boolean done = Controller.register();
				if(done) Controller.goToPage("Login");
				break;
			case "Cancel":
				Controller.goToPage("Login");
				break;
			}
		}
		
	}
}
