package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.DepositService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.support.annotation.NeedNavigation;
/**
 * 前台Action类 - 预存款
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-22
 * @version 1.0
 */

@Controller
@RequestMapping("/cjlhome/deposit")
public class DepositController extends BaseShopController implements Serializable{
	private static final long serialVersionUID = 1L;
	@Resource
	private DepositService depositService;
	@Resource
	private MemberService memberService;
	
	
	//预存款记录列表
	@RequestMapping("list")
	@NeedNavigation
	public String list(ModelMap map,HttpServletRequest request){
		addSystemConfig(map);
		HttpSession session = request.getSession();
		String usrname = (String) session.getAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME);
		Members loginMember = memberService.getMemberByUsername(usrname);
		pager = depositService.getDepositPager(loginMember, pager);
		map.put("loginMember", loginMember);
		map.put("pager", pager);
		return "shop/deposit_list";
	}
	
	//充钱
	@RequestMapping("recharge")
	@NeedNavigation
	public String recharge(ModelMap map,HttpServletRequest request){;
		addSystemConfig(map);
		HttpSession session = request.getSession();
		String usrname = (String) session.getAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME);
		Members loginMember = memberService.getMemberByUsername(usrname);
		pager = depositService.getDepositPager(loginMember, pager);
		map.put("loginMember", loginMember);
		map.put("pager", pager);
		return "shop/deposit_recharge";
	}
}
