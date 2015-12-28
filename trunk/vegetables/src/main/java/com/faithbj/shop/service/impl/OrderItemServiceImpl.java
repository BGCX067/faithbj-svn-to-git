package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.OrderItemDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.OrderItem;
import com.faithbj.shop.service.OrderItemService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 订单项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, String> implements OrderItemService {

	
	@Resource
	private OrderItemDao orderItemDao;
	public void setBaseDao(OrderItemDao orderItemDao) {
		super.setBaseDao(orderItemDao);
	}


}
