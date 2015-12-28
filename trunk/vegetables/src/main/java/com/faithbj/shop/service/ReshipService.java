package com.faithbj.shop.service;

import com.faithbj.shop.model.entity.Reship;

/**
 * Service接口 - 退货
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface ReshipService extends BaseService<Reship, String> {

	/**
	 * 获取最后生成的退货编号
	 * 
	 * @return 退货编号
	 */
	public String getLastReshipSn();

}
