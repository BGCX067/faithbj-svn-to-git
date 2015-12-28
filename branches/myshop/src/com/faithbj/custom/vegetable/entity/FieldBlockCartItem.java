package com.faithbj.custom.vegetable.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.faithbj.shop.entity.BaseEntity;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.util.SystemConfigUtil;

/**
 * 实体类 - 租赁块相关购物车项
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-25
 * @version 1.0
 */

@Entity
public class FieldBlockCartItem extends BaseEntity
{
	private static final long serialVersionUID = -2259249323532543641L;

	private Member member;// 会员
	private Integer quantity;// 数量
	private FieldBlock fieldBlock = null;
	private Boolean isTrusteeFeeEnabled = null;
	private Boolean isDeliveryFeeEnabled = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public FieldBlock getFieldBlock()
	{
		return fieldBlock;
	}

	public void setFieldBlock(FieldBlock fieldBlock)
	{
		this.fieldBlock = fieldBlock;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	// 获取优惠价格，若member对象为null则返回原价格
	@Transient
	public BigDecimal getPreferentialPrice() {
		
		BigDecimal price = fieldBlock.getRent();
		if (this.getIsTrusteeFeeEnabled() != null && this.getIsTrusteeFeeEnabled())
		{
			price = price.add(fieldBlock.getTrusteeFee());
		}
		if (this.getIsDeliveryFeeEnabled() != null && this.getIsDeliveryFeeEnabled())
		{
			price = price.add(fieldBlock.getDeliveryFee());
		}
//		price = price.multiply(new BigDecimal(this.quantity));
		
		if (member != null) {
			BigDecimal preferentialPrice = price.multiply(new BigDecimal(member.getMemberRank().getPreferentialScale().toString()).divide(new BigDecimal("100")));
			return SystemConfigUtil.getPriceScaleBigDecimal(preferentialPrice);
		} else {
			return price;
		}
	}
	
	// 获取小计价格
	@Transient
	public BigDecimal getSubtotalPrice() {
		BigDecimal subtotalPrice = getPreferentialPrice().multiply(new BigDecimal(quantity.toString()));
		return SystemConfigUtil.getOrderScaleBigDecimal(subtotalPrice);
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

}
