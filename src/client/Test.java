package client;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

import client.menu.mcomponents.MImage;
import shared.APIObject;
import utils.FileUtils;

public class Test extends JFrame
{
	private static final long serialVersionUID = 1L;
	private MImage image;
	
	public Test(String arg0) throws HeadlessException
	{
		super(arg0);
		setSize(800, 600);
		image = new MImage(300);
		add(image);
		
		image.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("Trying...");
				doa();
				System.out.println("Done!");
			}
		});
		
	}
	
	public void doa()
	{
		try
		{
			ClientSession.getInstance().setConnection("localhost");
			
			APIObject request = new APIObject();
			request.put("method", "loadImage");
			request.put("type", "tank");
			request.put("name", "tank_0");
			ClientSession.getInstance().sendObject(request);
			
			APIObject response = ClientSession.getInstance().getObject();
			byte[] array = (byte[]) response.get("image");
			BufferedImage bimage = FileUtils.convertByteArrayToImage(array, "tank_0");
			image.setImage(bimage);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new Test("a").setVisible(true);
	}

}
