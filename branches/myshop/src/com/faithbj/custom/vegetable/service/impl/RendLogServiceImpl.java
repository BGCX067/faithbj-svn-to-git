package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.dao.RendLogDao;
import com.faithbj.custom.vegetable.entity.RendLog;
import com.faithbj.custom.vegetable.service.RendLogService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>租赁日志Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Service("rendLogService")
public class RendLogServiceImpl extends BaseServiceImpl<RendLog, String> implements RendLogService
{
	@Resource
	protected RendLogDao rendLogDao = null;

	@Resource
	public void setBaseDao(RendLogDao rendLogDao) {
		super.setBaseDao(rendLogDao);
	}
}
