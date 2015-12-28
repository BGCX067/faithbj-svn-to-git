package com.faithbj.custom.dao.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.faithbj.custom.dao.pagination.PaginationInfo;
import com.faithbj.custom.dao.pagination.PaginationList;

/**
 * 
 * <p>Title: 通用增删查改Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public abstract class BaseCRUDServiceImpl<Entity> implements CommonCRUDService<Entity>
{

	@Override
	public boolean deleteEntity(Entity entity)
	{
		boolean result = true;
		try
		{
			result = this.getDao().deleteEntity(entity);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Entity> deleteEntityList(List<Entity> entityList)
	{
		List<Entity> result = null;
		try
		{
			result = this.getDao().deleteEntityList(entityList);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Entity insertEntity(Entity entity)
	{
		Entity result = null;
		try
		{
			result = this.getDao().insertEntity(entity);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Entity selectEntityById(Entity entity)
	{
		Entity result = null;
		try
		{
			result = this.getDao().selectEntityById(entity);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PaginationList<Entity> selectEntityListByCond(Map<String, Object> cond, PaginationInfo paginationInfo)
	{
		PaginationList<Entity> result = null;
		try
		{
			result = this.getDao().selectEntityListByCond(cond, paginationInfo);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateEntity(Entity entity)
	{
		boolean result = true;
		try
		{
			result = this.getDao().updateEntity(entity);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Entity> updateEntityList(List<Entity> entityList)
	{
		List<Entity> result = null;
		try
		{
			result = this.getDao().updateEntityList(entityList);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	protected abstract CommonCRUDDao<Entity> getDao();

}
