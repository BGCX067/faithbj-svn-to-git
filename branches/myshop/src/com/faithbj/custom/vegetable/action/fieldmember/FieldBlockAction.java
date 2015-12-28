package com.faithbj.custom.vegetable.action.fieldmember;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.service.FieldBlockService;
import com.faithbj.shop.action.shop.BaseShopAction;

/**
 * 前台Action类 - 可租赁土地块
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	barney
 * @date 	2012-01-28
 * @version 1.0
 */

@ParentPackage("member")
public class FieldBlockAction extends BaseShopAction
{
	private static final long serialVersionUID = 1L;
	
	private String viewType = null;
	
	private FieldBlock fieldBlock = null;

	@Resource
	private FieldBlockService fieldBlockService;
	
	public String list() {
		pager = this.fieldBlockService.findByPager(pager);
		return LIST;
	}

	public String content()
	{
		this.fieldBlock = this.fieldBlockService.load(this.id);
		return "content";
	}

	public String getViewType()
	{
		return viewType;
	}
	public void setViewType(String viewType)
	{
		this.viewType = viewType;
	}

	public FieldBlock getFieldBlock()
	{
		return fieldBlock;
	}

	public void setFieldBlock(FieldBlock fieldBlock)
	{
		this.fieldBlock = fieldBlock;
	}
}
