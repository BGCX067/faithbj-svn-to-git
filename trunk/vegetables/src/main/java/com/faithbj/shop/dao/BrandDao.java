package com.faithbj.shop.dao;

import com.faithbj.shop.model.entity.Brand;

/**
 * Dao接口 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface BrandDao extends BaseDao<Brand, String> {
	public void ppsaveOrUpdate(Brand entity);

}
