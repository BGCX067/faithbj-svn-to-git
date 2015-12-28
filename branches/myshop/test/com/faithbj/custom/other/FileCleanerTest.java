package com.faithbj.custom.other;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import com.faithbj.custom.util.FileUtils;

public class FileCleanerTest extends TestCase
{
	private String srcFileDir = "d:\\workspace\\myshop\\src\\";
	private String templateFileDir = "f:\\galileoworkspace\\myshopsvn\\WebRoot\\WEB-INF\\template\\";
	
	private Pattern commentPattern = Pattern.compile("\\* ====([\\s\\S]*)====");
	
	public void testCleanComment() throws IOException
	{
		List<File> fileList = FileUtils.listFile(new File(srcFileDir));
		int i = 0;
		for (File file : fileList)
		{
			if (file.getName().endsWith(".java"))
			{
				System.out.println("********************************************" + i++);
				System.out.println(file.getAbsolutePath());
				
				String content = FileUtils.getFileContent(file);
				
				StringBuffer buf = new StringBuffer();
				
				Matcher matcher = commentPattern.matcher(content);
				if (matcher.find())
				{
					matcher.appendReplacement(buf, "* <p>Copyright: Copyright (c) 2011</p> \r\n" + 
						 " * \r\n" +
						 " * <p>Company: www.faithbj.com</p>\r\n" +
						 " * \r\n" +
						 " * @author 	faithbj\r\n" +
						 " * @date 	2011-12-16\r\n" +
						 " * @version 1.0");
					
					System.out.println(matcher.group());
				}
				matcher.appendTail(buf);
				
//				FileUtils.writeFile(file, buf.toString());
				
//				System.out.println(buf.toString());
			}
			
		}
	}
	
	public void testChangePackage() throws IOException
	{
		List<File> fileList = FileUtils.listFile(new File(srcFileDir));
		int i = 0;
		for (File file : fileList)
		{
			if (file.getName().endsWith(".java"))
			{
				String content = FileUtils.getFileContent(file);
				
				if (content.contains("net.shopxx"))
				{
					System.out.println("********************************************" + i++);
					System.out.println(file.getAbsolutePath());
					
					System.out.println("contains");
					
					content = content.replaceAll("net.shopxx", "com.faithbj.shop");
					
//					FileUtils.writeFile(file, content);
				}
				
			}
		}
	}
	
	public void testCleanTemplateFile() throws IOException
	{
		List<File> fileList = FileUtils.listFile(new File(templateFileDir));
		int i = 0;
		for (File file : fileList)
		{
			if (file.getName().endsWith(".ftl"))
			{
				String content = FileUtils.getFileContent(file);
				
				if (content.contains("CAIJINGLING"))
				{
					System.out.println("********************************************" + i++);
					System.out.println(file.getAbsolutePath());
					
					System.out.println("contains");
					
					content = content.replaceAll("SHOP\\+\\+", "MyShop");
					
//					System.out.println(content);
					
					FileUtils.writeFile(file, content);
				}
				
			}
		}
	}
}
