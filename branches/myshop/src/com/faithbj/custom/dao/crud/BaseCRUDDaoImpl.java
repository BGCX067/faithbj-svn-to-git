package com.faithbj.custom.dao.crud;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.faithbj.custom.dao.ibatis.BaseDao;
import com.faithbj.custom.dao.pagination.PaginationInfo;
import com.faithbj.custom.dao.pagination.PaginationList;

/**
 * 
 * <p>Title: 通用增删查改DAO实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public abstract class BaseCRUDDaoImpl<Entity> extends BaseDao implements CommonCRUDDao<Entity>
{
	private Class<Entity> entityClass = null;
	
	@SuppressWarnings("unchecked")
	public BaseCRUDDaoImpl() 
	{
		this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) 
        {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<Entity>) parameterizedType[0];
        }
	}
	
	@Override
	public boolean deleteEntity(Entity entity) throws SQLException
	{
		int result = this.getSqlMapClient().delete(this.getEntityClass().getSimpleName() + ".deleteEntity", entity);
		return result == 1;
	}

	@Override
	public List<Entity> deleteEntityList(List<Entity> entityList) throws SQLException
	{
		List<Entity> result = new ArrayList<Entity>();
		for (Entity entity : entityList)
		{
			int operationResult = this.getSqlMapClient().delete(this.getEntityClass().getSimpleName() + ".deleteEntity", entity);
			if (operationResult == 1)
			{
				result.add(entity);
			}
		}
		return result;
	}

	@Override
	public Entity insertEntity(Entity entity) throws SQLException
	{
		this.getSqlMapClient().insert(this.getEntityClass().getSimpleName() + ".insertEntity", entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entity selectEntityById(Entity entity) throws SQLException
	{
		return  (Entity)this.getSqlMapClient().queryForObject(this.getEntityClass().getSimpleName() + ".selectEntityById", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationList<Entity> selectEntityListByCond(Map<String, Object> cond, PaginationInfo paginationInfo)
	{
		return this.getPaginationSqlMapClientTemplate().query4PaginationList(this.getEntityClass().getSimpleName() + ".selectEntityListByCond", cond, paginationInfo);
	}

	@Override
	public boolean updateEntity(Entity entity) throws SQLException
	{
		int result = this.getSqlMapClient().update(this.getEntityClass().getSimpleName() + ".updateEntity", entity);
		return result == 1;
	}

	@Override
	public List<Entity> updateEntityList(List<Entity> entityList) throws SQLException
	{
		List<Entity> result = new ArrayList<Entity>();
		for (Entity entity : entityList)
		{
			int operationResult = this.getSqlMapClient().update(this.getEntityClass().getSimpleName() + ".updateEntity", entity);
			if (operationResult == 1)
			{
				result.add(entity);
			}
		}
		return result;
	}

	public Class<Entity> getEntityClass()
	{
		return entityClass;
	}
}
