package com.faithbj.shop.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>properties帮助类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-19
 * @version 1.0
 */

public class PropertyUtils {

//	private static Logger logger = Logger.getLogger(PropertyUtils.class);

	public static String config = "config";

	private ResourceBundle bundle = null;

	public PropertyUtils(String baseName, Locale local) {
		if (StringUtils.isBlank(baseName))
			baseName = config;
		if (local == null)
			local = new Locale("zh", "CN");
		try {
			bundle = ResourceBundle.getBundle(baseName, local);
		} catch (Exception e) {
//			logger.fatal(e.getMessage());
		}
	}

	public String getPropery(String key) {
		String value = "";
		try {
			value = bundle.getString(key);
		} catch (Exception e) {
//			logger.fatal(e.getMessage());
		}
		return value;
	}

}
