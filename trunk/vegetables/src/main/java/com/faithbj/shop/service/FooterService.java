package com.faithbj.shop.service;

import com.faithbj.shop.model.entity.Footer;

/**
 * Service接口 - 页面底部信息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface FooterService extends BaseService<Footer, String> {

	/**
	 * 获取Footer
	 * 
	 * @return Footer
	 * 
	 */
	public Footer getFooter();

}
