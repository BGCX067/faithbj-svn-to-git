package com.faithbj.shop.web.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.model.entity.MemberAttribute;
import com.faithbj.shop.service.MemberAttributeService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * 后台Action类 - 会员属性
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class AdminMemberAttributeController extends BaseAdminController {

	private static final long serialVersionUID = -6763618277991640777L;

	private MemberAttribute memberAttribute;
	private List<String> attributeOptionList;

	@Resource
	private MemberAttributeService memberAttributeService;

//	// 是否已存在 ajax验证
//	public String checkName() {
//		String oldValue = getParameter("oldValue");
//		String newValue = memberAttribute.getName();
//		if (memberAttributeService.isUnique("name", oldValue, newValue)) {
//			return ajaxText("true");
//		} else {
//			return ajaxText("false");
//		}
//	}
//
//	// 添加
//	public String add() {
//		return INPUT;
//	}
//
//	// 编辑
//	public String edit() {
//		memberAttribute = memberAttributeService.load(id);
//		return INPUT;
//	}
//
//	// 列表
//	public String list() {
//		pager = memberAttributeService.findByPager(pager);
//		return LIST;
//	}
//
//	// 删除
//	public String delete() {
//		memberAttributeService.delete(ids);
//		return ajaxJsonSuccessMessage("删除成功！");
//	}
//
//	// 保存
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "memberAttribute.name", message = "注册项名称不允许为空!")
//		}, 
//		requiredFields = { 
//			@RequiredFieldValidator(fieldName = "memberAttribute.attributeType", message = "自定义注册项类型不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.isRequired", message = "是否必填不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.isEnabled", message = "是否启用不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "memberAttribute.orderList", min = "0", message = "排序必须为零或正整数!")
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String save() {
//		if (memberAttribute.getAttributeType() == AttributeType.select || memberAttribute.getAttributeType() == AttributeType.checkbox) {
//			if(attributeOptionList == null || attributeOptionList.size() < 1) {
//				addActionError("请至少填写一个选项内容!");
//				return ERROR;
//			}
//			Iterator<String> iterator = attributeOptionList.iterator(); 
//			while (iterator.hasNext()) {
//				String attributeOption = (String) iterator.next();
//				if (StringUtils.isEmpty(attributeOption)) {
//					iterator.remove();
//				}
//			}
//			memberAttribute.setAttributeOptionList(attributeOptionList);
//		} else {
//			memberAttribute.setAttributeOptionList(null);
//		}
//		memberAttributeService.save(memberAttribute);
//		redirectionUrl = "member_attribute!list.action";
//		return SUCCESS;
//	}
//
//	// 更新
//	@Validations(
//		requiredStrings = {
//			@RequiredStringValidator(fieldName = "memberAttribute.name", message = "注册项名称不允许为空!")
//		}, 
//		requiredFields = {
//			@RequiredFieldValidator(fieldName = "memberAttribute.attributeType", message = "自定义注册项类型不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.isRequired", message = "是否必填不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.isEnabled", message = "是否启用不允许为空!"),
//			@RequiredFieldValidator(fieldName = "memberAttribute.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "memberAttribute.orderList", min = "0", message = "排序必须为零或正整数!")
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String update() {
//		if (memberAttribute.getAttributeType() == AttributeType.select || memberAttribute.getAttributeType() == AttributeType.checkbox) {
//			if(attributeOptionList == null || attributeOptionList.size() < 1) {
//				addActionError("请至少填写一个选项内容!");
//				return ERROR;
//			}
//			Iterator<String> iterator = attributeOptionList.iterator(); 
//			while (iterator.hasNext()) {
//				String attributeOption = (String) iterator.next();
//				if (StringUtils.isEmpty(attributeOption)) {
//					iterator.remove();
//				}
//			}
//			memberAttribute.setAttributeOptionList(attributeOptionList);
//		} else {
//			memberAttribute.setAttributeOptionList(null);
//		}
//		MemberAttribute persistent = memberAttributeService.load(id);
//		BeanUtils.copyProperties(memberAttribute, persistent, new String[] {"id", "createDate", "modifyDate"});
//		memberAttributeService.update(persistent);
//		redirectionUrl = "member_attribute!list.action";
//		return SUCCESS;
//	}
//	
//	// 获取所有自定义注册项属性类型
//	public List<AttributeType> getAllAttributeType() {
//		List<AttributeType> allAttributeType = new ArrayList<AttributeType>();
//		for (AttributeType attributeType : AttributeType.values()) {
//			allAttributeType.add(attributeType);
//		}
//		return allAttributeType;
//	}

	public MemberAttribute getMemberAttribute() {
		return memberAttribute;
	}

	public void setMemberAttribute(MemberAttribute memberAttribute) {
		this.memberAttribute = memberAttribute;
	}

	public List<String> getAttributeOptionList() {
		return attributeOptionList;
	}

	public void setAttributeOptionList(List<String> attributeOptionList) {
		this.attributeOptionList = attributeOptionList;
	}

}
