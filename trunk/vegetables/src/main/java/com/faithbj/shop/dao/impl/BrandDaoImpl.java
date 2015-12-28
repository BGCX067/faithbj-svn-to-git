package com.faithbj.shop.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.faithbj.shop.dao.BrandDao;
import com.faithbj.shop.model.entity.Brand;
import com.faithbj.shop.model.entity.Product;

/**
 * Dao实现类 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository("brandDao")
public class BrandDaoImpl extends BaseDaoImpl<Brand, String> implements BrandDao {
	
	// 关联处理
	@Override
	public void delete(Brand brand) {
		Set<Product> productSet = brand.getProductSet();
		if (productSet != null) {
			for (Product product : productSet) {
				product.setBrand(null);
			}
		}
		super.delete(brand);
	}

	// 关联处理
	@Override
	public void delete(String id) {
		Brand brand = load(id);
		this.delete(brand);
	}

	// 关联处理
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			Brand brand = load(id);
			this.delete(brand);
		}
	}
	
	
	@Override
	public void ppsaveOrUpdate(Brand entity) {
		Assert.notNull(entity, "entity is required");
	    getSession().saveOrUpdate(entity);
	}
	
}
