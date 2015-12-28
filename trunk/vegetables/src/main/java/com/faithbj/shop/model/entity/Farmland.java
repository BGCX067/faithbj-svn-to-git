package com.faithbj.shop.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.annotations.ForeignKey;

import com.faithbj.shop.model.configuration.ProductImage;
import com.faithbj.shop.utils.JsonUtil;

/**
 * 实体类 - 耕地，把某个土地块儿（商品）再细分为更小的单位进行耕作
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Entity
public class Farmland extends BaseEntity
{
	private static final long serialVersionUID = -6185274101524914401L;
	
	/**
	 * 租赁块代码,按录入时间yyyymmddssffmm格式
	 */
	private String rendBlockCode;
	
	
	
	private String farmlandImage;//耕地图片位置
	
	
	
	/**
	 * 耕种状态:0要耕种，1要收获，2，要施肥，3，要杀虫
	 */
	private Integer landStatus;

	/**
	 * 操作人动作，与landStatus状态对应0耕种，1收获，3清理，4施肥，5杀虫
	 */
	private Set<FarmingLog> farmingLog;
	/**
	 * 所属租赁块(土地作为一种商品,从属于某个商品)，与土地商品是一对多关系，即某个商品有多块儿耕地
	 */
	private Product product;
	/**
	 * 种子
	 */
	private Seed seed;
	
	
	@Column
	public String getRendBlockCode() {
		return rendBlockCode;
	}
	@Column(length = 10000)
	public String getFarmlandImage() {
		return farmlandImage;
	}
	
	@Column(nullable = false)
	public Integer getLandStatus() {
		return landStatus;
	}
	
	/**
	 * 看到结尾时many，默认是懒加载
	 * @return
	 */
	@OneToMany(mappedBy = "farmland",cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY)
	public Set<FarmingLog> getFarmingLog() {
		return farmingLog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Product getProduct() {
		return product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Seed getSeed() {
		return seed;
	}
	
	public void setSeed(Seed seed) {
		this.seed = seed;
	}
	public void setRendBlockCode(String rendBlockCode) {
		this.rendBlockCode = rendBlockCode;
	}
	public void setLandStatus(Integer landStatus) {
		this.landStatus = landStatus;
	}
	public void setFarmingLog(Set<FarmingLog> farmingLog) {
		this.farmingLog = farmingLog;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setFarmlandImage(String farmlandImage) {
		this.farmlandImage = farmlandImage;
	}
	
	
	
	
	// 获取商品图片
	@SuppressWarnings("unchecked")
	@Transient
	public List<ProductImage> getProductImageList() {
		if (StringUtils.isEmpty(farmlandImage)) {
			return ListUtils.lazyList(new ArrayList<ProductImage>(), FactoryUtils.instantiateFactory(ProductImage.class));
		}
		List<ProductImage> resultProductImageList=JsonUtil.Json2List(farmlandImage, new TypeReference<List<ProductImage>>(){});
		return resultProductImageList;
	}
		
	/**
	* 设置商品图片
	*/
	@Transient
	public void setProductImageList(List<ProductImage> productImageList) {
		if (null==productImageList||productImageList.isEmpty()) {
			farmlandImage = null;
			return;
		}
		farmlandImage=JsonUtil.toJson(productImageList);
	}
		
	@Transient
	public void onSave()
	{
		
	}		
	
	@Transient
	public void onUpdate()
	{
		
	}
	
	
	
	
	
	
	
}
