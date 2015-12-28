package com.faithbj.shop.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.AccountDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Members;


@Repository
public class AccountDaoImpl extends BaseDaoImpl<Accounts, String> implements AccountDao {

	@Override
	public Pager findByPager(Pager pager, Members memberid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Accounts.class);
		detachedCriteria.add(Restrictions.eq("member", memberid));
		return findByPager(pager, detachedCriteria);
	}


}
