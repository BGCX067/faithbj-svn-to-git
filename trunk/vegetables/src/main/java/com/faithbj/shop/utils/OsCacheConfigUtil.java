package com.faithbj.shop.utils;


/**
 * 工具类 - 缓存
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class OsCacheConfigUtil {
	
	public static final String GENERAL_CACHE_ADMINISTRATOR_BEAN_NAME = "cacheManager";// GeneralCacheAdministrator注入Bean名称
	
//	/**
//	 * 根据Key读取缓存
//	 * 
//	 * @return 缓存对象
//	 */
//	public static Object getFromCache(String key) {
//		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(GENERAL_CACHE_ADMINISTRATOR_BEAN_NAME);
//		Object object = null;
//		try {
//			object = generalCacheAdministrator.getFromCache(key);
//		} catch (NeedsRefreshException e) {
//			generalCacheAdministrator.cancelUpdate(key);
//		}
//		return object;
//	}
//	
//	/**
//	 * 加入对象到缓存
//	 * 
//	 */
//	public static void putInCache(String key, Object object) {
//		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(GENERAL_CACHE_ADMINISTRATOR_BEAN_NAME);
//		generalCacheAdministrator.putInCache(key, object);
//	}
//	
//	/**
//	 * 根据Key刷新缓存对象
//	 * 
//	 */
//	public static void flushEntry(String key) {
//		GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil.getBean(GENERAL_CACHE_ADMINISTRATOR_BEAN_NAME);
//		generalCacheAdministrator.flushEntry(key);
//	}

}
