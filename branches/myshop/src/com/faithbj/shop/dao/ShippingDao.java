package com.faithbj.shop.dao;

import com.faithbj.shop.entity.Shipping;

/**
 * Dao接口 - 发货
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface ShippingDao extends BaseDao<Shipping, String> {
	
	/**
	 * 获取最后生成的发货编号
	 * 
	 * @return 发货编号
	 */
	public String getLastShippingSn();

}
