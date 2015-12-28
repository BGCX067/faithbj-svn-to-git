package com.faithbj.shop.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.OrderDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Order;
import com.faithbj.shop.model.entity.Order.OrderStatus;
import com.faithbj.shop.model.entity.Order.PaymentStatus;
import com.faithbj.shop.model.entity.Order.ShippingStatus;

/**
 * Dao实现类 - 订单
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, String> implements OrderDao {
	
	@SuppressWarnings("unchecked")
	public String getLastOrderSn() {
		String hql = "from Order as order order by order.createDate desc";
		List<Order> orderList =  getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		if (orderList != null && orderList.size() > 0) {
			return orderList.get(0).getOrderSn();
		} else {
			return null;
		}
	}
	
	public Pager getOrderPager(Members member, Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}
		if (pager.getPageSize() == null) {
			pager.setPageSize(Order.DEFAULT_ORDER_LIST_PAGE_SIZE);
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
		detachedCriteria.add(Restrictions.eq("member", member));
		return super.findByPager(pager, detachedCriteria);
	}
	
	public Long getUnprocessedOrderCount() {
		String hql = "select count(*) from Order as order where order.orderStatus = ?";
		return (Long) getSession().createQuery(hql).setParameter(0, OrderStatus.unprocessed).uniqueResult();
	}
	
	public Long getPaidUnshippedOrderCount() {
		String hql = "select count(*) from Order as order where order.paymentStatus = ? and order.shippingStatus = ? and order.orderStatus != ? and order.orderStatus != ?";
		return (Long) getSession().createQuery(hql).setParameter(0, PaymentStatus.paid).setParameter(1, ShippingStatus.unshipped).setParameter(2, OrderStatus.completed).setParameter(3, OrderStatus.invalid).uniqueResult();
	}

	@Override
	public List<Order> getLandOrderPager(Members member, Pager pager) {
		
		String hql = "from Order as order where order.member="+member+" and order.orderItemSet.product.isLand=1";
		List<Order> orderList =  getSession().createQuery(hql).list();
		return orderList;
	}
	
}
