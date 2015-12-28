package com.faithbj.custom.dao.ibatis;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.faithbj.custom.dao.dialect.Dialect;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;


/**
 * 
 * <p>SqlExecutor分页实现</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public class LimitSqlExecutor extends SqlExecutor
{
	private static final Log logger = LogFactory.getLog(LimitSqlExecutor.class);

	private Dialect dialect;
	private boolean enableLimit = true;

	public Dialect getDialect()
	{
		return dialect;
	}

	public void setDialect(Dialect dialect)
	{
		this.dialect = dialect;
	}

	public boolean isEnableLimit()
	{
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit)
	{
		this.enableLimit = enableLimit;
	}

	@Override
	public void executeQuery(StatementScope scope, Connection conn, String sql, Object[] parameters, int skipResults,
			int maxResults, RowHandlerCallback callback) throws SQLException
	{
		if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS) && supportsLimit())
		{
			sql = dialect.getLimitString(sql, skipResults, maxResults);
			//			System.out.println("limitString:" + sql);
			if (logger.isDebugEnabled())
			{
				logger.debug(sql);
			}
			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}
		super.executeQuery(scope, conn, sql, parameters, skipResults, maxResults, callback);
	}

	public boolean supportsLimit()
	{
		if (enableLimit && dialect != null)
		{
			return dialect.supportsLimit();
		}
		return false;
	}
}
