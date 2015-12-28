package com.faithbj.custom.vegetable.action.fieldmember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.service.RendBlockService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;

/**
 * 前台Action类 - 我的土地管理
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	barney
 * @date 	2012-01-28
 * @version 1.0
 */

@ParentPackage("member")
public class MyRendBlockAction extends BaseShopAction
{
	private static final long serialVersionUID = 1L;
	
	private String isTrusteeFeeEnabled = null;
	private String isDeliveryFeeEnabled = null;
	
	@Resource
	private RendBlockService rendBlockService;

	private String viewType = null;

	public String list() {
		
		Member currentMember = this.getLoginMember();
		if (currentMember != null)
		{
			if (pager == null)
			{
				pager = new Pager();
			}
			
			List<String> propertyList = new ArrayList<String>(Arrays.asList("username"));
			List<Object> keyworkdList = new ArrayList<Object>(Arrays.<Object>asList(currentMember.getUsername()));
			List<String> compareTypeList = new ArrayList<String>(Arrays.asList("eq"));
			
			if (StringUtils.isNotBlank(this.isDeliveryFeeEnabled))
			{
				propertyList.add("isDeliveryFeeEnabled");
				keyworkdList.add(Boolean.valueOf(isDeliveryFeeEnabled));
				compareTypeList.add("eq");
			}
			if (StringUtils.isNotBlank(this.isTrusteeFeeEnabled))
			{
				propertyList.add("isTrusteeFeeEnabled");
				keyworkdList.add(Boolean.valueOf(isTrusteeFeeEnabled));
				compareTypeList.add("eq");
			}
			
			pager.setPropertyList(propertyList);
			pager.setKeywordList(keyworkdList);
			pager.setCompareTypeList(compareTypeList);
			
			pager = this.rendBlockService.findByPager(pager);
		}
		
		return LIST;
	}

	public String getViewType()
	{
		return viewType;
	}
	public void setViewType(String viewType)
	{
		this.viewType = viewType;
	}

	public String getIsTrusteeFeeEnabled()
	{
		return isTrusteeFeeEnabled;
	}

	public void setIsTrusteeFeeEnabled(String isTrusteeFeeEnabled)
	{
		this.isTrusteeFeeEnabled = isTrusteeFeeEnabled;
	}

	public String getIsDeliveryFeeEnabled()
	{
		return isDeliveryFeeEnabled;
	}

	public void setIsDeliveryFeeEnabled(String isDeliveryFeeEnabled)
	{
		this.isDeliveryFeeEnabled = isDeliveryFeeEnabled;
	}
}
