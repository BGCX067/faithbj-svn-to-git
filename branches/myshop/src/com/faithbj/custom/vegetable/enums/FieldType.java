package com.faithbj.custom.vegetable.enums;

/**
 * 
 * <p>土地类型</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-02
 * @version 1.0
 */

public enum FieldType
{
	/**
	 * 室外，露天
	 */
	Outdoor("0"),
	/**
	 * 室内，大棚
	 */
	Indoor("1");
	

	private final String value;
	FieldType(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
