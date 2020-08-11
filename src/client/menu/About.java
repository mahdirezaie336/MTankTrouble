package client.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * This class contain a JTextArea and shows the content of the file "./about.txt".
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class About extends JPanel
{
	private static final long serialVersionUID = -9204064347753492650L;
	
	/**
	 * Creates components.
	 */
	public About()
	{
		super(new BorderLayout());
		setBackground(new Color(0,0,0,150));
		setBorder(BorderFactory.createTitledBorder("About"));
		
		String aboutContent = "";
		try(FileInputStream fs = new FileInputStream("./about.txt"))
		{
			byte[] buffer = new byte[2048];
			int nRead = -1;
			while((nRead = fs.read(buffer)) != -1)
				aboutContent += new String(buffer, 0, nRead);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		JTextArea ta = new JTextArea();
		ta.setText(aboutContent);
		ta.setEditable(false);
		add(new JScrollPane(ta),BorderLayout.CENTER);
	}

}
