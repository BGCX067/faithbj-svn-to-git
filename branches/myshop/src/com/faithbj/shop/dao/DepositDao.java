package com.faithbj.shop.dao;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Deposit;
import com.faithbj.shop.entity.Member;

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
	public Pager getDepositPager(Member member, Pager pager);

}
