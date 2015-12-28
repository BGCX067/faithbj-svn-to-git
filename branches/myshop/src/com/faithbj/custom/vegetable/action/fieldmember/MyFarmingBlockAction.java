package com.faithbj.custom.vegetable.action.fieldmember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.constant.FieldConstants;
import com.faithbj.custom.vegetable.entity.FarmingBlock;
import com.faithbj.custom.vegetable.entity.FarmingBlockDefaultImage;
import com.faithbj.custom.vegetable.enums.FarmingStatus;
import com.faithbj.custom.vegetable.service.FarmingBlockDefaultImageService;
import com.faithbj.custom.vegetable.service.FarmingBlockService;
import com.faithbj.custom.vegetable.service.SeedService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Seed;

/**
 * 前台Action类 - 我的地块管理
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	barney
 * @date 	2012-01-28
 * @version 1.0
 */

@ParentPackage("member")
public class MyFarmingBlockAction extends BaseShopAction
{
	private static final long serialVersionUID = -6857789989258024123L;

	@Resource
	private FarmingBlockDefaultImageService farmingBlockDefaultImageService = null;
	
	@Resource
	private FarmingBlockService farmingBlockService = null;
	
	private FarmingBlock farmingBlock = null;
	
	private String farmingBlockStatus = null;

	private String viewType = null;
	
	private String seedid = null;
	
	@Resource
	private SeedService seedService = null;

	public String batchSeeding()
	{
		if (StringUtils.isNotEmpty(this.seedid))
		{
			Seed seed = this.seedService.load(this.seedid);
			if (seed == null)
			{
				return ajaxJsonSuccessMessage("种子不存在");
			}
			
			if (this.ids != null && this.ids.length > 0)
			{
				List<FarmingBlock> farmingBlockList = this.farmingBlockService.get(this.ids);
				if (farmingBlockList != null)
				{
					for (FarmingBlock farmingBlock : farmingBlockList)
					{
						if (FarmingStatus.Free.getValue().equals(farmingBlock.getStatus()))
						{
							farmingBlock.setSeedName(seed.getName());
							farmingBlock.setSeed(seed);
							farmingBlock.setStatus(FarmingStatus.Seeding.getValue());
							farmingBlock.setOperateTime(new Date());
							farmingBlock.setOperateUser(this.getLoginMember().getUsername());
							
							this.farmingBlockService.save(farmingBlock);
						}
					}
				}
				return ajaxJsonSuccessMessage("播种成功！");
			}
			else
			{
				return ajaxJsonSuccessMessage("请先选中耕种地块");
			}
		}
		else
		{
			return ajaxJsonSuccessMessage("请先选择种子");
		}
	}
	
	public String batchClean()
	{
		if (this.ids != null && this.ids.length > 0)
		{
			List<FarmingBlock> farmingBlockList = this.farmingBlockService.get(this.ids);
			if (farmingBlockList != null)
			{
				for (FarmingBlock farmingBlock : farmingBlockList)
				{
					if (!FarmingStatus.Free.getValue().equals(farmingBlock.getStatus()) && !FarmingStatus.Cleaning.getValue().equals(farmingBlock.getStatus()))
					{
						farmingBlock.setSeedName(null);
						farmingBlock.setSeed(null);
						farmingBlock.setStatus(FarmingStatus.Cleaning.getValue());
						farmingBlock.setOperateTime(new Date());
						farmingBlock.setOperateUser(this.getLoginMember().getUsername());
						
						this.farmingBlockService.save(farmingBlock);
					}
				}
			}
			return ajaxJsonSuccessMessage("清理成功！");
		}
		else
		{
			return ajaxJsonSuccessMessage("请先选中耕种地块");
		}
	}
	
	public String content()
	{
		this.farmingBlock = this.farmingBlockService.load(this.id);
		
		this.addDefaultImage(Arrays.asList(farmingBlock));
		
		return INPUT;
	}

	public String manage()
	{
		Member currentMember = this.getLoginMember();
		if (currentMember != null)
		{
			if (pager == null)
			{
				pager = new Pager();
			}

			List<String> propertyList = new ArrayList<String>(Arrays.asList("username", "rendBlock.isTrusteeFeeEnabled"));
			List<Object> keyworkdList = new ArrayList<Object>(Arrays.<Object>asList(currentMember.getUsername(), true));
			List<String> compareTypeList = new ArrayList<String>(Arrays.asList("eq", "eq"));
			
			if (StringUtils.isNotBlank(this.farmingBlockStatus))
			{
				propertyList.add("status");
				keyworkdList.add(this.farmingBlockStatus);
				compareTypeList.add("eq");
			}

			pager.setPropertyList(propertyList);
			pager.setKeywordList(keyworkdList);
			pager.setCompareTypeList(compareTypeList);
			
			pager = this.farmingBlockService.findByPager(pager);
			
			List<FarmingBlock> farmingBlockList = pager.getList();
			if (farmingBlockList != null && !farmingBlockList.isEmpty())
			{
				this.addDefaultImage(farmingBlockList);
			}
			
		}
		
		return "manage";
	}
	
