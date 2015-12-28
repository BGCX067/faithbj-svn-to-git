package com.faithbj.shop.dao.impl;

import java.util.Set;

import com.faithbj.shop.dao.DeliveryCorpDao;
import com.faithbj.shop.entity.DeliveryCorp;
import com.faithbj.shop.entity.DeliveryType;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 物流公司
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class DeliveryCorpDaoImpl extends BaseDaoImpl<DeliveryCorp, String> implements DeliveryCorpDao {

	// 关联处理
	@Override
	public void delete(DeliveryCorp deliveryCorp) {
		Set<DeliveryType> deliveryTypeSet = deliveryCorp.getDeliveryTypeSet();
		if (deliveryTypeSet != null) {
			for (DeliveryType deliveryType : deliveryTypeSet) {
				deliveryType.setDefaultDeliveryCorp(null);
			}
		}
		super.delete(deliveryCorp);
	}

	// 关联处理
	@Override
	public void delete(String id) {
		DeliveryCorp deliveryCorp = load(id);
		this.delete(deliveryCorp);
	}

	// 关联处理
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			DeliveryCorp deliveryCorp = load(id);
			this.delete(deliveryCorp);
		}
	}

}
