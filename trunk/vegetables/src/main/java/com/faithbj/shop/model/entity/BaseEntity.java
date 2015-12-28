package com.faithbj.shop.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 实体类 - 基类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2012-04-06
 * @version 1.0
 */


@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6718838800112233445L;

	private String id;// ID

	private Date createDate;// 创建日期

	private Date modifyDate;// 修改日期

	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	@SearchableProperty(store = Store.YES)
	@Column(insertable=true,updatable = false)
	public Date getCreateDate() {
		return createDate;
	}
	
	
//	@SearchableProperty(store = Store.YES)
	@Column(insertable=true,updatable = true)
	public Date getModifyDate() {
		return modifyDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		return true;
	}

}
