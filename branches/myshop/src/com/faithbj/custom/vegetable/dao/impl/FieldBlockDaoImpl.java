package com.faithbj.custom.vegetable.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FieldBlockDao;
import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.dao.impl.BaseDaoImpl;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Product;

/**
 * 
 * <p>土地块Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Repository("fieldBlockDao")
public class FieldBlockDaoImpl extends BaseDaoImpl<FieldBlock, String> implements FieldBlockDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<FieldBlock> getFieldBlockList() {
		String hql = "from FieldBlock";
		return getSession().createQuery(hql).list();
	}

	public Pager findFavoriteFieldBlockByPager(Member member, Pager pager)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FieldBlock.class);
		detachedCriteria.createAlias("favoriteMemberSet", "favoriteMemberSet");
		detachedCriteria.add(Restrictions.eq("favoriteMemberSet.id", member.getId()));
		detachedCriteria.addOrder(Order.desc("name"));
		return findByPager(pager, detachedCriteria);
	}
}
