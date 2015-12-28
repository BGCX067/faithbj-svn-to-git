package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.ReshipDao;
import com.faithbj.shop.entity.Reship;
import com.faithbj.shop.service.ReshipService;
import com.faithbj.shop.util.SerialNumberUtil;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 退货
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class ReshipServiceImpl extends BaseServiceImpl<Reship, String> implements ReshipService {
	
	@Resource
	private ReshipDao reshipDao;

	@Resource
	public void setBaseDao(ReshipDao reshipDao) {
		super.setBaseDao(reshipDao);
	}
	
	public String getLastReshipSn() {
		return reshipDao.getLastReshipSn();
	}

	// 重写对象，保存时自动设置退货编号
	@Override
	public String save(Reship reship) {
		reship.setReshipSn(SerialNumberUtil.buildReshipSn());
		return super.save(reship);
	}

}
