package com.faithbj.shop.action.shop;

import javax.annotation.Resource;

import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.MessageService;

import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * 前台Action类 - 会员中心
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("member")
public class MemberCenterAction extends BaseShopAction {

	private static final long serialVersionUID = -3568504222758246021L;
	
	@Resource
	MemberService memberService;
	@Resource
	MessageService messageService;
	
	// 会员中心首页
	public String index() {
		return "index";
	}

	// 获取未读消息数量
	public Long getUnreadMessageCount() {
		return messageService.getUnreadMessageCount(getLoginMember());
	}
	
}
