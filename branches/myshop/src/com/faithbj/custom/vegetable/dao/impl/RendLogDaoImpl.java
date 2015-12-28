package com.faithbj.custom.vegetable.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.RendLogDao;
import com.faithbj.custom.vegetable.entity.RendLog;
import com.faithbj.shop.dao.impl.BaseDaoImpl;

/**
 * 
 * <p>租赁日志Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Repository("rendLogDao")
public class RendLogDaoImpl extends BaseDaoImpl<RendLog, String> implements RendLogDao
{

}
