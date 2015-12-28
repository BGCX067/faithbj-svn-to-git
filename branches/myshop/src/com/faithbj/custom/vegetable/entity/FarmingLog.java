package com.faithbj.custom.vegetable.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.faithbj.shop.entity.BaseEntity;

/**
 * 实体类 - 耕种日志
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class FarmingLog extends BaseEntity
{
	private static final long serialVersionUID = 4149432082832586565L;

	/**
	 * 耕种状态
	 */
	private String status = null;
	/**
	 * 操作时间
	 */
	private Date operateTime = null;
	/**
	 * 操作人
	 */
	private String operateUser = null;
	/**
	 * 租赁块代码
	 */
	private String rendBlockCode = null;
	/**
	 * 会员名称
	 */
	private String username = null;
	/**
	 * 种子名称
	 */
	private String seedName = null;
	/**
	 * 代码
	 */
	private String code = null;
	/**
	 * 面积
	 */
	private Integer area = null;
	
	@Column
	public String getSeedName()
	{
		return this.seedName;
	}
	@Column
	public String getUsername()
	{
		return this.username;
	}
	@Column
	public String getRendBlockCode()
	{
		return this.rendBlockCode;
	}
	@Column
	public Date getOperateTime()
	{
		return this.operateTime;
	}
	@Column(length = 64)
	public String getOperateUser()
	{
		return this.operateUser;
	}
	@Column(length = 1)
	public String getStatus()
	{
		return this.status;
	}
	@Column(nullable = false, length = 16)
	public String getCode()
	{
		return this.code;
	}
	@Column(nullable = false)
	public Integer getArea()
	{
		return this.area;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}
	public void setOperateUser(String operateUser)
	{
		this.operateUser = operateUser;
	}
	public void setRendBlockCode(String rendBlockCode)
	{
		this.rendBlockCode = rendBlockCode;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setSeedName(String seedName)
	{
		this.seedName = seedName;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public void setArea(Integer area)
	{
		this.area = area;
	}
	
}
