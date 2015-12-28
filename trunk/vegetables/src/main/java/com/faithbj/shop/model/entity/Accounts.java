package com.faithbj.shop.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


/**
 * 记账本
 * @author Administrator
 *
 */
@Entity
public class Accounts extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7261087359152336729L;
	
	/**
	 * 收获记录的开始时间
	 */
	private Date gainDate;
	
	/**
	 * 收获记录的会员
	 */
	private Members member;
	
	
	/**
	 * 收货量（可带单位，如：100斤）
	 */
	private String account;
	
	/**
	 * 备注
	 */
	private String comment;
	
	/**
	 * 收获作物
	 */
	private Seed seed;

	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Seed getSeed() {
		return seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}

	
}
