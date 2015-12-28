package com.faithbj.shop.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 实体类 - 在线问答
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
@Table(name = "online")
public class Online extends BaseEntity  {

	private static final long serialVersionUID = -5132652107151648662L;

	public static final String PATH_SEPARATOR = ",";// 树路径分隔符

	private String title;// 主题
	private String question;// 提问
	private String username;// 用户名
	private String status;// 问题状态，0未回答，1已回答
	private String answer;//答案
	


	@Column(nullable=true,length =40)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(nullable=true,length =500)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(nullable=true,length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(length = 500)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


}
