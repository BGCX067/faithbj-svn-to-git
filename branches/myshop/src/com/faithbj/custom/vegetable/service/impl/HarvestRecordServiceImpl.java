package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.HarvestRecordDao;
import com.faithbj.custom.vegetable.entity.HarvestRecord;
import com.faithbj.custom.vegetable.service.HarvestRecordService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>收货记录Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-02
 * @version 1.0
 */

@Repository("harvestRecordService")
public class HarvestRecordServiceImpl extends BaseServiceImpl<HarvestRecord, String> implements HarvestRecordService
{
	@Resource
	protected HarvestRecordDao harvestRecordDao = null;

	@Resource
	public void setBaseDao(HarvestRecordDao harvestRecordDao) {
		super.setBaseDao(harvestRecordDao);
	}
}
