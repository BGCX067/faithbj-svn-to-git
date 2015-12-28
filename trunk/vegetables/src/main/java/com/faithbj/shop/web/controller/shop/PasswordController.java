package com.faithbj.shop.web.controller.shop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.support.annotation.NeedNavigation;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 前台Action类 - 密码、安全问题
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/password")
public class PasswordController extends BaseShopController {

	private static final long serialVersionUID = 7986413434419152864L;
	
	@Resource
	private MemberService memberService;
	
	// 密码修改
	@RequestMapping("edit")
	@NeedNavigation
	public String edit(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		return "/shop/password_input";
	}

	// 密码更新
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(ModelMap map,Members member,String oldPassword,HttpServletRequest request) {
		Members persistent = getLoginMember(request);
		if (StringUtils.isNotEmpty(oldPassword) && StringUtils.isNotEmpty(member.getPassword())) {
			String oldPasswordMd5 = DigestUtils.md5Hex(oldPassword);
			if (!StringUtils.equals(persistent.getPassword(), oldPasswordMd5)) {
				map.put("errorMessage", "旧密码不正确!");
				return ERROR;
			}
			String newPasswordMd5 = DigestUtils.md5Hex(member.getPassword());
			persistent.setPassword(newPasswordMd5);
		}
		if (StringUtils.isNotEmpty(member.getSafeQuestion()) && StringUtils.isNotEmpty(member.getSafeAnswer())) {
			persistent.setSafeQuestion(member.getSafeQuestion());
			persistent.setSafeAnswer(member.getSafeAnswer());
		}
		memberService.update(persistent);
		return SUCCESS;
	}

}
