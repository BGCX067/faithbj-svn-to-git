package com.faithbj.shop.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 地区
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class Seed extends BaseEntity {

	private static final long serialVersionUID = -2158109459123036967L;
	
	/**
	 * 种子名称
	 */
	private String seedName;
	/**
	 * 种子周期的起始日期
	 */
	private Date startDate;
	/**
	 * 种子周期的结束日期
	 */
	private Date endDate;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 类型，1为当季默认种子，0为自选种子
	 * 
	 */
	private int type;
	
	
	private Set<Farmland> farmland;
	
	/**
	 * 种子的收获记录
	 */
	private Set<Accounts> accounts;
	
	@Column(nullable = false)
	public String getSeedName() {
		return seedName;
	}
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seed")
	public Set<Farmland> getFarmland() {
		return farmland;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public void setSeedName(String seedName) {
		this.seedName = seedName;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setFarmland(Set<Farmland> farmland) {
		this.farmland = farmland;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seed")
	public Set<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Accounts> accounts) {
		this.accounts = accounts;
	}
	
	
}
