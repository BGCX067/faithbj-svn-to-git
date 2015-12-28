package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.faithbj.custom.vegetable.entity.HarvestRecord;
import com.faithbj.custom.vegetable.service.HarvestRecordService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 
 * <p>收获记录管理Action</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-02
 * @version 1.0
 */

@ParentPackage("admin")
public class HarvestRecordAction extends BaseAdminAction
{
	private static final long serialVersionUID = 1762161033236158746L;

	@Resource
	private HarvestRecordService harvestRecordService = null;
	
	private HarvestRecord harvestRecord = null;
	
	//列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = this.harvestRecordService.findByPager(pager);
		return LIST;
	}

	//添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		this.harvestRecord = this.harvestRecordService.load(id);
		return INPUT;
	}

	// 删除
	public String delete() {
		this.harvestRecordService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}

	//添加
	public String save() {

		this.harvestRecord.setOperateTime(new Date());
		this.harvestRecord.setOperateUser(((String) getSession("SPRING_SECURITY_LAST_USERNAME")).toLowerCase());
		
		this.harvestRecordService.save(this.harvestRecord);
		redirectionUrl = "harvest_record!list.action";
		return SUCCESS;
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
		this.harvestRecord.setOperateUser(((String) getSession("SPRING_SECURITY_LAST_USERNAME")).toLowerCase());
		
		BeanUtils.copyProperties(this.harvestRecord, persistent, new String[] {"id", "createDate", "modifyDate"});
		
		this.harvestRecordService.update(persistent);
		
		redirectionUrl = "harvest_record!list.action";
		return SUCCESS;
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
