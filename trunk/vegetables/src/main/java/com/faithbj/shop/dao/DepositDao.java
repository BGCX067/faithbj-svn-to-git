package com.faithbj.shop.dao;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Deposit;
import com.faithbj.shop.model.entity.Members;

/**
 * Dao接口 - 预存款记录
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface DepositDao extends BaseDao<Deposit, String> {
	
	/**
	 * 根据Member、Pager获取充值记录分页对象
	 * 
	 * @param member
	 *            Member对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 充值记录分页对象
	 */
	public Pager getDepositPager(Members member, Pager pager);

}
