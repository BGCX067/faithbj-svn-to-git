package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.entity.MemberAuth;
import com.faithbj.shop.dao.MemberAuthDao;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.MemberAuthService;

/**
 * 
 * <p>蔬菜会员资格Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-02
 * @version 1.0
 */
@Service
public class MemberAuthServiceImpl extends BaseServiceImpl<MemberAuth, String> implements MemberAuthService
{
	@Resource
	protected MemberAuthDao memberAuthDao = null;

	@Resource
	public void setBaseDao(MemberAuthDao memberAuthDao) {
		super.setBaseDao(memberAuthDao);
	}
}
