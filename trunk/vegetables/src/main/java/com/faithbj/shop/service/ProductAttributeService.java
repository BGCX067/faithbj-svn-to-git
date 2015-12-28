package com.faithbj.shop.service;

import java.util.List;

import com.faithbj.shop.model.entity.ProductAttribute;
import com.faithbj.shop.model.entity.ProductType;

/**
 * Service接口 - 商品属性
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface ProductAttributeService extends BaseService<ProductAttribute, String> {
	
	/**
	 * 获取已启用的商品属性.
	 * 
	 * @return 已启用的商品属性集合.
	 */
	public List<ProductAttribute> getEnabledProductAttributeList();
	
	/**
	 * 根据商品类型获取已启用的商品属性.
	 * 
	 * @return 已启用的商品属性集合.
	 */
	public List<ProductAttribute> getEnabledProductAttributeList(ProductType productType);
	
	/**
	 * 根据商品类型、商品名称查找，若不存在则返回null
	 * 
	 * @param productType
	 *            商品类型
	 * 
	 * @param name
	 *            商品属性名称 
	 * 
	 */
	public ProductAttribute getProductAttribute(ProductType productType, String name);

}
