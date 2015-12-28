package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.dao.FarmingBlockDao;
import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.entity.FarmingLog;
import com.faithbj.custom.vegetable.service.FarmingBlockService;
import com.faithbj.custom.vegetable.service.FarmingLogService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>耕作块Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Service("farmingBlockService")
public class FarmingBlockServiceImpl extends BaseServiceImpl<FarmingBlock, String> implements FarmingBlockService
{
	@Resource
	protected FarmingBlockDao farmingBlockDao = null;

	@Resource
	public void setBaseDao(FarmingBlockDao farmingBlockDao) {
		super.setBaseDao(farmingBlockDao);
	}
	

	@Resource
	private FarmingLogService farmingLogService = null;

	private void log(FarmingBlock entity)
	{
		FarmingLog farmingLog = new FarmingLog();
		BeanUtils.copyProperties(entity, farmingLog, new String[] {"id", "createDate", "modifyDate"});
		this.farmingLogService.save(farmingLog);
	}
	
	@Override
	public String save(FarmingBlock entity)
	{
		this.log(entity);
		
		return super.save(entity);
	}

	@Override
	public void update(FarmingBlock entity)
	{
		this.log(entity);
		
		super.update(entity);
	}
}
