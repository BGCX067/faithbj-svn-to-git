package com.faithbj.shop.web.controller.shop;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.faithbj.shop.model.entity.Area;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Receiver;
import com.faithbj.shop.service.AreaService;
import com.faithbj.shop.service.ReceiverService;
import com.faithbj.shop.support.annotation.NeedNavigation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 前台Action类 - 收货地址
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/receiver")
public class ReceiverController extends BaseShopController {

	private static final long serialVersionUID = 5947142304957196520L;
	
	private final String OPERRATE_RETURN_URL="/cjlhome/receiver/list";
	@Resource
	private AreaService areaService;
	@Resource
	private ReceiverService receiverService;

	// 收货地址添加
	@RequestMapping("/new")
	@NeedNavigation
	public String add(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		Set<Receiver> receiverSet = loginMember.getReceiverSet();
		
		if (receiverSet != null && Receiver.MAX_RECEIVER_COUNT != null && receiverSet.size() >= Receiver.MAX_RECEIVER_COUNT) {
			map.put("errorMessage","只允许添加最多" + Receiver.MAX_RECEIVER_COUNT + "项收货地址!");
			return ERROR;
		}
		map.put("isAddAction", true);
		
		map.put("receiverSet", receiverSet);
		return "shop/receiver_input";
	}
	
	// 收货地址编辑
	@RequestMapping("/{id}/edit")
	@NeedNavigation
	public String edit(ModelMap map,HttpServletRequest request,@PathVariable String id) {
		addSystemConfig(map);
		Receiver receiver = receiverService.load(id);
		Members loginMember = getLoginMember(request);
		if(receiver.getMember() != loginMember) {
			map.put("errorMessage","参数错误!");
			return ERROR;
		}
		map.put("loginMember", loginMember);
		map.put("receiver", receiver);
		return "shop/receiver_input";
	}
	
	// 收货地址删除
	@RequestMapping("/delete/{id}")
	public String delete(ModelMap map,@PathVariable String id,HttpServletRequest request) {
		Receiver receiver = receiverService.load(id);
		Members loginMember = getLoginMember(request);
		if(receiver.getMember() != loginMember) {
			map.put("errorMessage","参数错误!");
			return ERROR;
		}
		receiverService.delete(receiver);
		map.put("loginMember", loginMember);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	
	// 收货地址列表
	@RequestMapping("/list")
	@NeedNavigation
	public String list(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		Set<Receiver> receiverSet = loginMember.getReceiverSet();
		map.put("receiverSet", receiverSet);
		return "shop/receiver_list";
	}
	
	// 收货地址保存
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(ModelMap map,HttpServletRequest request,Receiver receiver) {
		if (StringUtils.isEmpty(receiver.getPhone()) && StringUtils.isEmpty(receiver.getMobile())) {
			map.put("errorMessage","联系电话、联系手机必须填写其中一项!");
			return ERROR;
		}
		if (!areaService.isAreaPath(receiver.getAreaPath())) {
			map.put("errorMessage","地区错误!");
			return ERROR;
		}
		 Area localArea = (Area)areaService.get(receiver.getAreaPath().substring(receiver.getAreaPath().lastIndexOf(",")+1));
		 if (localArea == null)
		    {
			 	map.put("errorMessage","请选择收货地址!");
				return ERROR;
		    }
		 receiver.setArea(localArea);
		Members loginMember = getLoginMember(request);
		Set<Receiver> receiverSet = loginMember.getReceiverSet();
		if (receiverSet != null && Receiver.MAX_RECEIVER_COUNT != null && receiverSet.size() >= Receiver.MAX_RECEIVER_COUNT) {
			map.put("errorMessage","只允许添加最多" + Receiver.MAX_RECEIVER_COUNT + "项收货地址!");
			return ERROR;
		}
		receiver.setMember(loginMember);
		receiverService.save(receiver);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	
	// 收货地址更新
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ModelMap map,HttpServletRequest request,Receiver receiver,@RequestParam("id") String id) {
		if (StringUtils.isEmpty(receiver.getMobile()) && StringUtils.isEmpty(receiver.getPhone())) {
			map.put("errorMessage","联系手机、联系电话必须填写其中一项!");
			return ERROR;
		}
		if (!areaService.isAreaPath(receiver.getAreaPath())) {
			map.put("errorMessage","地区错误!");
			return ERROR;
		}
		Receiver persistent = receiverService.load(id);
		if(persistent.getMember() != getLoginMember(request)) {
			map.put("errorMessage","参数错误!");
			return ERROR;
		}
		BeanUtils.copyProperties(receiver, persistent, new String[] {"id", "createDate", "modifyDate", "member"});
		receiverService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

}
