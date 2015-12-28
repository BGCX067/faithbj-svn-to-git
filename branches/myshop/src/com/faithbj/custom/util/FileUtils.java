package com.faithbj.custom.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title: File Utility</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-19
 * @version 1.0
 */

public class FileUtils
{
	private static String encoding = "UTF-8";
	
	public static List<File> listFile(File dir)
	{
		List<File> result = new ArrayList<File>();
		if (dir.isDirectory())
		{
			File[] files = dir.listFiles();
			for (File file : files)
			{
				result.addAll(listFile(file));
			}
		}
		else
		{
			result.add(dir);
		}
		
		return result;
	}
	
	public static void writeFile(File file, String fileContent) throws IOException
	{
		if (!file.exists())
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		BufferedWriter writer = null;
		
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			
			writer.write(fileContent);
			writer.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static String getFileContent(File file)
	{
		StringBuffer buf = new StringBuffer();
		
		BufferedReader reader = null;		
		String line = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			while ((line = reader.readLine()) != null)
			{
				buf.append(line);
				buf.append("\r\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return buf.toString();
	}
}
