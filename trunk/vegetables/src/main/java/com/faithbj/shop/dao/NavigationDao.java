package com.faithbj.shop.dao;

import java.util.List;

import com.faithbj.shop.model.entity.Navigation;

/**
 * Dao接口 - 导航
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface NavigationDao extends BaseDao<Navigation, String> {

	/**
	 * 获取顶部Navigation对象集合（只包含isVisible=true的对象）
	 * 
	 * @return Navigation对象集合
	 * 
	 */
	public List<Navigation> getTopNavigationList();
	
	/**
	 * 获取中间Navigation对象集合（只包含isVisible=true的对象）
	 * 
	 * @return Navigation对象集合
	 * 
	 */
	public List<Navigation> getMiddleNavigationList();
	
	/**
	 * 获取底部Navigation对象集合（只包含isVisible=true的对象）
	 * 
	 * @return Navigation对象集合
	 * 
	 */
	public List<Navigation> getBottomNavigationList();
	
}
