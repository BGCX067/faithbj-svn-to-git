package com.faithbj.custom.vegetable.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.dao.RendBlockDao;
import com.faithbj.custom.vegetable.entity.RendBlock;
import com.faithbj.custom.vegetable.entity.RendLog;
import com.faithbj.custom.vegetable.service.RendBlockService;
import com.faithbj.custom.vegetable.service.RendLogService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>租赁块Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Service("rendBlockService")
public class RendBlockServiceImpl extends BaseServiceImpl<RendBlock, String> implements RendBlockService
{
	@Resource
	protected RendBlockDao rendBlockDao = null;

	@Resource
	public void setBaseDao(RendBlockDao rendBlockDao) {
		super.setBaseDao(rendBlockDao);
	}
	
	@Resource
	private RendLogService rendLogService = null;

	private void log(RendBlock entity)
	{
		RendLog rendLog = new RendLog();
		rendLog.setOperateTime(new Date());
		BeanUtils.copyProperties(entity, rendLog, new String[] {"id", "createDate", "modifyDate"});
		this.rendLogService.save(rendLog);
	}
	
	@Override
	public String save(RendBlock entity)
	{
		this.log(entity);
		
		return super.save(entity);
	}

	@Override
	public void update(RendBlock entity)
	{
		this.log(entity);
		
		super.update(entity);
	}
	
}
