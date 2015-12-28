package com.faithbj.custom.dao.ibatis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.faithbj.custom.util.ReflectionUtils;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

/**
 * 
 * <p>SqlMapClientDaoSupport分页实现</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

@SuppressWarnings("deprecation")
public abstract class PaginationSupportSqlMapClientDaoSupport extends SqlMapClientDaoSupport
{
	@Autowired
	private SqlExecutor sqlExecutor;


	public void setSqlExecutor(SqlExecutor sqlExecutor)
	{
		this.sqlExecutor = sqlExecutor;
	}

	public void setEnableLimit(boolean enableLimit)
	{
		if (sqlExecutor instanceof LimitSqlExecutor)
		{
			((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
		}
	}

	@PostConstruct
	public void initialize() throws Exception
	{
		if (sqlExecutor != null)
		{
			SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
			if (sqlMapClient instanceof ExtendedSqlMapClient)
			{
				ReflectionUtils.setFieldValue(((ExtendedSqlMapClient) sqlMapClient).getDelegate(), "sqlExecutor",
						sqlExecutor);
			}
		}

	}
	

	PaginationSqlMapClientTemplate paginationSqlMapClientTemplate = null;
	public void setPaginationSqlMapClientTemplate(PaginationSqlMapClientTemplate paginationSqlMapClientTemplate)
	{
		this.paginationSqlMapClientTemplate = paginationSqlMapClientTemplate;
	}
	public PaginationSqlMapClientTemplate getPaginationSqlMapClientTemplate()
	{
		return paginationSqlMapClientTemplate;
	}
}
