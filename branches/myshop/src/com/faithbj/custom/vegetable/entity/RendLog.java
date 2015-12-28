package com.faithbj.custom.vegetable.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.faithbj.shop.entity.BaseEntity;

/**
 * 实体类 - 租赁日志
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class RendLog extends BaseEntity
{
	private static final long serialVersionUID = 3371968686743877745L;
	
	/**
	 * 租赁起始日期
	 */
	private Date startDate = null;
	/**
	 * 租赁结束日期
	 */
	private Date endDate = null;
	/**
	 * 租赁块所属会员名
	 */
	private String username = null;
	/**
	 * 是否启用年托管费用
	 */
	private Boolean isTrusteeFeeEnabled = null;
	/**
	 * 是否启用年配送费用
	 */
	private Boolean isDeliveryFeeEnabled = null;
	/**
	 * 所属土地块代码
	 */
	private String fieldBlockCode = null;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 代码
	 */
	private String code = null;
	/**
	 * 面积
	 */
	private Integer area = null;
	/**
	 * 操作时间
	 */
	private Date operateTime = null;
	/**
	 * 操作人
	 */
	private String operateUser = null;
	
	@Column
	public String getFieldBlockCode()
	{
		return this.fieldBlockCode;
	}
	@Column
	public String getUsername()
	{
		return this.username;
	}
	@Column(nullable = false)
	public Boolean getIsDeliveryFeeEnabled()
	{
		return this.isDeliveryFeeEnabled;
	}
	@Column(nullable = false)
	public Boolean getIsTrusteeFeeEnabled()
	{
		return this.isTrusteeFeeEnabled;
	}
	@Column
	public Date getEndDate()
	{
		return this.endDate;
	}
	@Column
	public Date getStartDate()
	{
		return this.startDate;
	}
	@Column(nullable = false)
	public String getName()
	{
		return this.name;
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
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}
	public void setOperateUser(String operateUser)
	{
		this.operateUser = operateUser;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setIsTrusteeFeeEnabled(Boolean isTrusteeFeeEnabled)
	{
		this.isTrusteeFeeEnabled = isTrusteeFeeEnabled;
	}
	public void setIsDeliveryFeeEnabled(Boolean isDeliveryFeeEnabled)
	{
		this.isDeliveryFeeEnabled = isDeliveryFeeEnabled;
	}
	public void setFieldBlockCode(String fieldBlockCode)
	{
		this.fieldBlockCode = fieldBlockCode;
	}
	public void setName(String name)
	{
		this.name = name;
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
