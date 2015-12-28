package com.faithbj.custom.vegetable.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.faithbj.shop.entity.BaseEntity;
import com.faithbj.shop.entity.Order;
import com.faithbj.shop.util.SystemConfigUtil;

/**
 * 实体类 - 租赁块相关订单项
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-25
 * @version 1.0
 */

@Entity
public class FieldBlockOrderItem extends BaseEntity
{
	private static final long serialVersionUID = 2723976834368059458L;

	private String fieldBlockCode;
	private String fieldBlockName;
	private Boolean isTrusteeFeeEnabled = null;
	private Boolean isDeliveryFeeEnabled = null;
	private BigDecimal rent;
	private BigDecimal trusteeFee;
	private BigDecimal deliveryFee;
	private Integer quantity;// 数量
	
	private Order order;
	private FieldBlock fieldBlock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(updatable = false, nullable = false)
	public String getFieldBlockCode()
	{
		return fieldBlockCode;
	}
	public void setFieldBlockCode(String fieldBlockCode)
	{
		this.fieldBlockCode = fieldBlockCode;
	}

	@Column(updatable = false, nullable = false)
	public String getFieldBlockName()
	{
		return fieldBlockName;
	}
	public void setFieldBlockName(String fieldBlockName)
	{
		this.fieldBlockName = fieldBlockName;
	}

	@Column
	public Boolean getIsTrusteeFeeEnabled()
	{
		return isTrusteeFeeEnabled;
	}
	public void setIsTrusteeFeeEnabled(Boolean isTrusteeFeeEnabled)
	{
		this.isTrusteeFeeEnabled = isTrusteeFeeEnabled;
	}

	@Column
	public Boolean getIsDeliveryFeeEnabled()
	{
		return isDeliveryFeeEnabled;
	}
	public void setIsDeliveryFeeEnabled(Boolean isDeliveryFeeEnabled)
	{
		this.isDeliveryFeeEnabled = isDeliveryFeeEnabled;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getRent()
	{
		return rent;
	}
	public void setRent(BigDecimal rent)
	{
		this.rent = rent;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getTrusteeFee()
	{
		return trusteeFee;
	}
	public void setTrusteeFee(BigDecimal trusteeFee)
	{
		this.trusteeFee = trusteeFee;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getDeliveryFee()
	{
		return deliveryFee;
	}
	public void setDeliveryFee(BigDecimal deliveryFee)
	{
		this.deliveryFee = deliveryFee;
	}

	@Column
	public Integer getQuantity()
	{
		return quantity;
	}
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public FieldBlock getFieldBlock()
	{
		return fieldBlock;
	}
	public void setFieldBlock(FieldBlock fieldBlock)
	{
		this.fieldBlock = fieldBlock;
	}

	// 获取小计价格
	@Transient
	public BigDecimal getSubtotalPrice() {
		BigDecimal subtotalPrice = this.rent;
		if (this.isTrusteeFeeEnabled)
		{
			subtotalPrice = subtotalPrice.add(this.trusteeFee);
		}
		if (this.isDeliveryFeeEnabled)
		{
			subtotalPrice = subtotalPrice.add(this.deliveryFee);
		}
		return SystemConfigUtil.getOrderScaleBigDecimal(subtotalPrice);
	}

}
