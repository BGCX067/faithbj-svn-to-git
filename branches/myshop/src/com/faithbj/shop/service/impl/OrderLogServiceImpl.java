package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.OrderLogDao;
import com.faithbj.shop.entity.OrderLog;
import com.faithbj.shop.service.OrderLogService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 订单日志
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, String> implements OrderLogService {

	@Resource
	public void setBaseDao(OrderLogDao orderLogDao) {
		super.setBaseDao(orderLogDao);
	}

}
