package com.faithbj.custom.vegetable.action.vegetableadmin;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.action.admin.MenuAction;

/**
 * 
 * <p>蔬菜菜单Action</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-04
 * @version 1.0
 */

@ParentPackage("admin")
public class VegetableMenuAction extends MenuAction
{
	private static final long serialVersionUID = -6663483813187398983L;

	// 默认种子管理
	public String seed() {
		return "seed";
	}

	// 土地管理
	public String field() {
		return "field";
	}
	
	// 配送管理
	public String deliver() {
		return "deliver";
	}
	
}
