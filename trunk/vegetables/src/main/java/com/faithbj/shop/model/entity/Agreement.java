package com.faithbj.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 实体类 - 会员注册协议
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class Agreement extends BaseEntity {

	private static final long serialVersionUID = 7226979256801891226L;

	public static final String AGREEMENT_ID = "1";// 记录ID

	private String content;// 内容

	@Column(length = 10000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
