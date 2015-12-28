package com.faithbj.custom.dao.dialect;


/**
 * 
 * <p>mysql实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public class MySQLDialect implements Dialect
{
	protected static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String sql, boolean hasOffset)
	{
		return new StringBuffer(sql.length() + 20).append(trim(sql)).append(hasOffset ? " limit ?,?" : " limit ?")
				.append(SQL_END_DELIMITER).toString();
	}

	public String getLimitString(String sql, int offset, int limit)
	{
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		if (offset > 0)
		{
			sb.append(" limit ").append(offset).append(',').append(limit).append(SQL_END_DELIMITER);
		}
		else
		{
			sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
		}
		return sb.toString();
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public String getCountString(String sql)
	{
		return null;
	}

	private String trim(String sql)
	{
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER))
		{
			sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
		}
		return sql;
	}

}
