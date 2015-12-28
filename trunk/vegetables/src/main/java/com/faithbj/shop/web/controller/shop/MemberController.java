package com.faithbj.shop.web.controller.shop;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faithbj.shop.model.configuration.CartItemCookie;
import com.faithbj.shop.model.entity.CartItem;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.service.CartItemService;
import com.faithbj.shop.service.MemberRankService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;

/**
 * 前台 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
public class MemberController extends BaseShopController {
//
//	private static final long serialVersionUID = 1115660086350733102L;
//
//	private Members member;
//	private Boolean isAgreeAgreement;
//	private String passwordRecoverKey;
//
	@Resource
	private MemberService memberService;
	@Resource
	private MemberRankService memberRankService;
	
//	@Resource
//	private AgreementService agreementService;
//	@Resource
//	private CaptchaService captchaService;
//	@Resource
//	private MailService mailService;
	@Resource
	private ProductService productService;
	@Resource
	private CartItemService cartItemService;
	
	private final String MUSTREGISTER="必须同意注册协议才可进行注册操作！";
	private final String CLOSEREGISTER="本站注册功能现已关闭！";
	private final String OPERRATE_RETURN_URL="cjlhome/index";
//	
//	//升级用户
//	public String promotion(){
//		return "promotion";
//	}
//	
	
	@RequestMapping("/cjlhome/loginview")
	@NeedNavigation
	public String loginview(ModelMap map){
		return "shop/login";
	}
	
	@RequestMapping("/ajaxMemberVerify")
	@ResponseBody
	  public ModelMap ajaxMemberVerify(ModelMap map)
	  {
	    Members localMember = getLoginMember();
	    if (localMember != null)
	      {
			map.put(STATUS, "success");
	      }
	    else{
			map.put(STATUS, "error");
	    }
	    return map;
	  }
	
//		
//		// 清空临时购物车Cookie
//		Cookie cartItemCookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, null);
//		cartItemCookie.setPath(request.getContextPath() + "/");
//		cartItemCookie.setMaxAge(0);
//		response.addCookie(cartItemCookie);
//		
//		String redirectionUrl = (String) session.getAttribute(Members.LOGIN_REDIRECTION_URL_SESSION_NAME);
//		session.removeAttribute(Members.LOGIN_REDIRECTION_URL_SESSION_NAME);
//		if (StringUtils.isEmpty(redirectionUrl)) {
//			map.put("redirectUrl", "/index");
//			map.put("loginMember", loginMember);
//			return SUCCESS;
//		} else {
//			map.put("redirectUrl", redirectionUrl);
//			map.put("loginMember", loginMember);
////			map.put("status", SUCCESS);
//			return SUCCESS;
//		}
//	}
	
	// Ajax会员登录验证
//	@RequestMapping(value="/ajaxLogin", method = RequestMethod.POST)
//	public String ajaxLogin(HttpServletRequest request,HttpServletResponse response,Members member,ModelMap map) {
//		HttpSession session = request.getSession();
////		String captchaID = request.getSession().getId();
////		String challengeResponse = StringUtils.upperCase(request.getParameter(JCaptchaEngine.CAPTCHA_INPUT_NAME));
////		if (StringUtils.isEmpty(challengeResponse) || captchaService.validateResponseForID(captchaID, challengeResponse) == false) {
////			return ajaxJsonErrorMessage("验证码输入错误！");
////		}
////		SystemConfig systemConfig = getSystemConfig();
//		Members loginMember = memberService.getMemberByUsername(member.getUsername());
////		if (loginMember != null) {
////			// 解除会员账户锁定
////			if (loginMember.getIsAccountLocked()) {
////				if (systemConfig.getIsLoginFailureLock()) {
////					int loginFailureLockTime = systemConfig.getLoginFailureLockTime();
////					if (loginFailureLockTime != 0) {
////						Date lockedDate = loginMember.getLockedDate();
////						Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
////						Date now = new Date();
////						if (now.after(nonLockedTime)) {
////							loginMember.setLoginFailureCount(0);
////							loginMember.setIsAccountLocked(false);
////							loginMember.setLockedDate(null);
////							memberService.update(loginMember);
////						}
////					}
////				} else {
////					loginMember.setLoginFailureCount(0);
////					loginMember.setIsAccountLocked(false);
////					loginMember.setLockedDate(null);
////					memberService.update(loginMember);
////				}
////			}
////			if (!loginMember.getIsAccountEnabled()) {
////				return ajaxJsonErrorMessage("您的账号已被禁用,无法登录！");
////			}
////			if (loginMember.getIsAccountLocked()) {
////				return ajaxJsonErrorMessage("您的账号已被锁定,无法登录！");
////			}
////			if (!memberService.verifyMember(member.getUsername(), member.getPassword())) {
////				if (systemConfig.getIsLoginFailureLock()) {
////					int loginFailureLockCount = getSystemConfig().getLoginFailureLockCount();
////					int loginFailureCount = loginMember.getLoginFailureCount() + 1;
////					if (loginFailureCount >= systemConfig.getLoginFailureLockCount()) {
////						loginMember.setIsAccountLocked(true);
////						loginMember.setLockedDate(new Date());
////					}
////					loginMember.setLoginFailureCount(loginFailureCount);
////					memberService.update(loginMember);
////					if (loginFailureLockCount - loginFailureCount <= 3) {
////						return ajaxJsonErrorMessage("若连续" + loginFailureLockCount + "次密码输入错误,您的账号将被锁定！");
////					} else {
////						return ajaxJsonErrorMessage("您的用户名或密码错误！");
////					}
////				} else {
////					return ajaxJsonErrorMessage("您的用户名或密码错误！");
////				}
////			}
////		} else {
////			return ajaxJsonErrorMessage("您的用户名或密码错误！");
////		}
////		loginMember.setLoginIp(request.getRemoteAddr());
////		loginMember.setLoginDate(new Date());
////		memberService.update(loginMember);
////		
//		// 写入会员登录Session
//		session.setAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME, loginMember.getId());
//		
//		// 写入会员登录Cookie
//		Cookie loginMemberUsernameCookie = null;
//		try {
//			loginMemberUsernameCookie = new Cookie(Members.LOGIN_MEMBER_USERNAME_COOKIE_NAME, URLEncoder.encode(member.getUsername().toLowerCase(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		loginMemberUsernameCookie.setPath(request.getContextPath() + "/");
//		response.addCookie(loginMemberUsernameCookie);
////
////		// 合并购物车
////		Cookie[] cookies = request.getCookies();
////		if (cookies != null && cookies.length > 0) {
////			for (Cookie cookie : cookies) {
////				if (StringUtils.equalsIgnoreCase(cookie.getName(), CartItemCookie.CART_ITEM_LIST_COOKIE_NAME)) {
////					if (StringUtils.isNotEmpty(cookie.getValue())) {
////						JsonConfig jsonConfig = new JsonConfig();
////						jsonConfig.setRootClass(CartItemCookie.class);
////						JSONArray jsonArray = JSONArray.fromObject(cookie.getValue());
////						List<CartItemCookie> cartItemCookieList = (List<CartItemCookie>) JSONSerializer.toJava(jsonArray, jsonConfig);
////						for (CartItemCookie cartItemCookie : cartItemCookieList) {
////							Product product = productService.load(cartItemCookie.getI());
////							if (product != null) {
////								CartItem cartItem = new CartItem();
////								cartItem.setMember(loginMember);
////								cartItem.setProduct(product);
////								cartItem.setQuantity(cartItemCookie.getQ());
////								cartItemService.save(cartItem);
////							}
////						}
////					}
////				}
////			}
////		}
////		
////		// 清空临时购物车Cookie
////		Cookie cartItemCookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, null);
////		cartItemCookie.setPath(request.getContextPath() + "/");
////		cartItemCookie.setMaxAge(0);
////		response.addCookie(cartItemCookie);
////		
////		return ajaxJsonSuccessMessage("会员登录成功！");
//		map.put("redirectUrl", "/cjlhome/index");
//		map.put("loginMember", loginMember);
//		return SUCCESS;
//	}
//	
//	// 会员注销
//	public String logout(HttpServletRequest request,HttpServletResponse response) {
////		request.getSession().removeAttribute(Member.LOGIN_MEMBER_ID_SESSION_NAME);
////		Cookie cookie = new Cookie(Member.LOGIN_MEMBER_USERNAME_COOKIE_NAME, null);
////		cookie.setPath(request.getContextPath() + "/");
////		cookie.setMaxAge(0);
////		response.addCookie(cookie);
//		return "index";
//	}
//	
//	// 获取注册协议内容
//	public String getAgreement() {
//		return ajaxText(agreementService.getAgreement().getContent());
//	}
	
//	// 检查用户名是否存在
//	@RequestMapping(value="/checkUsername", method = RequestMethod.POST)
//	public String checkUsername(Members member) {
//		String username = member.getUsername();
//		if (memberService.isExistByUsername(username)) {
//			return ERROR;
//		} else {
//			return SUCCESS;
//		}
//	}

	// Ajax会员注册保存
	@RequestMapping(value="/ajaxRegister", method = RequestMethod.POST)
	public String ajaxRegister(HttpServletRequest request,HttpServletResponse response,Members  member,Boolean isAgreeAgreement,ModelMap map) {
		Members newmember = new Members();
		String username = member.getUsername();
		if (memberService.isExistByUsername(username)) {
			map.put("errorMessage", "用户名已存在");
			return ERROR;
		} 
		if (isAgreeAgreement == null || isAgreeAgreement == false) {
			map.put("errorMessage", MUSTREGISTER);
			return ERROR;
		}
//		if (!getSystemConfig().getIsRegister()) {
//			map.put("errorMessage", CLOSEREGISTER);
//			return ERROR;
//		}
//		String captchaID = request.getSession().getId();
//		String challengeResponse = StringUtils.upperCase(request.getParameter(JCaptchaEngine.CAPTCHA_INPUT_NAME));
//		if (StringUtils.isEmpty(challengeResponse) || captchaService.validateResponseForID(captchaID, challengeResponse) == false) {
//			return ajaxJsonErrorMessage("验证码输入错误！");
//		}
		newmember.setUsername(member.getUsername().toLowerCase());
		newmember.setPassword(DigestUtils.md5Hex(member.getPassword()));
		newmember.setPasswordRecoverKey("1");
		newmember.setEmail(member.getEmail());
		newmember.setSafeQuestion(null);
		newmember.setSafeAnswer(null);
		newmember.setMemberRank(memberRankService.getDefaultMemberRank());
		newmember.setPoint(0);
		newmember.setDeposit(new BigDecimal("0"));
		newmember.setEnabled(true);
		newmember.setAccountNonLocked(false);
		newmember.setLoginFailureCount(0);
//		newmember.setPasswordRecoverKey(null);
		newmember.setLockedDate(null);
		newmember.setLoginDate(new Date());
		newmember.setRegisterIp(request.getRemoteAddr());
		newmember.setLoginIp(request.getRemoteAddr());
		newmember.setTelephone("1");
		newmember.setMemberAttributeMap(null);
		newmember.setReceiverSet(null);
		newmember.setFavoriteProductSet(null);
		memberService.save(newmember);
		
		// 写入会员登录Session
		request.getSession().setAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME, newmember.getId());
		
		// 写入会员登录Cookie
		Cookie loginMemberCookie = null;
		try {
			loginMemberCookie = new Cookie(Members.LOGIN_MEMBER_USERNAME_COOKIE_NAME, URLEncoder.encode(newmember.getUsername().toLowerCase(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		loginMemberCookie.setPath(request.getContextPath() + "/");
		response.addCookie(loginMemberCookie);
		
		// 合并购物车
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equalsIgnoreCase(cookie.getName(), CartItemCookie.CART_ITEM_LIST_COOKIE_NAME)) {
					if (StringUtils.isNotEmpty(cookie.getValue())) {
						JsonConfig jsonConfig = new JsonConfig();
						jsonConfig.setRootClass(CartItemCookie.class);
						JSONArray jsonArray = JSONArray.fromObject(cookie.getValue());
						List<CartItemCookie> cartItemCookieList = (List<CartItemCookie>) JSONSerializer.toJava(jsonArray, jsonConfig);
						for (CartItemCookie cartItemCookie : cartItemCookieList) {
							Product product = productService.load(cartItemCookie.getI());
							if (product != null) {
								CartItem cartItem = new CartItem();
								cartItem.setMember(newmember);
								cartItem.setProduct(product);
								cartItem.setQuantity(cartItemCookie.getQ());
								cartItemService.save(cartItem);
							}
						}
					}
				}
			}
		}
		
		// 清空临时购物车Cookie
		Cookie cartItemCookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, null);
		cartItemCookie.setPath(request.getContextPath() + "/");
		cartItemCookie.setMaxAge(0);
		response.addCookie(cartItemCookie);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	
//	// 密码找回
//	public String passwordRecover() throws Exception {
//		return "password_recover";
//	}
//	
//	// 发送密码找回邮件
//	public String sendPasswordRecoverMail() throws Exception {
////		Member persistent = memberService.getMemberByUsername(member.getUsername());
////		if (persistent == null || StringUtils.equalsIgnoreCase(persistent.getEmail(), member.getEmail()) == false) {
////			return ajaxJsonErrorMessage("用户名或E-mail输入错误！");
////		}
////		if (StringUtils.isNotEmpty(persistent.getSafeQuestion()) && StringUtils.isNotEmpty(persistent.getSafeAnswer())) {
////			if (StringUtils.isEmpty(member.getSafeAnswer())) {
////				Map<String, String> jsonMap = new HashMap<String, String>();
////				jsonMap.put("safeQuestion", persistent.getSafeQuestion());
////				jsonMap.put(STATUS, WARN);
////				jsonMap.put(MESSAGE, "请填写密码保护问题答案！");
////				return ajaxJson(jsonMap);
////			}
////			if (StringUtils.equalsIgnoreCase(persistent.getSafeAnswer(), member.getSafeAnswer()) == false) {
////				return ajaxJsonErrorMessage("密码保护答案错误！");
////			}
////		}
////		if (!mailService.isMailConfigComplete()) {
////			return ajaxJsonErrorMessage("系统邮件发送功能尚未配置，请联系管理员！");
////		}
////		persistent.setPasswordRecoverKey(memberService.buildPasswordRecoverKey());
////		memberService.update(persistent);
////		mailService.sendPasswordRecoverMail(persistent);
////		return ajaxJsonSuccessMessage("系统已发送邮件到您的E-mail，请根据邮件提示操作！");
//	}
//	
//	// 密码修改
//	@Validations(
//		requiredStrings = {
//			@RequiredStringValidator(fieldName = "id", message = "ID不允许为空!"),
//			@RequiredStringValidator(fieldName = "passwordRecoverKey", message = "passwordRecoverKey不允许为空!") 
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String passwordModify() throws Exception {
//		Member persistent = memberService.get(id);
//		if (persistent == null || StringUtils.equalsIgnoreCase(persistent.getPasswordRecoverKey(), passwordRecoverKey) == false) {
//			addActionError("对不起，此密码找回链接已失效！");
//			return ERROR;
//		}
//		Date passwordRecoverKeyBuildDate = memberService.getPasswordRecoverKeyBuildDate(passwordRecoverKey);
//		Date passwordRecoverKeyExpiredDate = DateUtils.addMinutes(passwordRecoverKeyBuildDate, Member.PASSWORD_RECOVER_KEY_PERIOD);
//		Date now = new Date();
//		if (now.after(passwordRecoverKeyExpiredDate)) {
//			addActionError("对不起，此密码找回链接已过期！");
//			return ERROR;
//		}
//		return "password_modify";
//	}
//	
//	// 密码更新
//	@Validations(
//		requiredStrings = {
//			@RequiredStringValidator(fieldName = "id", message = "ID不允许为空!"),
//			@RequiredStringValidator(fieldName = "passwordRecoverKey", message = "passwordRecoverKey不允许为空!"),
//			@RequiredStringValidator(fieldName = "member.password", message = "密码不允许为空!")
//		},
//		stringLengthFields = {
//			@StringLengthFieldValidator(fieldName = "member.password", minLength = "4", maxLength = "20", message = "密码长度必须在${minLength}到${maxLength}之间!")
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String passwordUpdate() throws Exception {
//		Member persistent = memberService.get(id);
//		if (persistent == null || StringUtils.equalsIgnoreCase(persistent.getPasswordRecoverKey(), passwordRecoverKey) == false) {
//			addActionError("对不起，此密码找回链接已失效！");
//			return ERROR;
//		}
//		Date passwordRecoverKeyBuildDate = memberService.getPasswordRecoverKeyBuildDate(passwordRecoverKey);
//		Date passwordRecoverKeyExpiredDate = DateUtils.addMinutes(passwordRecoverKeyBuildDate, Member.PASSWORD_RECOVER_KEY_PERIOD);
//		Date now = new Date();
//		if (now.after(passwordRecoverKeyExpiredDate)) {
//			addActionError("对不起，此密码找回链接已过期！");
//			return ERROR;
//		}
//		persistent.setPassword(DigestUtils.md5Hex(member.getPassword()));
//		persistent.setPasswordRecoverKey(null);
//		memberService.update(persistent);
//		redirectionUrl = request.getContextPath() + "/";
//		addActionMessage("密码修改成功！");
//		return SUCCESS;
//	}
//
//	public Member getMember() {
//		return member;
//	}
//
//	public void setMember(Member member) {
//		this.member = member;
//	}
//
//	public Boolean getIsAgreeAgreement() {
//		return isAgreeAgreement;
//	}
//
//	public void setIsAgreeAgreement(Boolean isAgreeAgreement) {
//		this.isAgreeAgreement = isAgreeAgreement;
//	}
//
//	public String getPasswordRecoverKey() {
//		return passwordRecoverKey;
//	}
//
//	public void setPasswordRecoverKey(String passwordRecoverKey) {
//		this.passwordRecoverKey = passwordRecoverKey;
//	}
//
}
