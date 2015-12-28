package com.faithbj.custom.vegetable.action.fieldmember;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.service.SeedService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 后台Action类 - 种子
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-02-05
 * @version 1.0
 */

@ParentPackage("shop")
public class SeedAction extends BaseShopAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SeedService seedService;
	
	//弹出
	@InputConfig(resultName = "error")
	public String popup() {
		pager = this.seedService.findByPager(pager);
		return "popup";
	}

	public SeedService getSeedService() {
		return seedService;
	}

	public void setSeedService(SeedService seedService) {
		this.seedService = seedService;
	}

}
