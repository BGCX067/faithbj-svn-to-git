package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.ProductTypeDao;
import com.faithbj.shop.model.entity.ProductType;
import com.faithbj.shop.service.ProductTypeService;

/**
 * Service实现类 - 商品类型
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, String> implements
		ProductTypeService {

	@Resource
	public void setBaseDao(ProductTypeDao productTypeDao) {
		super.setBaseDao(productTypeDao);
	}

}
