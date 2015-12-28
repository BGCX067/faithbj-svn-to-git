package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.ReceiverDao;
import com.faithbj.shop.entity.Receiver;
import com.faithbj.shop.service.ReceiverService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 收货地址
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class ReceiverServiceImpl extends BaseServiceImpl<Receiver, String> implements ReceiverService {

	@Resource
	public void setBaseDao(ReceiverDao receiverDao) {
		super.setBaseDao(receiverDao);
	}

}
