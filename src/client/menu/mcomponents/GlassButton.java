package client.menu.mcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This class is a child of JButton which i customize it to be looked flat and dark.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class GlassButton extends JButton
{
	private static final long serialVersionUID = -272318668571009048L;
	private Color color;
	private Color hover;
	private Color pressed;
	
	/**
	 * Creates a button with color Red=10 Green=10 blue=10 and the specified text.
	 * @param arg0 The button text.
	 */
	public GlassButton(String arg0)
	{
		this(arg0,new Color(10,10,10),new Color(10,10,10).brighter().brighter(),
				new Color(10,10,10).brighter().brighter().brighter(),Color.white);
	}
	
	/**
	 * Creates a button with the specified color and text.
	 * @param arg0 The button text
	 * @param color The button color
	 */
	public GlassButton(String text, Color color)
	{
		this(text,color,color.brighter(),color.brighter().brighter(),Color.white);
	}
	
	/**
	 * Creates a button with specified text, color, mouse in color, pressed color and text color.
	 * @param text The button text
	 * @param color The button color
	 * @param hover The button mouse in color
	 * @param pressed The button pressed color
	 * @param textColor The button text color
	 */
	public GlassButton(String text, Color color, Color hover, Color pressed, Color textColor)
	{
		super(text);
		this.color = color;
		this.hover = hover;
		this.pressed = pressed;
		setForeground(textColor);

		setUI(new BasicButtonUI());
		setOpaque(true);
		setBackground(color);
		setBorder(BorderFactory.createLineBorder(color.darker(), 1));
		addMouseListener(new MouseHandler());
		
		Dimension size = getPreferredSize();
		setPreferredSize(new Dimension(size.width + 20, size.height + 10));
	}
	
	/**
	 * This event handler changes the color of button.
	 * 
	 * @author Mahdi Rezaie 9728040
	 *
	 */
	private class MouseHandler implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			setBackground(hover);
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			setBackground(color);
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			setBackground(pressed);
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			setBackground(hover);
		}
		
	}
}
