package com.faithbj.shop.model.configuration;

import java.io.Serializable;


/**
 * Bean类 - 购物车项Cookie
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class CartItemCookie implements Serializable{
	
	private static final long serialVersionUID = -5952587432195419226L;
	
	public static final String CART_ITEM_LIST_COOKIE_NAME = "cartItemList";// 保存未登录会员购物车项集合的Cookie名称
	public static final int CART_ITEM_LIST_COOKIE_MAX_AGE = 86400;// 保存未登录会员购物车项集合的Cookie最大有效时间（单位：秒）
	
	private String i;// 商品ID
	private Integer q;// 商品购买数量
	
	public String getI() {
		return i;
	}
	
	public void setI(String i) {
		this.i = i;
	}

	public Integer getQ() {
		return q;
	}

	public void setQ(Integer q) {
		this.q = q;
	}
	
}
