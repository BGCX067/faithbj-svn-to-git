package com.faithbj.custom.vegetable.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.faithbj.shop.entity.CartItem;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.OrderItem;

/**
 * 实体类 - 租赁块
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class RendBlock extends BlockFee 
{
	private static final long serialVersionUID = -8727427702546666000L;
	
	/**
	 * 相对土地块地址
	 */
	private String relativeAddress = null;
	/**
	 * 租赁起始日期
	 */
	private Date startDate = null;
	/**
	 * 租赁结束日期
	 */
	private Date endDate = null;
	
	/**
	 * 租赁块所属土地块
	 */
	private FieldBlock fieldBlock = null;

	/**
	 * 租赁块下耕作块列表
	 */
	private List<FarmingBlock> farmingBlockList = null;

	/**
	 * 租赁块所属会员
	 */
	private Member member = null;
	/**
	 * 租赁块所属会员名
	 */
	private String username = null;

	/**
	 * 收藏夹会员
	 */
	private Set<Member> favoriteMemberSet; 
	/**
	 * 购物车项
	 */
	private Set<CartItem> cartItemSet;
	/**
	 * 订单项
	 */
	private Set<OrderItem> orderItemSet;
	
	/**
	 * 是否启用年托管费用
	 */
	private Boolean isTrusteeFeeEnabled = null;
	/**
	 * 是否启用年配送费用
	 */
	private Boolean isDeliveryFeeEnabled = null;

	/**
	 * 所属土地块代码
	 */
	private String fieldBlockCode = null;
	

	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Member> getFavoriteMemberSet() {
		return favoriteMemberSet;
	}

	public void setFavoriteMemberSet(Set<Member> favoriteMemberSet) {
		this.favoriteMemberSet = favoriteMemberSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@Cascade(value = { CascadeType.DELETE })
	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}
	
	@Column(nullable = false, length = 512)
	public String getRelativeAddress()
	{
		return relativeAddress;
	}

	public void setRelativeAddress(String relativeAddress)
	{
		this.relativeAddress = relativeAddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rendBlock")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	public List<FarmingBlock> getFarmingBlockList()
	{
		return farmingBlockList;
	}

	public void setFarmingBlockList(List<FarmingBlock> farmingBlockList)
	{
		this.farmingBlockList = farmingBlockList;
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
	
	@Column
	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	@Column
	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Column(nullable = false)
	public Boolean getIsTrusteeFeeEnabled()
	{
		return isTrusteeFeeEnabled;
	}

	public void setIsTrusteeFeeEnabled(Boolean isTrusteeFeeEnabled)
	{
		this.isTrusteeFeeEnabled = isTrusteeFeeEnabled;
	}

	@Column(nullable = false)
	public Boolean getIsDeliveryFeeEnabled()
	{
		return isDeliveryFeeEnabled;
	}

	public void setIsDeliveryFeeEnabled(Boolean isDeliveryFeeEnabled)
	{
		this.isDeliveryFeeEnabled = isDeliveryFeeEnabled;
	}
	
	@Column
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	@Column
	public String getFieldBlockCode()
	{
		return fieldBlockCode;
	}

	public void setFieldBlockCode(String fieldBlockCode)
	{
		this.fieldBlockCode = fieldBlockCode;
	}

	
	//------------------------------------------------------------------------
	@Column(nullable = false)
	public String getName()
	{
		return super.getName();
	}
	@Column
	public String getDescription()
	{
		return super.getDescription();
	}
	@Column(nullable = false)
	public Boolean getIsMarketable()
	{
		return super.getIsMarketable();
	}
	@Column(nullable = true, length = 4000)
	public String getProductImageListStore()
	{
		return super.getProductImageListStore();
	}
	@Column(nullable = false, length = 16)
	public String getCode()
	{
		return super.getCode();
	}
	@Column(nullable = false)
	public Integer getArea()
	{
		return super.getArea();
	}

	// 获取优惠价格，若member对象为null则返回原价格
	@Transient
	public BigDecimal getPreferentialPrice() {
		
		BigDecimal result = fieldBlock.getRent();
		if (this.getIsTrusteeFeeEnabled() != null && this.getIsTrusteeFeeEnabled())
		{
			result = result.add(fieldBlock.getTrusteeFee());
		}
		if (this.getIsDeliveryFeeEnabled() != null && this.getIsDeliveryFeeEnabled())
		{
			result = result.add(fieldBlock.getDeliveryFee());
		}
		return result;
	}
	
	
}
