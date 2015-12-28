package com.faithbj.shop.dao;

import com.faithbj.shop.entity.Refund;

/**
 * Dao接口 - 退款
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface RefundDao extends BaseDao<Refund, String> {
	
	/**
	 * 获取最后生成的退款编号
	 * 
	 * @return 收款编号
	 */
	public String getLastRefundSn();
	
	/**
	 * 根据退款编号获取对象（若对象不存在，则返回null）
	 * 
	 * @return 退款对象
	 */
	public Refund getRefundByRefundSn(String refundSn);


}
