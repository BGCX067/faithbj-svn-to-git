package com.faithbj.custom.dao.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.faithbj.custom.dao.pagination.PaginationInfo;
import com.faithbj.custom.dao.pagination.PaginationList;

/**
 * 
 * <p>Title: 通用增删查改DAO</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public interface CommonCRUDDao<Entity>
{
	/**
	 * insert
	 * @param t
	 * @return 返回实体包含生成主键
	 */
	public Entity insertEntity(Entity entity) throws SQLException;

	/**
	 * delete
	 * @param entity
	 * @return 是否删除成功
	 */
	public boolean deleteEntity(Entity entity) throws SQLException;

	/**
	 * update
	 * @param entity
	 * @return 是否更新成功
	 */
	public boolean updateEntity(Entity entity) throws SQLException;
	
	/**
	 * delete list
	 * @param entityList
	 * @return 返回删除成功实体列表
	 */
	public List<Entity> deleteEntityList(List<Entity> entityList) throws SQLException;

	/**
	 * update list
	 * @param entityList
	 * @return 返回更新成功实体列表
	 */
	public List<Entity> updateEntityList(List<Entity> entityList) throws SQLException;
	
	/**
	 * select by condition
	 * @param cond
	 * @param paginationInfo
	 * @return 分页实体列表
	 */
	public PaginationList<Entity> selectEntityListByCond(Map<String, Object> cond, PaginationInfo paginationInfo) throws SQLException;
	
	/**
	 * select by id
	 * @param clientVersion
	 * @return 实体
	 */
	public Entity selectEntityById(Entity entity) throws SQLException;
}
