package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Message;
import com.faithbj.shop.model.entity.Message.DeleteStatus;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.MessageService;
import com.faithbj.shop.support.annotation.NeedNavigation;

/**
 * 前台Action类 - 消息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/message")
public class MessageController extends BaseShopController implements Serializable{

	private static final long serialVersionUID = 3248218706961305882L;

	private Message message;
	private String toMemberUsername;

	@Resource
	private MessageService messageService;
	@Resource
	private MemberService memberService;
	

	// 发送消息
	@RequestMapping("send")
	@NeedNavigation
	public String send(ModelMap map,HttpServletRequest request,String id) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		if (StringUtils.isNotEmpty(id)) {
			message = messageService.load(id);
			if (message.getIsSaveDraftbox() == false || message.getFromMember() != loginMember) {
				map.put("errorMessage", "参数错误!");
				return ERROR;
			}
		}
		return "shop/message_send";
	}
	
	// 回复
	@RequestMapping("reply")
	@NeedNavigation
	public String reply(ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		message = messageService.load(id);
		if (message.getToMember() != loginMember) {
			map.put("errorMessage", "参数错误!");
			return ERROR;
		}
		return "shop/message_reply";
	}
	
	// 保存消息
	public String save(ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		if (StringUtils.isNotEmpty(toMemberUsername)) {
			Members toMember = memberService.getMemberByUsername(toMemberUsername);
			if (toMember == null) {
				map.put("errorMessage", "收件人不存在!");
				return ERROR;
			}
			if (toMember == loginMember) {
				map.put("errorMessage", "收件人不允许为自己!");
				return ERROR;
			}
			message.setToMember(toMember);
		} else {
			message.setToMember(null);
		}
		message.setFromMember(loginMember);
		message.setDeleteStatus(DeleteStatus.nonDelete);
		message.setIsRead(false);
		
		if (StringUtils.isNotEmpty(id)) {
			Message persistent = messageService.load(id);
			if (persistent.getIsSaveDraftbox() == false || persistent.getFromMember() != loginMember) {
				map.put("errorMessage", "参数错误!");
				return ERROR;
			}
			BeanUtils.copyProperties(message, persistent, new String[] {"id", "createDate", "modifyDate"});
			messageService.update(persistent);
		} else {
			messageService.save(message);
		}
		if (message.getIsSaveDraftbox()) {
			map.put(redirectUrl, "draftbox");
		} else {
			map.put(redirectUrl, "outbox");
		}
		return SUCCESS;
	}
	
	// 收件箱
	@RequestMapping("inbox")
	@NeedNavigation
	public String inbox(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		pager = messageService.getMemberInboxPager(loginMember, pager);
		map.put("pager", pager);
		return "shop/message_inbox";
	}

	// 发件箱
	@RequestMapping("outbox")
	@NeedNavigation
	public String outbox(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		pager = messageService.getMemberOutboxPager(loginMember, pager);
		map.put("pager", pager);
		return "shop/message_outbox";
	}

	// 草稿箱
	@RequestMapping("draftbox")
	@NeedNavigation
	public String draftbox(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		map.put("loginMember", loginMember);
		pager = messageService.getMemberDraftboxPager(loginMember, pager);
		map.put("pager", pager);
		return "shop/message_draftbox";
	}

	// 删除
	@RequestMapping(value="delete/{id}" ,method =RequestMethod.POST)
	public String delete(ModelMap map,@PathVariable String id,HttpServletRequest request) {
		Message message = messageService.load(id);
		Members loginMember = getLoginMember(request);
		if (message.getIsSaveDraftbox()) {
			if (message.getFromMember() == loginMember) {
				messageService.delete(message);
				map.put(redirectUrl, "draftbox");
			}
		} else {
			if (message.getToMember() != null && message.getToMember() == loginMember) {
				if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
					message.setDeleteStatus(DeleteStatus.toDelete);
					messageService.update(message);
				} else if (message.getDeleteStatus() == DeleteStatus.fromDelete) {
					messageService.delete(message);
				}
				map.put(redirectUrl, "inbox");
			} else if (message.getFromMember() != null && message.getFromMember() == loginMember) {
				if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
					message.setDeleteStatus(DeleteStatus.fromDelete);
					messageService.update(message);
				} else if (message.getDeleteStatus() == DeleteStatus.toDelete) {
					messageService.delete(message);
				}
				map.put(redirectUrl, "outbox");
			}
		}
		return SUCCESS;
	}

	// AJAX获取消息内容
//	public String ajaxMessageContent(ModelMap map) {
//		Message message = messageService.load(id);
//		if (message.getToMember() != getLoginMember()) {
//			map.put("errorMessage", "参数错误!");
//			return ERROR;
//		}
//		if (!message.getIsRead()) {
//			message.setIsRead(true);
//			messageService.update(message);
//		}
//		Map<String, String> jsonMap = new HashMap<String, String>();
//		jsonMap.put(STATUS, SUCCESS);
//		jsonMap.put(CONTENT, message.getContent());
//		return ajaxJson(jsonMap);
//	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getToMemberUsername() {
		return toMemberUsername;
	}

	public void setToMemberUsername(String toMemberUsername) {
		this.toMemberUsername = toMemberUsername;
	}

}
