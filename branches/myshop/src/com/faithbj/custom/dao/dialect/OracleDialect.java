package com.faithbj.custom.dao.dialect;


/**
 * 
 * <p>oracle实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public class OracleDialect implements Dialect
{
	protected static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String sql, boolean hasOffset)
	{
		return null;
	}

	public String getLimitString(String sql, int offset, int limit)
	{
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			sb.append("select * from ( select row_.*, rownum rownum_ from ( ").append(sql).append(
					" ) row_ where rownum <= ").append(offset + limit).append(") where rownum_ > ").append(
					offset);
		} else {
			sb.append("select * from ( ").append(sql).append(" ) where rownum <= ").append(limit);
		}
		return sb.toString();
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public String getCountString(String sql)
	{
		String resultSql = "select count(1) " + sql.substring(sql.indexOf("from"), sql.length());
		if (resultSql.toLowerCase().lastIndexOf("order by") != -1)
		{
			resultSql = resultSql.substring(0, resultSql.toLowerCase().lastIndexOf("order by"));
		}
		return resultSql;
	}

	private String trim(String sql)
	{
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER))
		{
			sql = sql.substring(0, sql.length() - SQL_END_DELIMITER.length());
		}
		return sql;
	}

}
