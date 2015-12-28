package com.faithbj.shop.web.controller.shop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.MemberAttribute;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.MemberAttribute.AttributeType;
import com.faithbj.shop.service.AreaService;
import com.faithbj.shop.service.MemberAttributeService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前台Action类 - 个人信息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/profile")
public class ProfileController extends BaseShopController {

	private static final long serialVersionUID = -7704084885878684413L;
	
	private Members member;
	
	private static final Integer pageSize = 10;// 商品收藏每页显示数
	
	@Resource
	private MemberService memberService;
	@Resource
	private MemberAttributeService memberAttributeService;
	@Resource
	private AreaService areaService;
	@Resource
	private ProductService productService;
	
	// 编辑
	@RequestMapping("edit")
	@NeedNavigation
	public String edit(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		member = getLoginMember(request);
		map.put("loginMember", member);
		return "shop/profile_input";
	}
	
	// 更新
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap map,HttpServletRequest request) {
		Map<MemberAttribute, List<String>> memberAttributeMap = new HashMap<MemberAttribute, List<String>>();
		List<MemberAttribute> enabledMemberAttributeList = memberAttributeService.getEnabledMemberAttributeList();
		for (MemberAttribute memberAttribute : enabledMemberAttributeList) {
			String[] parameterValues = request.getParameterValues(memberAttribute.getId());
			if (memberAttribute.getIsRequired() && (parameterValues == null || parameterValues.length == 0 || StringUtils.isEmpty(parameterValues[0]))) {
				map.put("errorMessage",memberAttribute.getName() + "不允许为空!");
				return ERROR;
			}
			if (parameterValues != null && parameterValues.length > 0 && StringUtils.isNotEmpty(parameterValues[0])) {
				if (memberAttribute.getAttributeType() == AttributeType.number) {
					Pattern pattern = Pattern.compile("^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
						map.put("errorMessage",memberAttribute.getName() + "只允许输入数字!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.alphaint) {
					Pattern pattern = Pattern.compile("[a-zA-Z]+");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
						map.put("errorMessage",memberAttribute.getName() + "只允许输入字母!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.email) {
					Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
						map.put("errorMessage",memberAttribute.getName() + "E-mail格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.date) {
					Pattern pattern = Pattern.compile("\\d{4}[\\/-]\\d{1,2}[\\/-]\\d{1,2}");
					Matcher matcher = pattern.matcher(parameterValues[0]);
					if (!matcher.matches()) {
						map.put("errorMessage",memberAttribute.getName() + "日期格式错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.area) {
					if (!areaService.isAreaPath(parameterValues[0])) {
						map.put("errorMessage",memberAttribute.getName() + "地区错误!");
						return ERROR;
					}
				}
				if (memberAttribute.getAttributeType() == AttributeType.select || memberAttribute.getAttributeType() == AttributeType.checkbox) {
					List<String> attributeOptionList = memberAttribute.getAttributeOptionList();
					for (String parameterValue : parameterValues) {
						if (!attributeOptionList.contains(parameterValue)) {
							map.put("errorMessage","参数错误!");
							return ERROR;
						}
					}
				}
				memberAttributeMap.put(memberAttribute, Arrays.asList(parameterValues));
			}
		}
		member.setMemberAttributeMap(memberAttributeMap);
		Members persistent = getLoginMember(request);
		if (StringUtils.isNotEmpty(member.getPassword())) {
			String passwordMd5 = DigestUtils.md5Hex(member.getPassword());
			persistent.setPassword(passwordMd5);
		}
		BeanUtils.copyProperties(member, persistent, new String[] {"id", "createDate", "modifyDate", "username", "password", "safeQuestion", "safeAnswer", "point", "deposit", "isAccountEnabled", "isAccountLocked", "loginFailureCount", "lockedDate", "registerIp", "loginIp", "loginDate", "passwordRecoverKey", "memberRank", "receiverSet", "favoriteProductSet","favorProductSet","hateProductSet", "cartItemSet", "inboxMessageSet", "outboxMessageSet", "orderSet", "depositSet" });
		memberService.update(persistent);
		map.put(redirectUrl, "edit");
		return SUCCESS;
	}

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}
	
	// 获取已启用的会员注册项
	public List<MemberAttribute> getEnabledMemberAttributeList() {
		return memberAttributeService.getEnabledMemberAttributeList();
	}
	
	// 喜欢商品列表
		@RequestMapping("favorlist")
		@NeedNavigation
		public String FavorList(ModelMap map,HttpServletRequest request) {
			addSystemConfig(map);
			Members loginMember = getLoginMember(request);
			if (pager == null) {
				pager = new Pager();
			}
			pager.setPageSize(pageSize);
			pager = productService.getFavorProductPager(loginMember, pager);
			map.put("loginMember", loginMember);
			map.put("pager", pager);
			return "shop/favor_list";
		}
		
		// 忌讳商品列表
		@RequestMapping("hatelist")
		@NeedNavigation
		public String HateList(ModelMap map,HttpServletRequest request) {
			addSystemConfig(map);
			Members loginMember = getLoginMember(request);
			if (pager == null) {
				pager = new Pager();
			}
			pager.setPageSize(pageSize);
			pager = productService.getHateProductPager(loginMember, pager);
			map.put("loginMember", loginMember);
			map.put("pager", pager);
			return "shop/hate_list";
		}

		// 添加喜好商品
		@RequestMapping(value="addfavor",method=RequestMethod.POST)
		@ResponseBody
		public ModelMap ajaxAddFavor(ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
			Product product = productService.load(id);
			if (!product.getIsMarketable()) {
				map.put(STATUS, "error");
				map.put("message", "此商品已下架!");
				return map;
			}
			Members loginMember = getLoginMember(request);
			Set<Product> favorProductSet = (loginMember.getFavorProductSet() == null ? new HashSet<Product>() : loginMember.getFavorProductSet());
			if (favorProductSet.contains(product)) {
				map.put(STATUS, "error");
				map.put("message", "您已经添加过此商品!");
				return map;
			} else {
				favorProductSet.add(product);
				memberService.update(loginMember);
				map.put(STATUS, "success");
				map.put("message", "喜好商品添加成功!");
				return map;
			}
		}
		
		// 添加忌讳商品
				@RequestMapping(value="addhate",method=RequestMethod.POST)
				@ResponseBody
				public ModelMap ajaxAddHate(ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
					Product product = productService.load(id);
					if (!product.getIsMarketable()) {
						map.put(STATUS, "error");
						map.put("message", "此商品已下架!");
						return map;
					}
					Members loginMember = getLoginMember(request);
					Set<Product> hateProductSet = loginMember.getHateProductSet();
					if (hateProductSet.contains(product)) {
						map.put(STATUS, "error");
						map.put("message", "您已经添加过此商品!");
						return map;
					} else {
						hateProductSet.add(product);
						memberService.update(loginMember);
						map.put(STATUS, "success");
						map.put("message", "忌讳商品添加成功!");
						return map;
					}
				}

		// 删除喜好商品
		@RequestMapping(value="/deletefavor",method=RequestMethod.POST)
		@ResponseBody
		public ModelMap deleteFavor (ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
			Product product = productService.load(id);
			Members loginMember = getLoginMember(request);
			Set<Product> favorProductSet = loginMember.getFavorProductSet();
			if (!favorProductSet.contains(product)) {
				map.put(STATUS, "error");
				map.put("message", "参数错误!");
				return map;
			}
			favorProductSet.remove(product);
			memberService.update(loginMember);
//			map.put(redirectUrl, "shop/favorite_list");
			map.put(STATUS, "success");
			map.put("message", "删除成功!");
			return map;
		}
		
		// 删除忌讳商品
				@RequestMapping(value="/deletehate",method=RequestMethod.POST)
				@ResponseBody
				public ModelMap deleteHate (ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
					Product product = productService.load(id);
					Members loginMember = getLoginMember(request);
					Set<Product> hateProductSet = loginMember.getHateProductSet();
					if (!hateProductSet.contains(product)) {
						map.put(STATUS, "error");
						map.put("message", "参数错误!");
						return map;
					}
					hateProductSet.remove(product);
					memberService.update(loginMember);
//					map.put(redirectUrl, "shop/hateite_list");
					map.put(STATUS, "success");
					map.put("message", "删除成功!");
					return map;
				}
}
