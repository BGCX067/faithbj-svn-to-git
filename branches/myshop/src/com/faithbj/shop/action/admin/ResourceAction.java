package com.faithbj.shop.action.admin;

import com.faithbj.shop.entity.Resource;
import com.faithbj.shop.service.ResourceService;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台Action类 - 资源
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("admin")
public class ResourceAction extends BaseAdminAction {

	private static final long serialVersionUID = -1066168819528324882L;

	private Resource resource;

	@javax.annotation.Resource
	private ResourceService resourceService;

	// 是否已存在ajax验证
	public String checkName() {
		String oldValue = getParameter("oldValue");
		String newValue = resource.getName();
		if (resourceService.isUnique("name", oldValue, newValue)) {
			return ajaxText("true");
		} else {
			return ajaxText("false");
		}
	}

	// 是否已存在ajax验证
	public String checkValue() {
		String oldValue = getParameter("oldValue");
		String newValue = resource.getValue();
		if (resourceService.isUnique("value", oldValue, newValue)) {
			return ajaxText("true");
		} else {
			return ajaxText("false");
		}
	}

	// 列表
	public String list() {
		pager = resourceService.findByPager(pager);
		return LIST;
	}

	// 删除
	public String delete() throws Exception {
		resourceService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}

	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		resource = resourceService.load(id);
		return INPUT;
	}

	// 保存
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "resource.name", message = "资源名称不允许为空!"),
			@RequiredStringValidator(fieldName = "resource.value", message = "资源值不允许为空!")
		}
	)
	@InputConfig(resultName = "error")
	public String save() throws Exception {
		resource.setRoleSet(null);
		resourceService.save(resource);
		redirectionUrl = "resource!list.action";
		return SUCCESS;
	}

	// 更新
	@Validations(
		requiredStrings = { 
			@RequiredStringValidator(fieldName = "resource.name", message = "资源名称不允许为空!"),
			@RequiredStringValidator(fieldName = "resource.value", message = "资源值不允许为空!")
		}
	)
	@InputConfig(resultName = "error")
	public String update() throws Exception {
		Resource persistent = resourceService.load(id);
		if (persistent.getIsSystem()) {
			addActionError("系统内置资源不允许修改!");
			return ERROR;
		}
		BeanUtils.copyProperties(resource, persistent, new String[] {"id", "createDate", "modifyDate", "isSystem", "roleSet"});
		resourceService.update(persistent);
		redirectionUrl = "resource!list.action";
		return SUCCESS;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
