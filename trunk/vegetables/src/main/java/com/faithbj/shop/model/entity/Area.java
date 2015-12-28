package com.faithbj.shop.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 地区
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: www.faithbj.com
 * </p>
 * 
 * @author faithbj
 * @date 2011-12-16
 * @version 1.0
 */

@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name",
//		"parent_id" }) })
public class Area extends BaseEntity {

	private static final long serialVersionUID = -2158109459123036967L;
	public static final String PATH_SEPARATOR = ",";// 树路径分隔符


	private String name;// 地区名称

	private String displayName;

	private String path;// 树路径

	private Area parent;// 上级地区

	private Set<Area> children;// 下级地区
	
	private Integer orderlist;
	
	@Column(nullable=false)	
	private Integer grade;	
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	@Column(nullable = false, length = 3000)
	public String getDisplayName() {
		return displayName;
	}
	@Column(nullable = true, length = 10000)
	public String getPath() {
		return path;
	}
	
	@JsonIgnore 
	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_area_parent")
	public Area getParent() {
		return parent;
	}
	
	@JsonIgnore 
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.REMOVE })
	@OrderBy("orderList asc")
	public Set<Area> getChildren() {
		return children;
	}
	@Column
	public Integer getOrderList() {
		return this.orderlist;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public void setChildren(Set<Area> children) {
		this.children = children;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setOrderList(Integer orderlist) {
		this.orderlist = orderlist;
	}

	public Integer getGrade()
	{
	    return this.grade;
	}

	public void setGrade(Integer grade)
	{
	    this.grade = grade;
	}
	
	// 获取分类层级（顶级分类：0）
	@JsonIgnore
	@Transient
	public Integer getLevel() {
		return path.split(PATH_SEPARATOR).length - 1;
	}
	
	 @Transient
	  public void onSave()
	  {
	    if ((this.grade == null) || (this.grade.intValue() < 0))
	      this.grade = Integer.valueOf(0);
	    if ((this.orderlist == null) || (this.orderlist.intValue() < 0))
	      this.orderlist = Integer.valueOf(0);
	  }

	  @Transient
	  public void onUpdate()
	  {
		  if ((this.grade == null) || (this.grade.intValue() < 0))
		      this.grade = Integer.valueOf(0);
		    if ((this.orderlist == null) || (this.orderlist.intValue() < 0))
		      this.orderlist = Integer.valueOf(0);
	  }
}
