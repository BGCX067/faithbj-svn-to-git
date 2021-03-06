package com.faithbj.shop.service;

import java.util.List;

import com.faithbj.shop.entity.Online;

/**
 * Service接口 - 在线问答
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface OnlineService extends BaseService<Online, String> {

    public List<Online> getAllQ();
	
	public List<Online> getAllA();
	

}
