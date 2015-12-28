package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.DeliveryItemDao;
import com.faithbj.shop.entity.DeliveryItem;
import com.faithbj.shop.service.DeliveryItemService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 发货项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class DeliveryItemServiceImpl extends BaseServiceImpl<DeliveryItem, String> implements DeliveryItemService {

	@Resource
	public void setBaseDao(DeliveryItemDao deliveryItemDao) {
		super.setBaseDao(deliveryItemDao);
	}

}
