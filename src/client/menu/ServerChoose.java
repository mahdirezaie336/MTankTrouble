package client.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import utils.SpringUtilities;

/**
 * This is the first shown page which wants the user to choose server.
 * It has a list and which can be updated by refresh button. On refresh,
 * it loads the servers in the file "servers.txt" which located in the
 * src directory.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ServerChoose extends JPanel
{
	private static final long serialVersionUID = 1443062313419980197L;
	
	/**
	 * Creates all of components on this page.
	 */
	public ServerChoose()
	{
		super();
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Server Choose"));
		setPreferredSize(new Dimension(600,400));
		
		JList<String> serverList = new JList<String>();										// Creating main components
		JPanel connectionFields = new JPanel(new SpringLayout());
		JTextField serverAddress = new JTextField();
		JButton refresh = new JButton("Refresh");
		JButton connect = new JButton("Connect");
		connectionFields.add(new JLabel("Address"));
		connectionFields.add(serverAddress);
		connectionFields.add(connect);
		connectionFields.add(refresh);
		SpringUtilities.makeCompactGrid(connectionFields, 1, 4, 5, 5, 5, 5);
		add(new JScrollPane(serverList),BorderLayout.CENTER);
		add(connectionFields,BorderLayout.SOUTH);
		
		String prefix = "Server Choose - ";
		MainFrame.addImportantComponent(prefix + "Server List", serverList);				// Making components accessible to event handler
		MainFrame.addImportantComponent(prefix + "Server Address Field", serverAddress);
		
		EventHandler h = new EventHandler();												// Adding event handlers
		refresh.addActionListener(h);
		connect.addActionListener(h);
		serverList.addListSelectionListener(h);
	}
	
	/**
	 * Event handlers of this page.
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class EventHandler implements ActionListener, ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent arg0)
		{
			Controller.loadTextFieldOfFirstServerPage();
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			switch( ((JButton)arg0.getSource()).getText() )
			{
			case "Refresh":
				Controller.refreshServerList();
				break;
			case "Connect":
				boolean done = Controller.connectToServer();
				if(done) Controller.goToPage("Login");
				break;
			}
		}
		
	}
}
