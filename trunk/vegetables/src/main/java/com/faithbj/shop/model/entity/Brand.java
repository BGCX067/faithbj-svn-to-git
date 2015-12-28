package com.faithbj.shop.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * 实体类 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */


@Entity
public class Brand extends BaseEntity {

	private static final long serialVersionUID = -6109590619136943215L;

	private String name;// 名称
	/**
	 * logo图片存放的路径
	 */
	private String logoPath;
	private String url;// 网址
	private String introduction;// 介绍
	private Integer orderList;// 排序
	
	private Set<Product> productSet;// 商品

	@Column(nullable = false)
	public String getName() {
		return name;
	}
	@Column
	public String getLogoPath() {
		return logoPath;
	}
	@Column	
	public String getUrl() {
		return url;
	}

	@Column(length = 10000)
	public String getIntroduction() {
		return introduction;
	}	
	
	@Column
	public Integer getOrderList() {
		return orderList;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	public Set<Product> getProductSet() {
		return productSet;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

}
