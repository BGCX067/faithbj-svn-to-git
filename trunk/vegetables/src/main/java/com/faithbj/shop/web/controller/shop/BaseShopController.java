package com.faithbj.shop.web.controller.shop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Footer;
import com.faithbj.shop.model.entity.FriendLink;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Navigation;
import com.faithbj.shop.service.FooterService;
import com.faithbj.shop.service.FriendLinkService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.NavigationService;
import com.faithbj.shop.utils.SystemConfigUtil;

/**
 * 前台Action类 - 前台基类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class BaseShopController {
	
	protected String[] ids;
	protected Pager pager;
	protected String logInfo;// 日志记录信息
	protected String redirectUrl;// 操作提示后的跳转URL,为null则返回前一页
	
	/**
	 * 返回成功页面
	 */
	protected static final String SUCCESS="shop/success";
	/**
	 * 返回失败页面
	 */
	protected static final String ERROR="shop/error";
	protected static final String STATUS= "status";
	
	@Resource
	protected NavigationService navigationService;
	
	@Resource
	protected SystemConfigUtil systemConfigUtil;
	
	
	@Resource
	protected MemberService memberService;
	@Resource
	protected FriendLinkService friendLinkService;
	@Resource
	protected FooterService footerService;
	

	public void addNavigationList(ModelMap map) {
		map.put("navigationList",navigationService.getTopNavigationList());
		
		List<Navigation> middle=navigationService.getMiddleNavigationList();
		map.put("middleNavigationList",middle);
		map.put("bottomNavigationList",navigationService.getBottomNavigationList());
	}

	public void addSystemConfig(ModelMap map) {
		map.put("setting", systemConfigUtil);
	}

	/**
	 * 获得登陆用户的用户名
	 * @param map
	 * @return
	 */
	protected Members getLoginMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME);
		Members loginMember = memberService.getMemberByUsername(username);
		return loginMember;
	}
	
	/**
	 * 获得登陆用户的用户名(无参)
	 * @param map
	 * @return
	 */
	protected Members getLoginMember() {
		Object memberObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(memberObject instanceof Members)
			return (Members)memberObject;
		else
			return null;
	}
	
	
	public void addFriendLinkList(ModelMap map) {
		map.put("friendLinkList",getFriendLinkList());
	}
	public List<FriendLink> getFriendLinkList() {
		return friendLinkService.getAll();
	}
	public List<FriendLink> getPictureFriendLinkList() {
		return friendLinkService.getPictureFriendLinkList();
	}

	public List<FriendLink> getTextFriendLinkList() {
		return friendLinkService.getTextFriendLinkList();
	}

	public Footer getFooter() {
		return footerService.getFooter();
	}
}
