package com.faithbj.shop.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 实体类 - 耕种日志
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class FarmingLog extends BaseEntity
{
	private static final long serialVersionUID = 4149432082832586565L;

	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 操作人名称(可以是用户自己，或者代管理人)
	 */
	private String operator;
	/**
	 * 操作人动作，与landStatus状态对应0耕种，1收获，3清理，4施肥，5杀虫
	 */
	private Integer operation;
	
	/**
	 * 耕地
	 */
	private Farmland farmland;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperationTime() {
		return operationTime;
	}
	
	@Column(nullable=false)
	public String getOperator() {
		return operator;
	}
	@Column(nullable=false)
	public Integer getOperation() {
		return operation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Farmland getFarmland() {
		return farmland;
	}

	
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setFarmland(Farmland farmland) {
		this.farmland = farmland;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
}
