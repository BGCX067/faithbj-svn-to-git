package com.faithbj.shop.dao;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Members;

/**
 * Dao接口 - 会员注册协议
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
public interface AccountDao extends BaseDao<Accounts, String> {

	/**
	 * 获取Accounts对象
	 * 
	 * @return Accounts对象
	 * 
	 */
	public Accounts get(String id);

	public Pager findByPager(Pager pager, Members memberid);

}
