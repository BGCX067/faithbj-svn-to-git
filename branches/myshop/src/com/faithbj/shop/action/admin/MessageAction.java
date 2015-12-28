package com.faithbj.shop.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Message;
import com.faithbj.shop.entity.Message.DeleteStatus;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.MessageService;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 后台Action类 - 消息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("admin")
public class MessageAction extends BaseAdminAction {

	private static final long serialVersionUID = -8841506249589763663L;

	private Message message;
	private String toMemberUsername;

	@Resource
	private MessageService messageService;
	@Resource
	private MemberService memberService;
	
	// 检查用户名是否存在
	public String checkUsername() {
		String value = getParameter("toMemberUsername");
		if (memberService.isExist("username", value)) {
			ajaxText("true");
		} else {
			ajaxText("false");
		}
		return null;
	}
	
	// 发送消息
	public String send() {
		return "send";
	}

	// 回复
	public String reply() {
		message = messageService.load(id);
		if (message.getToMember() != null) {
			addActionError("参数错误!");
			return ERROR;
		}
		return "reply";
	}

	// 收件箱
	public String inbox() {
		pager = messageService.getAdminInboxPager(pager);
		return "inbox";
	}
	
	// 发件箱
	public String outbox() {
		pager = messageService.getAdminOutboxPager(pager);
		return "outbox";
	}

	// 保存
	@Validations(
		requiredStrings = {
			@RequiredStringValidator(fieldName = "toMemberUsername", message = "收件人不允许为空!"),
			@RequiredStringValidator(fieldName = "message.title", message = "标题不允许为空!"),
			@RequiredStringValidator(fieldName = "message.content", message = "消息内容不允许为空!")
		},
		stringLengthFields = {
			@StringLengthFieldValidator(fieldName = "message.content", maxLength = "10000", message = "消息内容长度超出限制！")
		}
	)
	@InputConfig(resultName = "error")
	public String save() {
		Member toMember = memberService.getMemberByUsername(toMemberUsername);
		if (toMember == null) {
			addActionError("收件人不存在!");
			return ERROR;
		}
		message.setToMember(toMember);
		message.setFromMember(null);
		message.setDeleteStatus(DeleteStatus.nonDelete);
		message.setIsRead(false);
		message.setIsSaveDraftbox(false);
		messageService.save(message);
		redirectionUrl = "message!outbox.action";
		return SUCCESS;
	}

	// 删除
	public String delete() {
		for (String id : ids) {
			Message message = messageService.load(id);
			if (!message.getIsSaveDraftbox()) {
				if (message.getToMember() == null) {
					if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
						message.setDeleteStatus(DeleteStatus.toDelete);
						messageService.update(message);
					} else if (message.getDeleteStatus() == DeleteStatus.fromDelete) {
						messageService.delete(message);
					}
				} else if (message.getFromMember() == null) {
					if (message.getDeleteStatus() == DeleteStatus.nonDelete) {
						message.setDeleteStatus(DeleteStatus.fromDelete);
						messageService.update(message);
					} else if (message.getDeleteStatus() == DeleteStatus.toDelete) {
						messageService.delete(message);
					}
				}
			}
		}
		return ajaxJsonSuccessMessage("删除成功！");
	}

	// AJAX获取消息内容
	public String ajaxMessageContent() {
		Message message = messageService.load(id);
		if (message.getToMember() != null) {
			addActionError("参数错误!");
			return ERROR;
		}
		if (!message.getIsRead()) {
			message.setIsRead(true);
			messageService.update(message);
		}
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(CONTENT, message.getContent());
		return ajaxJson(jsonMap);
	}

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
