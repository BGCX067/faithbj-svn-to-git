package com.faithbj.custom.vegetable.action.vegetableadmin;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.service.SeedService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.faithbj.shop.entity.Seed;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台Action类 - 种子
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-28
 * @version 1.0
 */

@ParentPackage("admin")
public class SeedAction extends BaseAdminAction {

	private static final long serialVersionUID = -2080980180511054311L;

	@Resource
	private SeedService seedService;
	
	private Seed seed;
	private Date startDate;
	private Date endDate;

	//弹出
	@InputConfig(resultName = "error")
	public String popup() {
		pager = this.seedService.findByPager(pager);
		return "popup";
	}
	

	//种子列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = seedService.findByPager(pager);
		return LIST;
	}
	
	// 删除
	public String delete() {
		seedService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}
	
	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		seed = seedService.load(id);
		return INPUT;
	}
	
	// 是否已存在 ajax验证
	public String checkName() {
		String oldValue = getParameter("oldValue");
		String newValue;
		try {
				newValue = new String(seed.getName().getBytes("ISO-8859-1"),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			newValue = "";
			return ajaxText("false");
		}
		if (seedService.isUnique("name", oldValue, newValue)) {
			return ajaxText("true");
		} else {
			return ajaxText("false");
		}
	}
	
	//保存
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "seed.name", message = "名称不允许为空!")
			},
			regexFields = {
					@RegexFieldValidator(fieldName = "seed.name", expression = "^[\u4e00-\u9fa5]+$", message = "用户名只允许包含中文!") 
				},
			requiredFields = {
					@RequiredFieldValidator(fieldName = "seed.startDate", message = "种子周期的开始时间不能为空！"),
					@RequiredFieldValidator(fieldName = "seed.endDate", message = "种子周期的结束时间不能为空！")
			}
	)
	@InputConfig(resultName = "error")
	public String save() {
		seed.setName(seed.getName());
		seed.setType(seed.isType());
		seed.setStartDate(seed.getStartDate());
		seed.setEndDate(seed.getEndDate());
		seedService.save(seed);
		redirectionUrl = "seed!list.action";
		return SUCCESS;
	}
	
	@Validations(
			requiredFields = {
					@RequiredFieldValidator(fieldName = "seed.startDate", message = "种子周期的开始时间不能为空！"),
					@RequiredFieldValidator(fieldName = "seed.endDate", message = "种子周期的结束时间不能为空！")
			}
	)
	@InputConfig(resultName = "error")
	public String update() {
		Seed loadSeed = seedService.load(id);
		seed.setStartDate(seed.getStartDate());
		seed.setEndDate(seed.getEndDate());
		seedService.update(loadSeed);
		redirectionUrl = "seed!list.action";
		return SUCCESS;
	}
	
	public Seed getSeed() {
		return seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}

	public SeedService getSeedService() {
		return seedService;
	}

	public void setSeedService(SeedService seedService) {
		this.seedService = seedService;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
