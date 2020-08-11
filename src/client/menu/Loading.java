package client.menu;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import utils.AnimatedIcon;
import utils.SpringUtilities;

public class Loading extends JPanel
{
	private static final long serialVersionUID = -1721478897471209829L;

	public Loading()
	{
		setLayout(new SpringLayout());
		//setBackground(new Color(0,0,0,150));
		
		JLabel label = new JLabel("");
		AnimatedIcon animation = new AnimatedIcon(label, 200, -1);
		for(int i = 1; i < 13; ++i)
			animation.addIcon(new ImageIcon("./images/loading/"+i+".png"));
		
		label.setPreferredSize(new Dimension(400,200));
		label.setMinimumSize(new Dimension(850,480));
		animation.start();
		SpringUtilities.add(this, label, 5);
		add(new JLabel("loading..."));
		
		setPreferredSize(new Dimension(850,640));
		setSize(new Dimension(850,540));
		setMinimumSize(new Dimension(800,600));
	}

}
