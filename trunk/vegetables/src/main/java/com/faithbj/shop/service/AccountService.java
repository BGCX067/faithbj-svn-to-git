package com.faithbj.shop.service;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Members;


public interface AccountService extends BaseService<Accounts, String> {

	Pager findByPager(Pager pager, Members member);
	

}
