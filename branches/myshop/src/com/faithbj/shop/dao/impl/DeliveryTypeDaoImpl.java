package com.faithbj.shop.dao.impl;

import java.util.List;
import java.util.Set;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.bean.Pager.OrderType;
import com.faithbj.shop.dao.DeliveryTypeDao;
import com.faithbj.shop.entity.DeliveryType;
import com.faithbj.shop.entity.Order;
import com.faithbj.shop.entity.Reship;
import com.faithbj.shop.entity.Shipping;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 配送方式
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class DeliveryTypeDaoImpl extends BaseDaoImpl<DeliveryType, String> implements DeliveryTypeDao {

	public DeliveryType getDefaultDeliveryType() {
		String hql = "from DeliveryType as deliveryType where deliveryType.isDefault = ?";
		return (DeliveryType) getSession().createQuery(hql).setParameter(0, true).setMaxResults(1).uniqueResult();
	}
	
	// 关联处理
	@Override
	public void delete(DeliveryType deliveryType) {
		Set<Order> orderSet = deliveryType.getOrderSet();
		if (orderSet != null) {
			for (Order order : orderSet) {
				order.setDeliveryType(null);
			}
		}
		Set<Shipping> shippingSet = deliveryType.getShippingSet();
		if (shippingSet != null) {
			for (Shipping shipping : shippingSet) {
				shipping.setDeliveryType(null);
			}
		}
		Set<Reship> reshipSet = deliveryType.getReshipSet();
		if (reshipSet != null) {
			for (Reship reship : reshipSet) {
				reship.setDeliveryType(null);
			}
		}
		super.delete(deliveryType);
	}

	// 关联处理
	@Override
	public void delete(String id) {
		DeliveryType deliveryType = super.load(id);
		this.delete(deliveryType);
	}

	// 关联处理
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			DeliveryType deliveryType = super.load(id);
			this.delete(deliveryType);
		}
	}

	// 根据orderList排序
	@SuppressWarnings("unchecked")
	@Override
	public List<DeliveryType> getAll() {
		String hql = "from DeliveryType deliveryType order by deliveryType.orderList asc deliveryType.createDate desc";
		return getSession().createQuery(hql).list();
	}

	// 根据orderList排序
	@Override
	@SuppressWarnings("unchecked")
	public List<DeliveryType> getList(String propertyName, Object value) {
		String hql = "from DeliveryType deliveryType where deliveryType." + propertyName + "=? order by deliveryType.orderList asc deliveryType.createDate desc";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	// 根据orderList排序
	@Override
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new Pager();
			pager.setOrderBy("orderList");
			pager.setOrderType(OrderType.asc);
		}
		return super.findByPager(pager, detachedCriteria);
	}

	// 根据orderList排序
	@Override
	public Pager findByPager(Pager pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DeliveryType.class);
		return this.findByPager(pager, detachedCriteria);
	}

}
