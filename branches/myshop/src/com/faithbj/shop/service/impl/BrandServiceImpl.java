package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.BrandDao;
import com.faithbj.shop.entity.Brand;
import com.faithbj.shop.service.BrandService;

import org.springframework.stereotype.Service;

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

@Service
public class BrandServiceImpl extends BaseServiceImpl<Brand, String> implements BrandService {

	@Resource
	public void setBaseDao(BrandDao brandDao) {
		super.setBaseDao(brandDao);
	}

}
