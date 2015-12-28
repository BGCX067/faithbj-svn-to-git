package com.faithbj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 
 * <p>Title: SpringHibernate测试基类，支持Spring注入，Hibernate懒加载</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-21
 * @version 1.0
 */

public class BaseSpringHibernateTest extends BaseSpringTest
{
	protected SessionFactory sessionFactory = null;

	@Override
	protected void onSetUp() throws Exception
	{
		super.onSetUp();
		
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}

	@Override
	protected void onTearDown() throws Exception
	{
		super.onTearDown();
		
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
}
