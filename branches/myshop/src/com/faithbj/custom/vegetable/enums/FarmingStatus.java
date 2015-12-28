package com.faithbj.custom.vegetable.enums;

/**
 * 
 * <p>耕种状态</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-02
 * @version 1.0
 */

public enum FarmingStatus
{
	/**
	 * 空闲
	 */
	Free("0"),
	/**
	 * 播种
	 */
	Seeding("2"),
	/**
	 * 生长
	 */
	Growing("4"),
	/**
	 * 收获
	 */
	Harvest("6"),
	/**
	 * 清理
	 */
	Cleaning("8");
	
	private final String value;
	FarmingStatus(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
