package com.faithbj.shop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.faithbj.shop.util.SystemConfigUtil;

/**
 * Bean类 - 订单项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class OrderItem extends BaseEntity {
	
	private static final long serialVersionUID = 5030818078599298690L;
	
	private String productSn;// 商品货号
	private String productName;// 商品名称
	private BigDecimal productPrice;// 商品价格
	private String productHtmlFilePath;// 商品HTML静态文件路径
	private Integer productQuantity;// 商品数量
	private Integer deliveryQuantity;// 发货数量
	private Integer totalDeliveryQuantity;// 总发货量
	
	private Order order;// 订单
	private Product product;// 商品
	
	@Column(updatable = false, nullable = false)
	public String getProductSn() {
		return productSn;
	}
	
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	
	@Column(updatable = false, nullable = false)
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	
	// 精度处理
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = SystemConfigUtil.getPriceScaleBigDecimal(productPrice);
	}
	
	@Column(nullable = false, updatable = false)
	public String getProductHtmlFilePath() {
		return productHtmlFilePath;
	}
	
	public void setProductHtmlFilePath(String productHtmlFilePath) {
		this.productHtmlFilePath = productHtmlFilePath;
	}
	
	@Column(nullable = false)
	public Integer getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	@Column(nullable = false)
	public Integer getDeliveryQuantity() {
		return deliveryQuantity;
	}

	public void setDeliveryQuantity(Integer deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}

	@Column(nullable = false)
	public Integer getTotalDeliveryQuantity() {
		return totalDeliveryQuantity;
	}

	public void setTotalDeliveryQuantity(Integer totalDeliveryQuantity) {
		this.totalDeliveryQuantity = totalDeliveryQuantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	// 获取小计价格
	@Transient
	public BigDecimal getSubtotalPrice() {
		BigDecimal subtotalPrice = productPrice.multiply(new BigDecimal(productQuantity.toString()));
		return SystemConfigUtil.getOrderScaleBigDecimal(subtotalPrice);
	}
	
}
