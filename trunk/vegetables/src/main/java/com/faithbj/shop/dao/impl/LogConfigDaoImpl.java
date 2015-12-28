package com.faithbj.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.LogConfigDao;
import com.faithbj.shop.model.entity.LogConfig;

/**
 * Dao实现类 - 日志设置
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class LogConfigDaoImpl extends BaseDaoImpl<LogConfig, String> implements LogConfigDao {

	@SuppressWarnings("unchecked")
	public List<LogConfig> getLogConfigList(String actionClassName) {
		String hql = "from LogConfig as logConfig where logConfig.actionClassName = ?";
		return getSession().createQuery(hql).setParameter(0, actionClassName).list();
	}

}