	public String list() {
		
		Member currentMember = this.getLoginMember();
		if (currentMember != null)
		{
			if (pager == null)
			{
				pager = new Pager();
			}

			List<String> propertyList = new ArrayList<String>(Arrays.asList("username"));
			List<Object> keyworkdList = new ArrayList<Object>(Arrays.<Object>asList(currentMember.getUsername()));
			List<String> compareTypeList = new ArrayList<String>(Arrays.asList("eq"));
			
			if (StringUtils.isNotBlank(this.farmingBlockStatus))
			{
				propertyList.add("status");
				keyworkdList.add(this.farmingBlockStatus);
				compareTypeList.add("eq");
			}

			pager.setPropertyList(propertyList);
			pager.setKeywordList(keyworkdList);
			pager.setCompareTypeList(compareTypeList);
			
			pager = this.farmingBlockService.findByPager(pager);
			
			List<FarmingBlock> farmingBlockList = pager.getList();
			if (farmingBlockList != null && !farmingBlockList.isEmpty())
			{
				this.addDefaultImage(farmingBlockList);
			}
			
		}
		
		return LIST;
	}

	//更新
	public String update() {
		FarmingBlock persistent = this.farmingBlockService.load(this.id);
		
		if ("".equals(this.farmingBlock.getSeed().getId()))
		{
			this.farmingBlock.setSeed(null);
			persistent.setSeed(null);
		}
		
		this.farmingBlock.setOperateTime(new Date());
		this.farmingBlock.setOperateUser(this.getLoginMember().getUsername());
		
		BeanUtils.copyProperties(this.farmingBlock, persistent, new String[] {
				"id", "createDate", "modifyDate", "productImageListStore", "username", "member", "rendBlockCode", "rendBlock", "area", "code", "isMarketable"});
		
		this.farmingBlockService.update(persistent);
		
		return list();
	}
	
	private void addDefaultImage(List<FarmingBlock> farmingBlockList)
	{
		List<FarmingBlockDefaultImage> defaultImageList = this.farmingBlockDefaultImageService.getAll();
		
		for (FarmingBlock farmingBlock : farmingBlockList)
		{
			if (StringUtils.isEmpty(farmingBlock.getProductImageListStore()))
			{
				farmingBlock.setProductImageListStore(this.getDefaultProductImageListStore(farmingBlock, defaultImageList));
			}
		}
	}
	
	private String getDefaultImageStore(String status)
	{
		String result = null;
		if (FarmingStatus.Free.getValue().equals(status))
		{
			result = FieldConstants.DefaultFreeImageStore;
		}
		else if (FarmingStatus.Seeding.getValue().equals(status))
		{
			result = FieldConstants.DefaultSeedImageStore;
		}
		else if (FarmingStatus.Growing.getValue().equals(status))
		{
			result = FieldConstants.DefaultGrowImageStore;
		}
		else if (FarmingStatus.Harvest.getValue().equals(status))
		{
			result = FieldConstants.DefaultHarvestImageStore;
		}
		else if (FarmingStatus.Cleaning.getValue().equals(status))
		{
			result = FieldConstants.DefaultCleanImageStore;
		}
		return result;
	}
	
	private String getDefaultProductImageListStore(FarmingBlock farmingBlock, List<FarmingBlockDefaultImage> defaultImageList)
	{
		String result = null;
		
		FarmingBlockDefaultImage targetImage = null;
		for (FarmingBlockDefaultImage defaultImage : defaultImageList)
		{
			if (defaultImage.getStatus().equals(farmingBlock.getStatus()))
			{
				targetImage = defaultImage;
				if (StringUtils.trimToEmpty(farmingBlock.getSeedName()).equals(StringUtils.trimToEmpty(defaultImage.getSeedName())))
				{
					break;
				}
			}
		}
		if (targetImage == null)
		{
			result = this.getDefaultImageStore(farmingBlock.getStatus());
		}
		else
		{
			result = targetImage.getProductImageListStore();
		}
		
		return result;
	}
	
	public String getViewType()
	{
		return viewType;
	}
	public void setViewType(String viewType)
	{
		this.viewType = viewType;
	}

	public FarmingBlock getFarmingBlock()
	{
		return farmingBlock;
	}

	public void setFarmingBlock(FarmingBlock farmingBlock)
	{
		this.farmingBlock = farmingBlock;
	}

	public String getFarmingBlockStatus()
	{
		return farmingBlockStatus;
	}

	public void setFarmingBlockStatus(String farmingBlockStatus)
	{
		this.farmingBlockStatus = farmingBlockStatus;
	}

	public String getSeedid()
	{
		return seedid;
	}

	public void setSeedid(String seedid)
	{
		this.seedid = seedid;
	}
}
