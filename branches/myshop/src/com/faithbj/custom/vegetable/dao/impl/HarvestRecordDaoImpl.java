package com.faithbj.custom.vegetable.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.HarvestRecordDao;
import com.faithbj.custom.vegetable.entity.HarvestRecord;
import com.faithbj.shop.dao.impl.BaseDaoImpl;

/**
 * 
 * <p>收货记录Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-02
 * @version 1.0
 */

@Repository("harvestRecordDao")
public class HarvestRecordDaoImpl extends BaseDaoImpl<HarvestRecord, String> implements HarvestRecordDao
{

}
