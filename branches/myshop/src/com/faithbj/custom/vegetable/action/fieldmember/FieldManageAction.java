package com.faithbj.custom.vegetable.action.fieldmember;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.entity.FieldBlockCartItem;
import com.faithbj.custom.vegetable.entity.HarvestRecord;
import com.faithbj.custom.vegetable.entity.MemberAuth;
import com.faithbj.custom.vegetable.entity.RendBlock;
import com.faithbj.custom.vegetable.service.HarvestRecordService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.MemberAuthService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 前台Action类 - 土地管理
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	barney
 * @date 	2012-01-28
 * @version 1.0
 */

@ParentPackage("member")
public class FieldManageAction extends BaseShopAction
{
	private static final long serialVersionUID = 1L;
	
	private int freeSize = 0;
	private int seedingSize = 0;
	private int growingSize = 0;
	private int harvestSize = 0;
	private int cleanSize = 0;
	
	private int rendBlockSize = 0;
	
	private int favoriteSize = 0;
	private int harvestRecordSize = 0;
	private int fieldBlockCartItemSize = 0;
	
	@Resource
	private HarvestRecordService harvestRecordService = null;

	public String index() {
		
		Member member = this.getLoginMember();
		
		List<RendBlock> rendBlockList = member.getRendBlockList();
		if (rendBlockList != null && !rendBlockList.isEmpty())
		{
			this.rendBlockSize = rendBlockList.size();
			
			for (RendBlock rendBlock : rendBlockList)
			{
				if (rendBlock.getIsTrusteeFeeEnabled())
				{
					for (FarmingBlock farmingBlock : rendBlock.getFarmingBlockList())
					{
						if ("0".equals(farmingBlock.getStatus()))
						{
							this.freeSize++;
						}
						else if ("2".equals(farmingBlock.getStatus()))
						{
							this.seedingSize++;
						}
						else if ("4".equals(farmingBlock.getStatus()))
						{
							this.growingSize++;
						}
						else if ("6".equals(farmingBlock.getStatus()))
						{
							this.harvestSize++;
						}
						else if ("8".equals(farmingBlock.getStatus()))
						{
							this.cleanSize++;
						}
					}
				}
			}
		}
		
		Set<FieldBlock> favoriteSet = member.getFavoriteFieldBlockSet();
		if (favoriteSet != null && !favoriteSet.isEmpty())
		{
			this.favoriteSize = favoriteSet.size();
		}
		
		Set<FieldBlockCartItem> fieldBlockCartItemSet = member.getFieldBlockCartItemSet();
		if (fieldBlockCartItemSet != null && !fieldBlockCartItemSet.isEmpty())
		{
			this.fieldBlockCartItemSize = fieldBlockCartItemSet.size();
		}
		
		List<HarvestRecord> harvestRecordList = this.harvestRecordService.getList("username", member.getUsername());
		if (harvestRecordList != null && !harvestRecordList.isEmpty())
		{
			this.harvestRecordSize = harvestRecordList.size();
		}
		
		return "index";
	}

	public int getFreeSize()
	{
		return freeSize;
	}
	public void setFreeSize(int freeSize)
	{
		this.freeSize = freeSize;
	}
	public int getSeedingSize()
	{
		return seedingSize;
	}
	public void setSeedingSize(int seedingSize)
	{
		this.seedingSize = seedingSize;
	}
	public int getGrowingSize()
	{
		return growingSize;
	}
	public void setGrowingSize(int growingSize)
	{
		this.growingSize = growingSize;
	}
	public int getHarvestSize()
	{
		return harvestSize;
	}
	public void setHarvestSize(int harvestSize)
	{
		this.harvestSize = harvestSize;
	}
	public int getCleanSize()
	{
		return cleanSize;
	}
	public void setCleanSize(int cleanSize)
	{
		this.cleanSize = cleanSize;
	}
	public int getRendBlockSize()
	{
		return rendBlockSize;
	}
	public void setRendBlockSize(int rendBlockSize)
	{
		this.rendBlockSize = rendBlockSize;
	}
	public int getFavoriteSize()
	{
		return favoriteSize;
	}
	public void setFavoriteSize(int favoriteSize)
	{
		this.favoriteSize = favoriteSize;
	}
	public int getHarvestRecordSize()
	{
		return harvestRecordSize;
	}
	public void setHarvestRecordSize(int harvestRecordSize)
	{
		this.harvestRecordSize = harvestRecordSize;
	}
	public int getFieldBlockCartItemSize()
	{
		return fieldBlockCartItemSize;
	}
	public void setFieldBlockCartItemSize(int fieldBlockCartItemSize)
	{
		this.fieldBlockCartItemSize = fieldBlockCartItemSize;
	}
}
