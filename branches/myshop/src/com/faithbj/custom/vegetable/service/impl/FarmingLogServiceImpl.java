package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.vegetable.dao.FarmingLogDao;
import com.faithbj.custom.vegetable.entity.FarmingLog;
import com.faithbj.custom.vegetable.service.FarmingLogService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>耕作日志Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Service("farmingLogService")
public class FarmingLogServiceImpl extends BaseServiceImpl<FarmingLog, String> implements FarmingLogService
{
	@Resource
	protected FarmingLogDao farmingLogDao = null;

	@Resource
	public void setBaseDao(FarmingLogDao farmingLogDao) {
		super.setBaseDao(farmingLogDao);
	}
}
