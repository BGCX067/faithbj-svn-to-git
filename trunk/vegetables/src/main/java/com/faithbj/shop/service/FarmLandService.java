package com.faithbj.shop.service;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Farmland;

public interface FarmLandService extends BaseService<Farmland, String>{

	/**
	 * 
	 * @param productid
	 * @param pager
	 * @return 土地块下的耕种块
	 */
	public Pager findByPager(String productid, Pager pager);
}
