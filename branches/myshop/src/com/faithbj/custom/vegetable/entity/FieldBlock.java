package com.faithbj.custom.vegetable.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.faithbj.shop.entity.Member;

/**
 * 实体类 - 土地块
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class FieldBlock extends BlockFee 
{
	private static final long serialVersionUID = 8251680580714376535L;
	
	/**
	 * 年租金
	 */
	private BigDecimal rent;
	/**
	 * 年托管费用
	 */
	private BigDecimal trusteeFee;
	/**
	 * 年配送费用
	 */
	private BigDecimal deliveryFee;
	/**
	 * 积分
	 */
	private Integer point;
	/**
	 * 地址
	 */
	private String address = null;
	/**
	 * 经纬度
	 */
	private String longitudeLatitude = null;
	/**
	 * 土地类型
	 */
	private String fieldType = null;
	
	/**
	 * 土地块下租赁块列表
	 */
	private List<RendBlock> rendBlockList = null;
	/**
	 * 库存，即可承包租赁块数量
	 */
	private Integer store = null;
	/**
	 * 收藏夹会员
	 */
	private Set<Member> favoriteMemberSet;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="favoriteFieldBlockSet")
	public Set<Member> getFavoriteMemberSet() {
		return favoriteMemberSet;
	}

	public void setFavoriteMemberSet(Set<Member> favoriteMemberSet) {
		this.favoriteMemberSet = favoriteMemberSet;
	}

	@Column(nullable = false, length = 512)
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(nullable = true)
	public String getLongitudeLatitude()
	{
		return longitudeLatitude;
	}

	public void setLongitudeLatitude(String longitudeLatitude)
	{
		this.longitudeLatitude = longitudeLatitude;
	}

	@Column(nullable = true)
	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fieldBlock")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	public List<RendBlock> getRendBlockList()
	{
		return rendBlockList;
	}

	public void setRendBlockList(List<RendBlock> rendBlockList)
	{
		this.rendBlockList = rendBlockList;
	}
	
	@Column(nullable = false)
	public BigDecimal getRent()
	{
		return rent;
	}
	public void setRent(BigDecimal rent)
	{
		this.rent = rent;
	}
	@Column(nullable = false)
	public BigDecimal getTrusteeFee()
	{
		return trusteeFee;
	}
	public void setTrusteeFee(BigDecimal trusteeFee)
	{
		this.trusteeFee = trusteeFee;
	}
	@Column(nullable = false)
	public BigDecimal getDeliveryFee()
	{
		return deliveryFee;
	}
	public void setDeliveryFee(BigDecimal deliveryFee)
	{
		this.deliveryFee = deliveryFee;
	}
	@Column(nullable = false)
	public Integer getPoint()
	{
		return point;
	}
	public void setPoint(Integer point)
	{
		this.point = point;
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

	@Column
	public Integer getStore()
	{
		return store;
	}

	public void setStore(Integer store)
	{
		this.store = store;
	}
}
