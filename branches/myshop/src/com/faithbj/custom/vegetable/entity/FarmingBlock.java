package com.faithbj.custom.vegetable.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Seed;

/**
 * 实体类 - 耕种块
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class FarmingBlock extends BlockFee 
{
	private static final long serialVersionUID = -6185274101524914401L;
	
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
	 * 所属租赁块
	 */
	private RendBlock rendBlock = null;
	/**
	 * 租赁块代码
	 */
	private String rendBlockCode = null;
	/**
	 * 所属会员
	 */
	private Member member = null;
	/**
	 * 会员名称
	 */
	private String username = null;
	/**
	 * 种子
	 */
	private Seed seed = null;
	/**
	 * 种子名称
	 */
	private String seedName = null;
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public RendBlock getRendBlock()
	{
		return rendBlock;
	}

	public void setRendBlock(RendBlock rendBlock)
	{
		this.rendBlock = rendBlock;
	}

	@OneToOne(fetch = FetchType.EAGER)
	public Seed getSeed()
	{
		return seed;
	}

	public void setSeed(Seed seed)
	{
		this.seed = seed;
	}

	@Column(length = 1)
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Column(length = 64)
	public String getOperateUser()
	{
		return operateUser;
	}

	public void setOperateUser(String operateUser)
	{
		this.operateUser = operateUser;
	}

	@Column
	public Date getOperateTime()
	{
		return operateTime;
	}

	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	@Column
	public String getRendBlockCode()
	{
		return rendBlockCode;
	}

	public void setRendBlockCode(String rendBlockCode)
	{
		this.rendBlockCode = rendBlockCode;
	}

	@Column
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column
	public String getSeedName()
	{
		return seedName;
	}

	public void setSeedName(String seedName)
	{
		this.seedName = seedName;
	}

	
	//---------------------------------------------
	@Column(nullable = false, length = 16)
	public String getCode()
	{
		return super.getCode();
	}
	@Column(nullable = false)
	public Integer getArea()
	{
		return super.getArea();
	}
	@Column(nullable = true, length = 4000)
	public String getProductImageListStore()
	{
		return super.getProductImageListStore();
	}
}
