package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.AccountDao;
import com.faithbj.shop.dao.AreaDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.AccountService;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Accounts, String> implements AccountService{

	@Resource
	private AccountDao accountDao;
	
	@Resource
	public void setBaseDao(AccountDao accountDao) {
		super.setBaseDao(accountDao);
	}
	
	@Override
	public Pager findByPager(Pager pager, Members memberid) {
		return accountDao.findByPager(pager,memberid);
	}
	

}
