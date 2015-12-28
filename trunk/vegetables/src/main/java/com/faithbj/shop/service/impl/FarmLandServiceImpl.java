package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.FarmLandDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Farmland;
import com.faithbj.shop.service.FarmLandService;

@Service("farmLandService")
public class FarmLandServiceImpl extends BaseServiceImpl<Farmland, String> implements
		FarmLandService {
	
	@Resource
	private FarmLandDao farmlandDao;
	
	@Resource
	public void setBaseDao(FarmLandDao farmlandDao){
		super.setBaseDao(farmlandDao);
	}

	@Override
	public Pager findByPager(String productid, Pager pager) {
		return farmlandDao.findByPager(productid,pager);
	}

}
