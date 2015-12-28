package com.faithbj.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.dao.LogConfigDao;
import com.faithbj.shop.entity.LogConfig;
import com.faithbj.shop.service.LogConfigService;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

/**
 * Service实现类 - 日志设置
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class LogConfigServiceImpl extends BaseServiceImpl<LogConfig, String> implements LogConfigService {
	
	@Resource
	private LogConfigDao logConfigDao;

	@Resource
	public void setBaseDao(LogConfigDao logConfigDao) {
		super.setBaseDao(logConfigDao);
	}
	
	public List<LogConfig> getLogConfigList(String actionClassName) {
		return logConfigDao.getLogConfigList(actionClassName);
	}
	
	@Override
	@Cacheable(modelId = "caching")
	public List<LogConfig> getAll() {
		List<LogConfig> allLogConfig = logConfigDao.getAll();
		if (allLogConfig != null) {
			for (LogConfig logConfig : allLogConfig) {
				Hibernate.initialize(logConfig);
			}
		}
		return allLogConfig;
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(LogConfig entity) {
		logConfigDao.delete(entity);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String id) {
		logConfigDao.delete(id);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String[] ids) {
		logConfigDao.delete(ids);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public String save(LogConfig entity) {
		return logConfigDao.save(entity);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void update(LogConfig entity) {
		logConfigDao.update(entity);
	}

}
