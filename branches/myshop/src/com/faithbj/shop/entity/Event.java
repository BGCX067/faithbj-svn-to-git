package com.faithbj.shop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Entity
@Searchable
public class Event extends BaseEntity {

	private static final long serialVersionUID = -8690757978725678535L;
	private String eventname;
	private BigDecimal account;
	private Date expiredate;

	public Event() {

	}

	public Event(String eventname, BigDecimal account, Date createdate,Date expiredate, Date modifyDate) {
		this.eventname = eventname;
		this.account = account;
		this.expiredate = expiredate;
	}

	@SearchableProperty(store = Store.YES)
	@Column(nullable = true)
	public String getEventname() {
		return this.eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	@SearchableProperty(store = Store.YES)
	@Column(precision = 15, scale = 5, nullable = true)
	public BigDecimal getAccount() {
		return this.account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	@Column(nullable = true)
	public Date getExpiredate() {
		return this.expiredate;
	}

	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}

}
