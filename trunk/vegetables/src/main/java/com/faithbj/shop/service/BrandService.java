package com.faithbj.shop.service;

import com.faithbj.shop.model.entity.Brand;

/**
 * Service接口 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-03
 * @version 1.0
 */

public interface BrandService extends BaseService<Brand, String> {
	public void ppsaveOrUpdate(Brand entity) ;
}
