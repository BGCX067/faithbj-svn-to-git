package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.dao.DepositDao;
import com.faithbj.shop.entity.Deposit;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.DepositService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 预存款记录
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class DepositServiceImpl extends BaseServiceImpl<Deposit, String> implements DepositService {

	@Resource
	private DepositDao depositDao;
	
	@Resource
	public void setBaseDao(DepositDao depositDao) {
		super.setBaseDao(depositDao);
	}
	
	public Pager getDepositPager(Member member, Pager pager) {
		return depositDao.getDepositPager(member, pager);
	}

}
