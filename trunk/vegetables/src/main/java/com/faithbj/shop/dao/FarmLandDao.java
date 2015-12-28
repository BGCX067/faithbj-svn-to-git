package com.faithbj.shop.dao;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Farmland;

@Repository("farmlandDao")
public interface FarmLandDao extends BaseDao<Farmland, String> {

	Pager findByPager(String productid, Pager pager);
	
}
