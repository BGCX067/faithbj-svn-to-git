package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faithbj.shop.dao.BrandDao;
import com.faithbj.shop.model.entity.Brand;
import com.faithbj.shop.service.BrandService;

/**
 * Service实现类 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service("brandService")
@Transactional(readOnly=false)
public class BrandServiceImpl extends BaseServiceImpl<Brand, String> implements BrandService {

	@Resource
	private BrandDao brandDao;
	@Resource
	public void setBaseDao(BrandDao brandDao) {
		super.setBaseDao(brandDao);
	}
	
	public void ppsaveOrUpdate(Brand entity) {
		brandDao.ppsaveOrUpdate(entity);
	}

}
