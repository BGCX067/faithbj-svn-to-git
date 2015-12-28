package com.faithbj.shop.web.controller.admin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.faithbj.shop.model.entity.Deposit;
import com.faithbj.shop.model.entity.Deposit.DepositType;
import com.faithbj.shop.model.entity.MemberAttribute;
import com.faithbj.shop.model.entity.MemberAttribute.AttributeType;
import com.faithbj.shop.model.entity.MemberRank;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.AreaService;
import com.faithbj.shop.service.DepositService;
import com.faithbj.shop.service.MemberAttributeService;
import com.faithbj.shop.service.MemberRankService;
import com.faithbj.shop.service.MemberService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台Action类 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/faith/member")
public class AdminMemberController extends BaseAdminController {

	private static final long serialVersionUID = -5383463207248344967L;

	private Members member;

	@Resource
	private MemberService memberService;
	@Resource
	private MemberRankService memberRankService;
	@Resource
	private MemberAttributeService memberAttributeService;
	@Resource
	private AreaService areaService;
	@Resource
	private DepositService depositService;
	
	private final String LIST="admin/member_list";
	private final String INPUT="admin/member_input";
	private final String HAS_DEPOSIT="此会员预付款余额不为零，删除失败!";
	private final String DEPOSITERROR="预付款不允许小于零!";
	private final String OPERRATE_RETURN_URL="faith/member/list";
	
	// 会员menu
	@RequestMapping("menu")
		public String member() {
			return "admin/menu_member";
		}
//	// 查看
//	public String view() {
//		member = memberService.load(id);
//		return VIEW;
//	}

	// 列表
	@RequestMapping("list")
	public String list(ModelMap map) {
		pager = memberService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}

	// 删除
	@RequestMapping("delete")
	public String delete(ModelMap map,@RequestParam String id) {
		Members member = memberService.load(id);
		if (member.getDeposit().compareTo(new BigDecimal("0")) > 0) {
			map.put("errorMessage", HAS_DEPOSIT);
			return ERROR;
		}
			
		memberService.delete(id);
		return SUCCESS;
	}

