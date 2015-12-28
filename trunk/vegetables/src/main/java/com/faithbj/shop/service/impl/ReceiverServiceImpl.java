package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.ReceiverDao;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Receiver;
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

	@Override
	public Receiver getReceiver(Members member) {
		Receiver receiver = new Receiver();
		receiver.setAddress("Address");
		receiver.setId("231");
		receiver.setIsDefault(true);
		receiver.setName("name");
		receiver.setPhone("12345");
		receiver.setMobile("12345");
		return receiver;
	}
}
