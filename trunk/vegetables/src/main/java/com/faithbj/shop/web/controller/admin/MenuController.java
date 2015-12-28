package com.faithbj.shop.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 后台Action类 - 菜单
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseAdminController {

	private static final long serialVersionUID = 6465259795235263493L;

	// 商店配置
	@RequestMapping(value="setting",method = RequestMethod.GET)
	public String setting() {
		return "admin/menu_setting";
	}
	
	// 会员
	@RequestMapping(value="member",method = RequestMethod.GET)
	public String member() {
		return "admin/menu_member";
	}
	
	// 商品
	@RequestMapping(value="product",method = RequestMethod.GET)
	public String product() {
		return "admin/menu_product";
	}
	
	// 页面管理
	@RequestMapping(value="content",method = RequestMethod.GET)
	public String content() {
		return "admin/menu_content";
	}
	
	// 订单管理
	@RequestMapping(value="order",method = RequestMethod.GET)
	public String order() {
		return "admin/menu_order";
	}
	
	// 管理员
	@RequestMapping(value="admin",method = RequestMethod.GET)
	public String admin() {
		return "admin/menu_admin";
	}

	// 主体
	@RequestMapping(value="main",method = RequestMethod.GET)
	public String main() {
		return "admin/menu_main";
	}

	// 常用
	@RequestMapping(value="common",method = RequestMethod.GET)
	public String common() {
		return "admin/menu_common";
	}

	// 头部
	public String header() {
		return "header";
	}

}
