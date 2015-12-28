package com.faithbj.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * 实体类 - 导航
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class Navigation extends BaseEntity {

	private static final long serialVersionUID = -7635757647887646795L;

	// 导航位置:顶部、中间、底部
	public enum Position {
		top, middle, bottom
	}

	private String navigationName;// 名称
	private Position navigationPosition;// 位置
	private String url;// 链接地址;
	private Boolean isVisible;// 是否显示
	private Boolean isBlankTarget;// 是否在新窗口中打开
	private Integer orderList;// 排序

	@Column(nullable = false)
	public String getNavigationName() {
		return navigationName;
	}

	@Enumerated
	@Column(nullable = false)
	public Position getNavigationPosition() {
		return navigationPosition;
	}

	@Column(nullable = false)
	public String getUrl() {
		return url;
	}

	@Column(nullable = false)
	public Boolean getIsBlankTarget() {
		return isBlankTarget;
	}

	@Column(nullable = false)
	public Boolean getIsVisible() {
		return isVisible;
	}

	@Column(nullable = false)
	public Integer getOrderList() {
		return orderList;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}
	public void setNavigationPosition(Position navigationPosition) {
		this.navigationPosition = navigationPosition;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void setIsBlankTarget(Boolean isBlankTarget) {
		this.isBlankTarget = isBlankTarget;
	}
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

}
