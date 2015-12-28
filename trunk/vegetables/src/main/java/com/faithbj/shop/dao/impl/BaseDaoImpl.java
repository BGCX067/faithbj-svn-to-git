package com.faithbj.shop.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

import com.faithbj.shop.dao.BaseDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.BaseEntity;

/**
 * Dao实现类 - Dao实现类基类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

//@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> entityClass;
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}
	
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}
	
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		
		 if ((entity instanceof BaseEntity))
		    try
		    {
		    	Method[] methods=entity.getClass().getMethods();
		    	for(Method mt:methods){
		    		if(StringUtils.equals("onSave", mt.getName())){
		    	        Method localMethod = entity.getClass().getMethod("onSave", new Class[0]);
				        if(null!=localMethod)
				        	localMethod.invoke(entity, new Object[0]);
				        break;
		    		}
		    	}
		    	return (PK) getSession().save(entity);
		    }
		    catch (Exception localException)
		    {
		        localException.printStackTrace();
		        return null;
		    }		
		return (PK) getSession().save(entity);
	}
	
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity is required");
	    getSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		
		if ((entity instanceof BaseEntity))
		      try
		      {
			    	Method[] methods=entity.getClass().getMethods();
			    	for(Method mt:methods){
			    		if(StringUtils.equals("onSave", mt.getName())){
			    	        Method localMethod = entity.getClass().getMethod("onUpdate", new Class[0]);
					        if(null!=localMethod)
					        	localMethod.invoke(entity, new Object[0]);
					        break;
			    		}
			    	}
			    	getSession().update(entity);
		      }
		      catch (Exception localException)
		      {
		        localException.printStackTrace();
		      }
		else
		   getSession().update(entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}
	
	public Pager findByPager(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		return findByPager(pager, detachedCriteria);
	}

	public Pager findPager(Pager pager, Criterion[] paramArrayOfCriterion)
	{
  	    DetachedCriteria localCriteria = DetachedCriteria.forClass(entityClass);
		for (Criterion localCriterion : paramArrayOfCriterion)
	    	localCriteria.add(localCriterion);
	    
	    return findByPager(pager, localCriteria);
	}

//	  public Pager findPager(Pager pager, Order[] paramArrayOfOrder)
//	  {
//	    Criteria localCriteria = getSession().createCriteria(entityClass);
//	    for (Order localOrder : paramArrayOfOrder)
//	      localCriteria.addOrder(localOrder);
//	    
//	    return findByPager(pager, localCriteria);
//	  }
	
	  public Pager findPager(Pager pager, final String hql,Integer count, final Object... paramValues)
	  {
	    Assert.notNull(pager, "pager is required");
	    Assert.notNull(hql, "criteria is required");
		if (pager == null) {
			pager = new Pager();
		}
	    Integer pageNmber = Integer.valueOf(pager.getPageNumber());
	    Integer pageSize = Integer.valueOf(pager.getPageSize());
	  
		Query query = getSession().createQuery(hql);
		if (paramValues != null) {
			for (int i = 0; i < paramValues.length; i++) {
				query.setParameter(i, paramValues[i]);
			}
		}
		query.setFirstResult((pageNmber.intValue() - 1) * pageSize.intValue());
		query.setMaxResults(pageSize.intValue());
		
		pager.setTotalCount((count==null)?pager.getTotalCount():count);//count为null，就从pager对象找总数
		pager.setResult(query.list());
	    return pager;
	  }	  
	  
	  
	  
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new Pager();
		}
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String property = pager.getProperty();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		OrderType orderType = pager.getOrderType();
		
		List<String> propertyList = pager.getPropertyList();
		List<Object> keywordList = pager.getKeywordList();
		List<String> compareTypeList = pager.getCompareTypeList();
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
			String propertyString = "";
			if (property.contains(".")) {
				String propertyPrefix = StringUtils.substringBefore(property, ".");
				String propertySuffix = StringUtils.substringAfter(property, ".");
				criteria.createAlias(propertyPrefix, "model");
				propertyString = "model." + propertySuffix;
			} else {
				propertyString = property;
			}
			criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
		}
		if (propertyList != null && !propertyList.isEmpty() 
				&& keywordList != null && !keywordList.isEmpty() 
				&& propertyList.size() == keywordList.size())
		{
			for (int i = 0; i < propertyList.size(); i++)
			{
				String propertyString = "";
				if (propertyList.get(i).contains(".")) {
					String propertyPrefix = StringUtils.substringBefore(propertyList.get(i), ".");
					String propertySuffix = StringUtils.substringAfter(propertyList.get(i), ".");
					criteria.createAlias(propertyPrefix, "model");
					propertyString = "model." + propertySuffix;
				} else {
					propertyString = propertyList.get(i);
				}
				if ("eq".equals(compareTypeList.get(i)))
				{
					criteria.add(Restrictions.eq(propertyString, keywordList.get(i)));
				}
				else
				{
					criteria.add(Restrictions.like(propertyString, "%" + keywordList.get(i) + "%"));
				}
			}
		}
		
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		pager.setTotalCount(totalCount.intValue());
		pager.setResult(criteria.list());
		return pager;
	}
	
	
	public int executeUpdate(final String hql, final Map<String, Object> valueMap) {
		return getSession().createQuery(hql).setProperties(valueMap).executeUpdate();
	}
	
	@Override
	public Long count(final String hql, final Object... values) {
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		Long result=(Long) query.uniqueResult();
		
		return result;
	}

}
