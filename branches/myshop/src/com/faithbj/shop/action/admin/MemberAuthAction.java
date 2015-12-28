package com.faithbj.shop.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.service.MemberAuthService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@ParentPackage("admin")
public class MemberAuthAction extends BaseAdminAction
{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private MemberAuthService memberAuthService;
	//种子列表
	@InputConfig(resultName = "error")
	public String list() {
		pager = memberAuthService.findByPager(pager);
		return LIST;
	}

}
