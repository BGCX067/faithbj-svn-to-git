package com.faithbj.shop.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 购物车项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class CartItem extends BaseEntity {

	private static final long serialVersionUID = -4241469437632553865L;

	private Integer quantity;// 数量
	
	private Product product;// 商品
	
	private Members member;// 会员
	
	@Column(nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	@ForeignKey(name="FK4393E73CA10C3E")
	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}
	
	// 获取优惠价格，若member对象为null则返回原价格
	@Transient
	public BigDecimal getPreferentialPrice() {
		if (member != null) {
			BigDecimal preferentialPrice = product.getPrice().multiply(new BigDecimal(member.getMemberRank().getPreferentialScale()).divide(new BigDecimal(100)));
			return preferentialPrice;
		} else {
			return product.getPrice();
		}
	}
	
	// 获取小计价格
	@Transient
	public BigDecimal getSubtotalPrice() {
		BigDecimal subtotalPrice = getPreferentialPrice().multiply(new BigDecimal(quantity));
		return subtotalPrice;
	}

}
