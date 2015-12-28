package com.faithbj.shop.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.faithbj.shop.dao.BaseDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.service.BaseService;

/**
 * Service实现类 - Service实现类基类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Transactional
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	private BaseDao<T, PK> baseDao;

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Transactional(readOnly=true)
	public T get(PK id) {
		return baseDao.get(id);
	}
	
    @Transactional(readOnly=true)
	public T load(PK id) {
		return baseDao.load(id);
	}
	@Transactional(readOnly=true)	
	public List<T> get(PK[] ids) {
		return baseDao.get(ids);
	}
	@Transactional(readOnly=true)
	public T get(String propertyName, Object value) {
		return baseDao.get(propertyName, value);
	}
	@Transactional(readOnly=true)	
	public List<T> getList(String propertyName, Object value) {
		return baseDao.getList(propertyName, value);
	}
	@Transactional(readOnly=true)
	public List<T> getAll() {
		return baseDao.getAll();
	}
	
	@Transactional(readOnly=true)
	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		return baseDao.isUnique(propertyName, oldValue, newValue);
	}
	
	public boolean isExist(String propertyName, Object value) {
		return baseDao.isExist(propertyName, value);
	}
	@Transactional
	public PK save(T entity) {
		return baseDao.save(entity);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void delete(PK id) {
		baseDao.delete(id);
	}

	public void delete(PK[] ids) {
		baseDao.delete(ids);
	}

	public void flush() {
		baseDao.flush();
	}

	public void clear() {
		baseDao.clear();
	}

	public void evict(Object object) {
		baseDao.evict(object);
	}
	@Transactional(readOnly=true)
	public Pager findByPager(Pager pager) {
		return baseDao.findByPager(pager);
	}
	@Transactional(readOnly=true)
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
		return baseDao.findByPager(pager, detachedCriteria);
	}

	@Override
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
		
	}

}
