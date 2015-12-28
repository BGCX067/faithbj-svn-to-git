package com.faithbj.custom.vegetable.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.faithbj.shop.bean.ProductImage;
import com.faithbj.shop.entity.BaseEntity;

/**
 * 实体类 - 耕种地块默认图片
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-03
 * @version 1.0
 */

@Entity
public class FarmingBlockDefaultImage extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**
	 * 图片路径存储
	 */
	private String productImageListStore;
	/**
	 * 耕种状态
	 */
	private String status = null;
	/**
	 * 种子名称
	 */
	private String seedName = null;
	
	@Column(nullable = true, length = 4000)
	public String getProductImageListStore()
	{
		return productImageListStore;
	}
	public void setProductImageListStore(String productImageListStore)
	{
		this.productImageListStore = productImageListStore;
	}
	
	@Column
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	@Column
	public String getSeedName()
	{
		return seedName;
	}
	public void setSeedName(String seedName)
	{
		this.seedName = seedName;
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
}
