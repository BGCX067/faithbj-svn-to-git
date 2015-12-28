package com.faithbj.custom.vegetable.entity;

import java.util.List;

import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.entity.BaseEntity;

/**
 * 实体类 - 土地块和租赁块的抽象基类
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-25
 * @version 1.0
 */

public abstract class BlockFee extends BaseEntity 
{
	private static final long serialVersionUID = 4425789531545699853L;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 是否上架
	 */
	private Boolean isMarketable;
	/**
	 * 图片路径存储
	 */
	private String productImageListStore;
	/**
	 * 代码
	 */
	private String code = null;

	/**
	 * 面积
	 */
	private Integer area = null;
	
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Boolean getIsMarketable()
	{
		return isMarketable;
	}
	public void setIsMarketable(Boolean isMarketable)
	{
		this.isMarketable = isMarketable;
	}
	public String getProductImageListStore()
	{
		return productImageListStore;
	}
	public void setProductImageListStore(String productImageListStore)
	{
		this.productImageListStore = productImageListStore;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public Integer getArea()
	{
		return area;
	}
	public void setArea(Integer area)
	{
		this.area = area;
	}
	

	// 获取商品图片
	@SuppressWarnings("unchecked")
	@Transient
	public List<ProductImage> getProductImageList() {
		if (StringUtils.isEmpty(productImageListStore)) {
			return null;
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(ProductImage.class);
		JSONArray jsonArray = JSONArray.fromObject(productImageListStore);
		return (List<ProductImage>) JSONSerializer.toJava(jsonArray, jsonConfig);
	}
	
	// 设置商品图片
	@Transient
	public void setProductImageList(List<ProductImage> productImageList) {
		if (productImageList == null || productImageList.size() == 0) {
			productImageListStore = null;
			return;
		}
		JSONArray jsonArray = JSONArray.fromObject(productImageList);
		productImageListStore = jsonArray.toString();
	}
	/**
	 * 根据商品图片ID获取商品图片，未找到则返回null
	 * 
	 * @param ProductImage
	 *            ProductImage对象
	 */
	@Transient
	public ProductImage getProductImage(String productImageId) {
		List<ProductImage> productImageList = getProductImageList();
		for (ProductImage productImage : productImageList) {
			if (StringUtils.equals(productImageId, productImage.getId())) {
				return productImage;
			}
		}
		return null;
	}
	
	//------------------------------------------------------------------------
//	@Column(nullable = false)
//	public String getName()
//	{
//		return super.getName();
//	}
//	@Column
//	public String getDescription()
//	{
//		return super.getDescription();
//	}
//	@Column(nullable = false)
//	public Integer getPoint()
//	{
//		return super.getPoint();
//	}
//	@Column(nullable = false)
//	public Boolean getIsMarketable()
//	{
//		return super.getIsMarketable();
//	}
//	@Column
//	public String getProductImageListStore()
//	{
//		return super.getProductImageListStore();
//	}
//	@Column(nullable = false, length = 16)
//	public String getCode()
//	{
//		return super.getCode();
//	}
//	@Column(nullable = false)
//	public BigDecimal getRent()
//	{
//		return super.getRent();
//	}
//	@Column(nullable = false)
//	public BigDecimal getTrusteeFee()
//	{
//		return super.getTrusteeFee();
//	}
//	@Column(nullable = false)
//	public BigDecimal getDeliveryFee()
//	{
//		return super.getDeliveryFee();
//	}
//	@Column(nullable = false)
//	public Integer getArea()
//	{
//		return super.getArea();
//	}
	
}
