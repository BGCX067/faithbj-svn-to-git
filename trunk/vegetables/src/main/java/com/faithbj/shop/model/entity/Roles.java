package com.faithbj.shop.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 角色
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class Roles extends BaseEntity {

	private static final long serialVersionUID = -6614052029623997372L;

	private String name;// 角色名称
	private String value;// 角色标识
	private Boolean isSystem;// 是否为系统内置角色
	private String description;// 描述
	
	private Set<Members> memberSet;// 用户
	private Set<Resource> resourceSet;// 资源

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	@Column(nullable = false, unique = true)
	public String getValue() {
		return value;
	}

	@Column(nullable = false, updatable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	@Column(length = 5000)
	public String getDescription() {
		return description;
	}
	
	@ManyToMany(mappedBy="roleSet", fetch=FetchType.LAZY)
	@ForeignKey(name="fk_role_member_set")
	public Set<Members> getMemberSet() {
		return memberSet;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "id")

	

	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}
	
	public void setMemberSet(Set<Members> memberSet) {
		this.memberSet = memberSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

}
