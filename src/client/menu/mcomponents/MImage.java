package client.menu.mcomponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * This class is panel that holds an image and displays it.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class MImage extends JPanel
{
	private static final long serialVersionUID = -8349265314963733463L;
	private BufferedImage image;
	private Dimension size;
	
	/**
	 * Initializes with null image and a specific width. Height will be set automatically.
	 * 
	 * @param width The button width
	 */
	public MImage(int width)
	{
		this(new File("./images/null.jpg"),width);
	}
	
	/**
	 * Initializes with the specified image and the that image size.
	 * @param file The image file
	 */
	public MImage(File file)
	{
		super();
		try
		{
			image = ImageIO.read(file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		size = new Dimension(image.getWidth(),image.getHeight());
	}
	
	/**
	 * Initializes with the specified image and a specified width. Height will be set automatically.
	 * @param file The image file
	 * @param width The image width
	 */
	public MImage(File file, int width)
	{
		super();
		try
		{
			image = ImageIO.read(file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		int height = (int) (((double)width)/image.getWidth()*image.getHeight());
		size = new Dimension(width,height);
	}
	
	/**
	 * Overrides the get preferred size to make size stable.
	 */
	public Dimension getPreferredSize()
	{
		return size;
	}
	
	/**
	 * Changes the image.
	 * @param image The new image
	 */
	public void setImage(BufferedImage image)
	{
		this.image = image;
		updateUI();
	}
	
	/**
	 * Gets the image.
	 * @return The image
	 */
	public BufferedImage getImage()
	{
		return image;
	}
	
	/**
	 * Paints the image file into panel.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, (int) size.getWidth(), (int) size.getHeight(), null);
	}
}
