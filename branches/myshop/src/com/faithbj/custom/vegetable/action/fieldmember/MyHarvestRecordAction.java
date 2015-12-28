package com.faithbj.custom.vegetable.action.fieldmember;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.entity.HarvestRecord;
import com.faithbj.custom.vegetable.service.HarvestRecordService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 前台Action类 - 我的收获记录管理
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	barney
 * @date 	2012-02-03
 * @version 1.0
 */

@ParentPackage("member")
public class MyHarvestRecordAction extends BaseShopAction
{
	private static final long serialVersionUID = -7053713599203613425L;

	@Resource
	private HarvestRecordService harvestRecordService = null;
	
	private HarvestRecord harvestRecord = null;
	
	//列表
	@InputConfig(resultName = "error")
	public String list() {
		
		Member currentMember = this.getLoginMember();
		
		if (pager == null)
		{
			pager = new Pager();
		}

		pager.setPropertyList(Arrays.asList("username"));
		pager.setKeywordList(Arrays.<Object>asList(currentMember.getUsername()));
		pager.setCompareTypeList(Arrays.asList("eq"));
		
		pager = this.harvestRecordService.findByPager(pager);
		return LIST;
	}

	public String content()
	{
		this.harvestRecord = this.harvestRecordService.load(this.id);
		
		return INPUT;
	}

	public String add()
	{
		return INPUT;
	}

	// 删除
	public String delete() {
		this.harvestRecordService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}

	//添加
	public String save() {

		System.out.println("************************\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
		System.out.println("save ...");
		
		System.out.println(this.harvestRecord.getFarmingBlockCode());
		System.out.println(this.harvestRecord.getSeedName());
		System.out.println(this.harvestRecord.getUnit());
		System.out.println(this.harvestRecord.getAmount());
		System.out.println(this.harvestRecord.getHarvestTime());
		
		this.harvestRecord.setOperateTime(new Date());
		this.harvestRecord.setOperateUser(this.getLoginMember().getUsername());
		
		this.harvestRecord.setUsername(this.getLoginMember().getUsername());
		
		this.harvestRecordService.save(this.harvestRecord);
		
		
		return this.list();
	}

	//更新
	public String update() {
		HarvestRecord persistent = this.harvestRecordService.load(this.id);
		
		if ("".equals(this.harvestRecord.getSeed().getId()))
		{
			this.harvestRecord.setSeed(null);
			persistent.setSeed(null);
		}
		if ("".equals(this.harvestRecord.getFarmingBlock().getId()))
		{
			this.harvestRecord.setFarmingBlock(null);
			persistent.setFarmingBlock(null);
		}
		
		this.harvestRecord.setOperateTime(new Date());
		this.harvestRecord.setOperateUser(this.getLoginMember().getUsername());
		
		BeanUtils.copyProperties(this.harvestRecord, persistent, new String[] {"id", "createDate", "modifyDate", "username"});
		
		this.harvestRecordService.update(persistent);
		
		return this.list();
	}
	
	public HarvestRecord getHarvestRecord()
	{
		return harvestRecord;
	}

	public void setHarvestRecord(HarvestRecord harvestRecord)
	{
		this.harvestRecord = harvestRecord;
	}

	
}
