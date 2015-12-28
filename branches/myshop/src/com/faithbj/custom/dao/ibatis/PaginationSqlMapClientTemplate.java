package com.faithbj.custom.dao.ibatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.faithbj.custom.dao.pagination.PaginationInfo;
import com.faithbj.custom.dao.pagination.PaginationList;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * 
 * <p>SqlMapClientTemplate分页实现</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */

public class PaginationSqlMapClientTemplate extends SqlMapClientTemplate
{
	@SuppressWarnings("unchecked")
	public PaginationList query4PaginationList(final String statementName, final Object parameterObject, PaginationInfo paginationInfo)
	{
		int currentPage = paginationInfo.getCurrentPage();
		int recordCountPerPage = paginationInfo.getRecordPerPage();
		
		PaginationList result = new PaginationList();
		
		SqlMapSession session = this.getSqlMapClient().openSession();
		
		Connection ibatisCon = null;
		try
		{
			Connection springCon = null;
			DataSource dataSource = getDataSource();
			boolean transactionAware = dataSource instanceof TransactionAwareDataSourceProxy;
			try
			{
				ibatisCon = session.getCurrentConnection();
				if (ibatisCon == null) 
				{
					springCon = (transactionAware ? dataSource.getConnection() : DataSourceUtils.doGetConnection(dataSource));
					session.setUserConnection(springCon);
				}
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
	
			String sqlStr = null;
			
			SqlMapClientImpl sqlMapClientImpl = (SqlMapClientImpl) this.getSqlMapClient();
			MappedStatement mappedStatement = sqlMapClientImpl.getMappedStatement(statementName);
			
			StatementScope requestScope = new StatementScope(new SessionScope());
			mappedStatement.initRequest(requestScope);
			Sql sql = mappedStatement.getSql();
			sqlStr = sql.getSql(requestScope, parameterObject);

			Object[] parameters = null;
	
			ParameterMap parameterMap = sql.getParameterMap(requestScope, parameterObject);
			requestScope.setParameterMap(parameterMap);
	
			parameters = parameterMap.getParameterObjectValues(requestScope, parameterObject);
			
			int recordCount = 0;

			final int skipResults = (currentPage - 1) * recordCountPerPage;
			final int maxResults = recordCountPerPage;
			
			sqlStr = this.getSumSql(sqlStr, skipResults, maxResults);
			
			System.out.println(sqlStr);
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try 
			{
				pstmt = springCon.prepareStatement(sqlStr);
				for (int i = 0, n = (null == parameters ? 0 : parameters.length); i < n; i++) 
				{
					pstmt.setObject(i + 1, parameters[i]);
				}
				rs = pstmt.executeQuery();
				if (rs.next()) 
				{
					recordCount = rs.getInt("CNT");
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{
				try
				{
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
				}
				catch (Exception e)
				{
				}
			}
			
			PaginationInfo reusltPaginationInfo = new PaginationInfo();
			reusltPaginationInfo.setRecordPerPage(recordCountPerPage);
			reusltPaginationInfo.setCurrentPage(currentPage);
			reusltPaginationInfo.setTotalRecord(recordCount);
			reusltPaginationInfo.setTotalPage(((recordCount - 1) / recordCountPerPage) + 1);
			
			result.setPaginationInfo(reusltPaginationInfo);
			
			SqlMapClientCallback action = new SqlMapClientCallback() 
			{
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException 
				{
					return executor.queryForList(statementName, parameterObject, skipResults, maxResults);
				}
			};
			
			try 
			{
				if (recordCount > 0) 
				{
					List list = (List) action.doInSqlMapClient(session);
					result.addAll(list);
				}
			} 
			catch (SQLException ex) 
			{
				throw getExceptionTranslator().translate("SqlMapClient operation", null, ex);
			} 
			finally 
			{
				try {
					if (springCon != null) 
					{
						if (transactionAware) 
						{
							springCon.close();
						} 
						else 
						{
							DataSourceUtils.doReleaseConnection(springCon, dataSource);
						}
					}
				} 
				catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}
		finally
		{
			if (ibatisCon == null) 
			{
				session.close();
			}
		}
		
		return result;
	}
	
	private String getSumSql(String sql, int offset, int limit)
	{
		sql = StringUtils.trim(sql);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1) CNT FROM (\n")
			.append(sql)
			.append("\n) a");
		return sb.toString();
	}
	
}
