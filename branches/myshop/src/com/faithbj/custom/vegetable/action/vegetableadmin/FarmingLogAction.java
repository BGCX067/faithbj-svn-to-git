package com.faithbj.custom.vegetable.action.vegetableadmin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.service.FarmingLogService;
import com.faithbj.shop.action.admin.BaseAdminAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 耕种日志Action
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-04
 * @version 1.0
 */

@ParentPackage("admin")
public class FarmingLogAction extends BaseAdminAction
{
	private static final long serialVersionUID = 1L;

	@Resource
	private FarmingLogService farmingLogService = null;

	//列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = this.farmingLogService.findByPager(pager);
		return LIST;
	}
}
