package com.faithbj.custom.vegetable.action.fieldmember;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.entity.RendBlock;
import com.faithbj.custom.vegetable.service.RendBlockService;
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
public class RendBlockAction extends BaseShopAction
{
	private static final long serialVersionUID = -8878340231045616486L;

	private String viewType = null;
	
	private RendBlock rendBlock = null;

	@Resource
	private RendBlockService rendBlockService;
	
	public String list() {
		pager = this.rendBlockService.findByPager(pager);
		return LIST;
	}
	
	public String content()
	{
		this.rendBlock = this.rendBlockService.load(this.id);
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
	public RendBlock getRendBlock()
	{
		return rendBlock;
	}
	public void setRendBlock(RendBlock rendBlock)
	{
		this.rendBlock = rendBlock;
	}
}
