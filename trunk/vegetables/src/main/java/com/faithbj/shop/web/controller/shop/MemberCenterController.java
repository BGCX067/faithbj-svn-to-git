package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.DepositService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.MessageService;
import com.faithbj.shop.support.annotation.NeedNavigation;



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

@Controller
@RequestMapping("/cjlhome/member_center")
public class MemberCenterController  extends BaseShopController implements Serializable{

	private static final long serialVersionUID = -3568504222758246021L;
	
	@Resource
	MemberService memberService;
	@Resource
	MessageService messageService;
	@Resource
	DepositService depositService;
	
	// 会员中心首页
	@RequestMapping("/index")
	@NeedNavigation
	public String index(ModelMap map) {
		Members loginMember = getLoginMember();
		long unreadMessageCount =messageService.getUnreadMessageCount(loginMember);// 获取未读消息数量
		map.put("loginMember", loginMember);
		map.put("unreadMessageCount", unreadMessageCount);
		return "shop/member_center_index";
	}

	
}
