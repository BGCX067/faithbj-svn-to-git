package com.faithbj;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * 
 * <p>Title: Spring测试基类，支持Spring注入</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-21
 * @version 1.0
 */

public class BaseSpringTest extends AbstractDependencyInjectionSpringContextTests
{
	public BaseSpringTest() 
	{
		super();
		this.setAutowireMode(AUTOWIRE_BY_NAME);
		this.setPopulateProtectedVariables(true);
	}
	
	@Override
	protected String[] getConfigLocations() 
	{
		return new String[]{
				"classpath:applicationContext.xml",
				"classpath:applicationContext-compass.xml", 
				"classpath:applicationContext-security.xml", 
			};
	}
}
