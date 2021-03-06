package com.faithbj.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 实体类 - 友情链接
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class FriendLink extends BaseEntity {

	private static final long serialVersionUID = 3019642557500517628L;

	private String name;// 名称
	private String logo;// Logo
	private String url;// 链接地址
	private Integer orderList;// 排序

	@Column(nullable = false)
	public String getName() {
		return name;
	}
	@Column
	public String getLogo() {
		return logo;
	}
	@Column(nullable = false)
	public String getUrl() {
		return url;
	}
	@Column(nullable = false)
	public Integer getOrderList() {
		return orderList;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

}
