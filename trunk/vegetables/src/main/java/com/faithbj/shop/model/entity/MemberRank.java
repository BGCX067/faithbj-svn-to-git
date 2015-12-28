package com.faithbj.shop.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


/**
 * 实体类 - 会员等级
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class MemberRank extends BaseEntity {

	private static final long serialVersionUID = 3599029355500655209L;

	private String name;// 等級名称
	
	private Double preferentialScale;// 优惠百分比
	
	private Integer point;// 所需积分

	private Boolean isDefault;// 是否为默认等级

	private Set<Members> memberSet;// 会员

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	@Column(nullable = false)	
	public Double getPreferentialScale() {
		return preferentialScale;
	}

	@Column(nullable = false, unique = true)	
	public Integer getPoint() {
		return point;
	}

	@Column(nullable = false)
	public Boolean getIsDefault() {
		return isDefault;
	}

	@OneToMany(mappedBy = "memberRank",cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
	public Set<Members> getMemberSet() {
		return memberSet;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPreferentialScale(Double preferentialScale) {
		this.preferentialScale = preferentialScale;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public void setMemberSet(Set<Members> memberSet) {
		this.memberSet = memberSet;
	}

}
