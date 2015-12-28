package com.faithbj.custom.dao.dialect;

/**
 * 
 * <p>dialect接口，提供生成分页和总和的sql</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public interface Dialect
{
	/**
	 * 是否支持物理分页
	 * @return
	 */
	boolean supportsLimit();

	/**
	 * 分页查询
	 * @param sql
	 * @param hasOffset
	 * @return
	 */
	String getLimitString(String sql, boolean hasOffset);

	/**
	 * 分页查询
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	String getLimitString(String sql, int offset, int limit);

	/**
	 * 生成查询总和的sql
	 * @param sql
	 * @return
	 */
	String getCountString(String sql);
}