	// 添加
	@RequestMapping("new")
	public String add(ModelMap map) {
		List<MemberRank> memberRankList = getAllMemberRank();
		List<MemberAttribute> memberAttributeList = getEnabledMemberAttributeList();
		map.put("memberAttributeList", memberAttributeList);
		map.put("allMemberRankList", memberRankList);
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("edit")
	public String edit(ModelMap map,@RequestParam String id) {
		member = memberService.load(id);
		MemberRank memberRank = member.getMemberRank();
		List<MemberRank> memberRankList = getAllMemberRank();
		map.put("memberRank", memberRank);
		map.put("allMemberRankList", memberRankList);
		map.put("isAddAction", false);
		map.put("member", member);
		return INPUT;
	}

	// 保存
	@RequestMapping("save")
	public String save(ModelMap map,Members member,@RequestParam Map<MemberAttribute, List<String>> memberAttributeMap,HttpServletRequest request) {
		if (member.getDeposit().compareTo(new BigDecimal("0")) < 0) {
			map.put("errorMessage", DEPOSITERROR);
			return ERROR;
		}
//		Map<MemberAttribute, List<String>> memberAttributeMap = new HashMap<MemberAttribute, List<String>>();
		List<MemberAttribute> enabledMemberAttributeList = memberAttributeService.getEnabledMemberAttributeList();
		for (MemberAttribute memberAttribute : enabledMemberAttributeList) {
			String[] parameterValues = request.getParameterValues(memberAttribute.getId());
			if (memberAttribute.getIsRequired() && (parameterValues == null || parameterValues.length == 0 || StringUtils.isEmpty(parameterValues[0]))) {
//				addActionError(memberAttribute.getName() + "不允许为空!");
				return ERROR;
			}
			if (parameterValues != null && parameterValues.length > 0 && StringUtils.isNotEmpty(parameterValues[0])) {
				if (memberAttribute.getAttributeType() == AttributeType.number) {
					Pattern pattern = Pattern.compile("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "只允许输入数字!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.alphaint) {
					Pattern pattern = Pattern.compile("[a-zA-Z]+");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "只允许输入字母!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.email) {
					Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "E-mail格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.date) {
					Pattern pattern = Pattern.compile("\\d{4}[\\/-]\\d{1,2}[\\/-]\\d{1,2}");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "日期格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.area) {
					if (!areaService.isAreaPath(parameterValues[0])) {
//						addActionError(memberAttribute.getName() + "地区错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.select || memberAttribute.getAttributeType() == AttributeType.checkbox) {
					List<String> attributeOptionList = memberAttribute.getAttributeOptionList();
					for (String parameterValue : parameterValues) {
						if (!attributeOptionList.contains(parameterValue)) {
//							addActionError("参数错误!");
							return ERROR;
						}
					}
				}
				memberAttributeMap.put(memberAttribute, Arrays.asList(parameterValues));
			}
		}
		member.setUsername(member.getUsername().toLowerCase());
		member.setPassword(DigestUtils.md5Hex(member.getPassword()));
		member.setSafeQuestion(null);
		member.setSafeAnswer(null);
		member.setAccountNonLocked(false);
		member.setLoginFailureCount(0);
		member.setPasswordRecoverKey("1");
		member.setLockedDate(null);
		member.setLoginDate(new Date());
		member.setRegisterIp(request.getRemoteAddr());
		member.setLoginIp(request.getRemoteHost());
		member.setMemberAttributeMap(null);
		member.setReceiverSet(null);
		member.setTelephone("1");
		member.setFavoriteProductSet(null);
		memberService.save(member);
		
		// 预存款记录
		if (member.getDeposit().compareTo(new BigDecimal("0")) > 0) {
			Deposit deposit = new Deposit();
			deposit.setDepositType(DepositType.adminRecharge);
			deposit.setCredit(member.getDeposit());
			deposit.setDebit(new BigDecimal("0"));
			deposit.setBalance(member.getDeposit());
			deposit.setMember(member);
			deposit.setPayment(null);
			depositService.save(deposit);
		}
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(ModelMap map,@RequestParam String id,Members member,@RequestParam Map<MemberAttribute, List<String>> memberAttributeMap,HttpServletRequest request) {
		if (member.getDeposit().compareTo(new BigDecimal("0")) < 0) {
//			addActionError("预存款不允许小于0");
			return ERROR;
		}
//		Map<MemberAttribute, List<String>> memberAttributeMap = new HashMap<MemberAttribute, List<String>>();
		List<MemberAttribute> enabledMemberAttributeList = memberAttributeService.getEnabledMemberAttributeList();
		for (MemberAttribute memberAttribute : enabledMemberAttributeList) {
			String[] parameterValues = request.getParameterValues(memberAttribute.getId());
			if (memberAttribute.getIsRequired() && (parameterValues == null || parameterValues.length == 0 || StringUtils.isEmpty(parameterValues[0]))) {
//				addActionError(memberAttribute.getName() + "不允许为空!");
				return ERROR;
			}
			if (parameterValues != null && parameterValues.length > 0 && StringUtils.isNotEmpty(parameterValues[0])) {
				if (memberAttribute.getAttributeType() == AttributeType.number) {
					Pattern pattern = Pattern.compile("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "只允许输入数字!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.alphaint) {
					Pattern pattern = Pattern.compile("[a-zA-Z]+");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "只允许输入字母!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.email) {
					Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "E-mail格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.date) {
					Pattern pattern = Pattern.compile("\\d{4}[\\/-]\\d{1,2}[\\/-]\\d{1,2}");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
//						addActionError(memberAttribute.getName() + "日期格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.area) {
					if (!areaService.isAreaPath(parameterValues[0])) {
//						addActionError(memberAttribute.getName() + "地区错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.select || memberAttribute.getAttributeType() == AttributeType.checkbox) {
					List<String> attributeOptionList = memberAttribute.getAttributeOptionList();
					for (String parameterValue : parameterValues) {
						if (!attributeOptionList.contains(parameterValue)) {
//							addActionError("参数错误!");
							return ERROR;
						}
					}
				}
				memberAttributeMap.put(memberAttribute, Arrays.asList(parameterValues));
			}
		}
//		member.setMemberAttributeMap(null);
		Members persistent = memberService.load(id);
//		BigDecimal previousDeposit = persistent.getDeposit();
//		BigDecimal currentDeposit = member.getDeposit();
//		if (StringUtils.isEmpty(member.getPassword())) {
//			member.setPassword(persistent.getPassword());
//		} else {
//			member.setPassword(DigestUtils.md5Hex(member.getPassword()));
//		}
//		BeanUtils.copyProperties(member, persistent, new String[] {"id", "createDate", "modifyDate", "username", "safeQuestion", "safeAnswer", "isAccountLocked", "loginFailureCount", "lockedDate", "registerIp", "loginIp", "loginDate", "passwordRecoverKey", "receiverSet", "favoriteProductSet", "orderSet", "inboxMessageSet", "outboxMessageSet", "orderSet", "depositSet" });
		memberService.update(persistent);
		
		// 预存款记录
//		if (currentDeposit.compareTo(previousDeposit) > 0) {
//			Deposit deposit = new Deposit();
//			deposit.setDepositType(DepositType.adminRecharge);
//			deposit.setCredit(currentDeposit.subtract(previousDeposit));
//			deposit.setDebit(new BigDecimal("0"));
//			deposit.setBalance(currentDeposit);
//			deposit.setMember(persistent);
//			deposit.setPayment(null);
//			depositService.save(deposit);
//		} else if (member.getDeposit().compareTo(previousDeposit) < 0) {
//			Deposit deposit = new Deposit();
//			deposit.setDepositType(DepositType.adminChargeback);
//			deposit.setCredit(new BigDecimal("0"));
//			deposit.setDebit(previousDeposit.subtract(currentDeposit));
//			deposit.setBalance(currentDeposit);
//			deposit.setMember(persistent);
//			deposit.setPayment(null);
//			depositService.save(deposit);
//		}
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

	// 获取所有会员等级
	public List<MemberRank> getAllMemberRank() {
		return memberRankService.getAll();
	}

	// 获取已启用的会员注册项
	public List<MemberAttribute> getEnabledMemberAttributeList() {
		return memberAttributeService.getEnabledMemberAttributeList();
	}

}
