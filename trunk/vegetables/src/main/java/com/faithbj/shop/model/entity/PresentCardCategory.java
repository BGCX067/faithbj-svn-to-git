package com.faithbj.shop.model.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;


@Entity
public class PresentCardCategory extends BaseEntity  {

	private static final long serialVersionUID = -1330356573428556484L;
	
	/**
	 * 礼品卡面额
	 */
	private BigDecimal denomination;
	/**
	 * 礼品卡中文名
	 */	
	private String alias;
	/**
	 * 礼品卡数量
	 */
//	private int amount;
	
	private Set<PresentCard> presentCardSet;

	public PresentCardCategory() {
	}

	@Column(precision=19,scale=2)
	public BigDecimal getDenomination() {
		return denomination;
	}

	@Column
	public String getAlias() {
		return alias;
	}

	
	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
//	
//	@Column
//	public int getAmount() {
//		return amount;
//	}
//
//	public void setAmount(int amount) {
//		this.amount = amount;
//	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryid")
	public Set<PresentCard> getPresentCardSet() {
		return presentCardSet;
	}

	public void setPresentCardSet(Set<PresentCard> presentCardSet) {
		this.presentCardSet = presentCardSet;
	}

}
