package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.dao.SeedDao;
import com.faithbj.custom.vegetable.service.SeedService;
import com.faithbj.shop.entity.Seed;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * Service实现类 - 种子
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-28
 * @version 1.0
 */

@Service
public class SeedServiceImpl extends BaseServiceImpl<Seed, String> implements
		SeedService {

//	@Resource
//	private SeedDao seedDao;

	@Resource
	public void setBaseDao(SeedDao seedDao) {
		super.setBaseDao(seedDao);
	}

}
