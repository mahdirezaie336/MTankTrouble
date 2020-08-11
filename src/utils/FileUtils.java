package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

/**
 * This class has some utilities about files.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class FileUtils
{
	/**
	 * Converts an array of bytes to a buffered image.
	 * @param array The array to be converted
	 * @param name The name of file or image
	 * @return The image
	 */
	public static BufferedImage convertByteArrayToImage(byte[] array, String name)
	{
		File file = new File("./data/temp/" + name);
		file.getParentFile().mkdirs();
		try
		{
			file.createNewFile();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		try(
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(file);
				)
		{
			fos.write(array);
			BufferedImage image = ImageIO.read(file);
			return image;
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Writes a string into a text file.
	 * @param fileAddress The file address
	 * @param text The text to be written in file
	 * @return If writes successfully returns true.
	 */
	public static boolean TextFileWrite(String fileAddress, String text)
	{
		File file = new File(fileAddress);
		file.mkdirs();
		
		try(
				FileOutputStream fos = new FileOutputStream(file);
				PrintWriter pw = new PrintWriter(fos);
				)
		{
			pw.println(text);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Reads a text file and returns it into a string object.
	 * @param fileAddress The file address
	 * @return The file into a String
	 */
	public static String TextFileRead(String fileAddress)
	{
		String text = "";
		
		try(
				FileInputStream fis = new FileInputStream(new File(fileAddress));
				)
		{
			byte[] buffer = new byte[4096];
			int nRead = -1;
			while((nRead = fis.read(buffer)) != -1)
				text += new String(buffer,0,nRead);
		} 
		catch (IOException e1)
		{
			System.err.println(e1.getMessage());
		}
		
		return text;
	}

}
