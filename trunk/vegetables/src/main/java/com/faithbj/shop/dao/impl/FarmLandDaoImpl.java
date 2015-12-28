package com.faithbj.shop.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.FarmLandDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Farmland;

@Repository("farmLandDao")
public class FarmLandDaoImpl extends BaseDaoImpl<Farmland, String> implements
		FarmLandDao {


	@Override
	public Pager findByPager(String productid, Pager pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Farmland.class);
		detachedCriteria.createAlias("product", "product");
		detachedCriteria.add(Restrictions.eq("product.id", productid));
		return findByPager(pager, detachedCriteria);
	}

}
