package com.faithbj.custom.vegetable.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.faithbj.shop.entity.BaseEntity;
import com.faithbj.shop.entity.Member;

/**
 * 实体类 - 蔬菜会员权限
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class MemberAuth extends BaseEntity 
{
	private static final long serialVersionUID = 1L;

	/**
	 * 会员起始日期
	 */
	private Date startDate = null;
	/**
	 * 会员结束日期
	 */
	private Date endDate = null;
	/**
	 * 会员资格
	 */
	private String type = null;
	
	private Member member = null;
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	@Column(length = 1)
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Column
	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	@Column
	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	
}
