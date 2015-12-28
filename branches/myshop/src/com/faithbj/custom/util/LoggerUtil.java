package com.faithbj.custom.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

/**
 * 
 * <p>Title: 日志工具</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-02-03
 * @version 1.0
 */

public class LoggerUtil {
	
	/**
	 * 土地日志
	 */
	public static void fieldInfo(Object msg)
	{
		Logger.getLogger("fieldLogger").info(msg);
	}
	
	
	
	//***********************************************************
	//system
	//***********************************************************
	/**debugLogger*/
	public static void debug(Object msg) {
		Logger.getLogger("debugLogger").debug(msg);
	}
	
	/** 人工干预的报警logger */
	public static void alarmInfo(Object msg) {
		Logger.getLogger("alarmLogger").info(msg); 
	}
	
	/** 缓存logger */
	public static void alarmInfo(String msg, Exception e) {
		printAlarm(Logger.getLogger("alarmLogger"), msg, e);
	}

	public static void printAlarm(Logger logger, String msg, Exception e) {
		if (msg != null) {
			logger.info(msg);
		}
		if (e != null) {
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(buf, true));
			logger.error(buf.toString());
		}
	}
	
	/**errorLogger*/
	public static void error(String msg, Exception e) {
		printError(Logger.getLogger("errorLogger"), msg, e);
	}
	
	public static void printError(Logger logger, String msg, Exception e) {
		if (msg != null) {
			logger.error(msg);
		}
		if (e != null) {
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(buf, true));
			logger.error(buf.toString());
		}
	}
	/**errorLogger*/
	public static void error(String msg, Throwable e) {

		printError(Logger.getLogger("errorLogger"), msg, e);
	}
	
	public static void printError(Logger logger, String msg, Throwable e) {
		if (msg != null) {
			logger.error(msg);
		}
		if (e != null) {
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(buf, true));
			logger.error(buf.toString());
		}
	}
	
	
}
