package com.faithbj.shop.dao;

import com.faithbj.shop.entity.Footer;

/**
 * Dao接口 - 页面底部信息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface FooterDao extends BaseDao<Footer, String> {
	
	/**
	 * 获取Footer对象
	 * 
	 * @return Footer对象
	 * 
	 */
	public Footer getFooter();

}
