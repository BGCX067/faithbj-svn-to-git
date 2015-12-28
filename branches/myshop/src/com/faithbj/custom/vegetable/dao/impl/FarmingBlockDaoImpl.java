package com.faithbj.custom.vegetable.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FarmingBlockDao;
import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.shop.dao.impl.BaseDaoImpl;

/**
 * 
 * <p>耕作块Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Repository("farmingBlockDao")
public class FarmingBlockDaoImpl extends BaseDaoImpl<FarmingBlock, String> implements FarmingBlockDao
{

}
