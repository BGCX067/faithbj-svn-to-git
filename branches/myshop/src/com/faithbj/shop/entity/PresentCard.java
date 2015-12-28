package com.faithbj.shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PresentCard implements java.io.Serializable {

	private static final long serialVersionUID = 1300326583420556484L;
	private Integer id;
	private String eventid;
	private String memberid;
	private String userNum;
	private Integer activate;
	private Date createDate;
	private Date expireDate;


	public PresentCard() {
	}

	public PresentCard(String eventid) {
		this.eventid = eventid;
	}

	public PresentCard(String eventid, String memberid, String userNum,
			Integer activate) {
		this.eventid = eventid;
		this.memberid = memberid;
		this.userNum = userNum;
		this.activate = activate;
	}

	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = true)
	public String getEventid() {
		return this.eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	@Column(nullable = true)
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(nullable = true)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(nullable = true)
	public Integer getActivate() {
		return this.activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}
	@Column(nullable = true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(nullable = true)
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
