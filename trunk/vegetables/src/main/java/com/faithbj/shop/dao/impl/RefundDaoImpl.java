package com.faithbj.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.RefundDao;
import com.faithbj.shop.model.entity.Refund;

/**
 * Dao实现类 - 退款
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class RefundDaoImpl extends BaseDaoImpl<Refund, String> implements RefundDao {
	
	@SuppressWarnings("unchecked")
	public String getLastRefundSn() {
		String hql = "from Refund as refund order by refund.createDate desc";
		List<Refund> refundList =  getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		if (refundList != null && refundList.size() > 0) {
			return refundList.get(0).getRefundSn();
		} else {
			return null;
		}
	}
	
	public Refund getRefundByRefundSn(String refundSn) {
		String hql = "from Refund as refund where refund.refundSn = ?";
		return (Refund) getSession().createQuery(hql).setParameter(0, refundSn).uniqueResult();
	}

}
