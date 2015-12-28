package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.DeliveryCorpDao;
import com.faithbj.shop.entity.DeliveryCorp;
import com.faithbj.shop.service.DeliveryCorpService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 物流公司
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, String> implements DeliveryCorpService {

	@Resource
	public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
		super.setBaseDao(deliveryCorpDao);
	}

}
