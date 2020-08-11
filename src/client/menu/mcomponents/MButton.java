package client.menu.mcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;

public class MButton extends JButton
{
	private static final long serialVersionUID = -1480037812962719092L;
	private Color buttonColor;
	
	public MButton(String text)
	{
		super(text);
		
		buttonColor = new Color(251, 100, 0);
		setUI(new BasicButtonUI());
		setBorder(BorderFactory.createLineBorder(buttonColor.darker().darker(), 1));
		setBackground(buttonColor);
		addMouseListener(new MouseHandler());
	}
	
	private class MouseHandler implements MouseListener
	{
		private Border white;
		private Border def;
		
		public MouseHandler()
		{
			white = BorderFactory.createLineBorder(Color.white, 1);
			def = BorderFactory.createLineBorder(buttonColor.darker().darker(), 1);
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			setBorder(white);
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			setBorder(def);
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			setBackground(buttonColor.darker());
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			setBackground(buttonColor);
		}
		
	}
	
	public Dimension getPreferredSize()
	{
		Dimension size = super.getPreferredSize();
		return new Dimension(size.width + 30, size.height + 10);
	}
	
	/*public void paintComponent(Graphics g)
	{
		String text = getText();
		g.drawString(text, 0, 0);
	}*/
}
