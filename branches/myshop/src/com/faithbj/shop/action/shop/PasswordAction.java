package com.faithbj.shop.action.shop;

import javax.annotation.Resource;

import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.MemberService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

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

@ParentPackage("member")
public class PasswordAction extends BaseShopAction {

	private static final long serialVersionUID = 7986413434419152864L;
	
	private Member member;
	private String oldPassword;
	
	@Resource
	private MemberService memberService;
	
	// 密码修改
	public String edit() {
		member = getLoginMember();
		return INPUT;
	}

	// 密码更新
	@Validations(
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "member.password", minLength = "4", maxLength = "20", message = "新密码长度必须在${minLength}到${maxLength}之间!") 
		}
	)
	@InputConfig(resultName = "error")
	public String update() {
		Member persistent = getLoginMember();
		if (StringUtils.isNotEmpty(oldPassword) && StringUtils.isNotEmpty(member.getPassword())) {
			String oldPasswordMd5 = DigestUtils.md5Hex(oldPassword);
			if (!StringUtils.equals(persistent.getPassword(), oldPasswordMd5)) {
				addActionError("旧密码不正确!");
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
