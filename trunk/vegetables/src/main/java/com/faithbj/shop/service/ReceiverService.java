package com.faithbj.shop.service;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Receiver;

/**
 * Service接口 - 收货地址
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface ReceiverService extends BaseService<Receiver, String> {
	/**
	 * @param member
	 * @return
	 */
	public Receiver getReceiver(Members member);
}
