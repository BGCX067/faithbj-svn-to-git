package com.faithbj.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.ShippingDao;
import com.faithbj.shop.model.entity.Shipping;

/**
 * Dao实现类 - 发货
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class ShippingDaoImpl extends BaseDaoImpl<Shipping, String> implements ShippingDao {
	
	@SuppressWarnings("unchecked")
	public String getLastShippingSn() {
		String hql = "from Shipping as shipping order by shipping.createDate desc";
		List<Shipping> shippingList =  getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		if (shippingList != null && shippingList.size() > 0) {
			return shippingList.get(0).getShippingSn();
		} else {
			return null;
		}
	}

}
