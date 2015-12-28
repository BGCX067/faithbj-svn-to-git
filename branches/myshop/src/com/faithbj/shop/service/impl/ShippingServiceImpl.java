package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.ShippingDao;
import com.faithbj.shop.entity.Shipping;
import com.faithbj.shop.service.ShippingService;
import com.faithbj.shop.util.SerialNumberUtil;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 发货
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class ShippingServiceImpl extends BaseServiceImpl<Shipping, String> implements ShippingService {
	
	@Resource
	private ShippingDao shippingDao;

	@Resource
	public void setBaseDao(ShippingDao shippingDao) {
		super.setBaseDao(shippingDao);
	}
	
	public String getLastShippingSn() {
		return shippingDao.getLastShippingSn();
	}

	// 重写对象，保存时自动设置发货编号
	@Override
	public String save(Shipping shipping) {
		shipping.setShippingSn(SerialNumberUtil.buildShippingSn());
		return super.save(shipping);
	}

}
