package com.faithbj.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.dao.OrderDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Order;
import com.faithbj.shop.service.OrderService;
import com.faithbj.shop.utils.SerialNumberUtil;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 订单
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, String> implements OrderService {
	
	@Resource
	private OrderDao orderDao;

	@Resource
	public void setBaseDao(OrderDao orderDao) {
		super.setBaseDao(orderDao);
	}
	
	public String getLastOrderSn() {
		return orderDao.getLastOrderSn();
	}
	
	public Pager getOrderPager(Members member, Pager pager) {
		return orderDao.getOrderPager(member, pager);
	}
	
	public Long getUnprocessedOrderCount() {
		return orderDao.getUnprocessedOrderCount();
	}
	
	public Long getPaidUnshippedOrderCount() {
		return orderDao.getPaidUnshippedOrderCount();
	}

	// 重写对象，保存时自动设置订单编号
	@Override
	public String save(Order order) {
		order.setOrderSn(SerialNumberUtil.buildOrderSn());
		return super.save(order);
	}

	@Override
	public List<Order> getLandOrderPager(Members member, Pager pager) {
		return orderDao.getLandOrderPager(member, pager);
	}

}
