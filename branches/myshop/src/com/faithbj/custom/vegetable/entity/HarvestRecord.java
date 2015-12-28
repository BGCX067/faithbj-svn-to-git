package com.faithbj.custom.vegetable.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.faithbj.shop.entity.BaseEntity;
import com.faithbj.shop.entity.Seed;

/**
 * 实体类 - 土地块和租赁块的抽象基类
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-25
 * @version 1.0
 */

@Entity
public class HarvestRecord extends BaseEntity 
{
	private static final long serialVersionUID = -1797383418155252562L;

	private Seed seed = null;
	private String seedName = null;
	private Date harvestTime = null;
	private Integer amount = null;
	private String unit = null;
	private FarmingBlock farmingBlock = null;
	private String farmingBlockCode = null;
	private String username = null;
	private String operateUser = null;
	private Date operateTime = null;
	
	@OneToOne(fetch = FetchType.EAGER)
	public Seed getSeed()
	{
		return seed;
	}
	public void setSeed(Seed seed)
	{
		this.seed = seed;
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
	
	@Column
	public Date getHarvestTime()
	{
		return harvestTime;
	}
	public void setHarvestTime(Date harvestTime)
	{
		this.harvestTime = harvestTime;
	}
	
	@Column
	public Integer getAmount()
	{
		return amount;
	}
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}
	
	@Column
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	public FarmingBlock getFarmingBlock()
	{
		return farmingBlock;
	}
	public void setFarmingBlock(FarmingBlock farmingBlock)
	{
		this.farmingBlock = farmingBlock;
	}
	
	@Column
	public String getFarmingBlockCode()
	{
		return farmingBlockCode;
	}
	public void setFarmingBlockCode(String farmingBlockCode)
	{
		this.farmingBlockCode = farmingBlockCode;
	}
	
	@Column
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
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
}
