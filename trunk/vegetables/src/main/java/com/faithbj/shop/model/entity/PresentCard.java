package com.faithbj.shop.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class PresentCard extends BaseEntity  {

	private static final long serialVersionUID = -1300326583420556484L;
	/**
	 * 礼品卡状态：0激活，1，已使用，2已过期
	 */
	public static enum  PresentCardStatus{activated, used,expired};
	
	/**
	 * 礼品卡类型，类型不同，面额不同
	 */		
	private PresentCardCategory categoryid;
	/**
	 * 用户ID，一经绑定，不能更改
	 */		
	private Members memberid;
	/**
	 * 礼品卡号码
	 */	
	private String presentNumber;
	/**
	 * 礼品卡密码
	 */	
	private String presentPassword;
	/**
	 * 礼品卡状态：0激活，1，已使用，2已过期
	 */
	private Integer presentStatus;
	/**
	 * 礼品卡生效时间
	 */
	private Date startDate;
	/**
	 * 礼品卡过期时间
	 */
	private Date expireDate;

	public PresentCard() {
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL},optional=true)
	public PresentCardCategory getCategoryid() {
		return categoryid;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	public Members getMemberid() {
		return memberid;
	}
	
	@Column
	public String getPresentNumber() {
		return presentNumber;
	}

	@Column
	public String getPresentPassword() {
		return presentPassword;
	}

	@Column
	public Integer getPresentStatus() {
		return presentStatus;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	@Column
	@Temporal(TemporalType.DATE)
	public Date getExpireDate() {
		return expireDate;
	}

	public void setCategoryid(PresentCardCategory categoryid) {
		this.categoryid = categoryid;
	}
	public void setMemberid(Members memberid) {
		this.memberid = memberid;
	}
	public void setPresentNumber(String presentNumber) {
		this.presentNumber = presentNumber;
	}
	public void setPresentPassword(String presentPassword) {
		this.presentPassword = presentPassword;
	}
	public void setPresentStatus(Integer presentStatus) {
		this.presentStatus = presentStatus;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
