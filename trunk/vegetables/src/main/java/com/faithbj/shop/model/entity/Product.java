package com.faithbj.shop.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

import com.faithbj.shop.model.configuration.ProductImage;
import com.faithbj.shop.utils.JsonUtil;
import com.faithbj.shop.utils.SerialNumberUtil;

/**
 * 实体类 - 商品
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
//@Searchable
@JsonIgnoreProperties({"JavassistLazyInitializer", "handler", "createDate", "modifyDate", "optionStore", "propertyIndex", "productType"})
public class Product extends BaseEntity {

	private static final long serialVersionUID = 4858058186018438872L;
	
//	private List<ProductImage> productImageList;
	public Product(){
	}
	
	// 重量单位（克、千克、吨）
	public enum WeightUnit {
		g, kg, t
	}

	public static final int MAX_BEST_PRODUCT_LIST_COUNT = 20;// 精品商品列表最大商品数
	public static final int MAX_NEW_PRODUCT_LIST_COUNT = 20;// 新品商品列表最大商品数
	public static final int MAX_HOT_PRODUCT_LIST_COUNT = 20;// 热销商品列表最大商品数
	public static final int DEFAULT_PRODUCT_LIST_PAGE_SIZE = 12;// 商品列表默认每页显示数
	
	private String productSn;// 货号
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	/**
	 * 商品成本价格
	 */	
	private BigDecimal cost;
	
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;
	/**
	 * 商品重量
	 */
	private Double weight;
	private WeightUnit weightUnit;// 重量单位
	private Integer store;// 商品库存数量
	/**
	 * 商品所在的具体仓库位置,便于检索
	 */
	private String storePlace;
	private Integer freezeStore;// 被占用库存数
	private Integer point;// 积分
	private Boolean isMarketable;// 是否上架
	private Boolean isBest;// 是否为精品商品
	private Boolean isNew;// 是否为新品商品
	private Boolean isHot;// 是否为热销商品
	private String introduction;// 描述
	private String metaKeywords;// 页面关键词
	private String metaDescription;// 页面描述
	private String htmlFilePath;// HTML静态文件路径
	private String productImageStore;// 商品图片路径存储
	/**
	 * 商品是否为土地
	 */
	private Boolean isLand;
	
	private ProductCategory productCategory;// 商品分类
	private Brand brand;// 品牌
	private ProductType productType;// 商品类型
	private String attributeMapStore;// 商品属性存储
	private String productParameterValue;// 商品参数存储
	private Set<Members> favoriteMemberSet; // 收藏夹会员
	private Set<Members> favorMemberSet; // 喜好商品会员
	private Set<Members> hateMemberSet; // 忌讳商品会员
	private Set<CartItem> cartItemSet;// 购物车项
	private Set<OrderItem> orderItemSet;// 订单项
	private Set<DeliveryItem> deliveryItemSet;// 物流项
	private Set<Farmland> farmland;// 耕地
	
	
	 
	@Column	
	public String getProductParameterValue() {
		return productParameterValue;
	}

	@Column
	public String getStorePlace() {
		return storePlace;
	}

	@Column
	public Boolean getIsLand() {
		return isLand;
	}
	
	@Column(nullable = false, unique = true)
	public String getProductSn() {
		return productSn;
	}

//	@SearchableProperty(store = Store.YES)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

//	@SearchableProperty(store = Store.YES)
	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getPrice() {
		return price;
	}

	@Column(precision=15, scale=5)	  
	public BigDecimal getCost() {
		return cost;
	}
	
//	@SearchableProperty(store = Store.YES)
	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	@Column
	public Double getWeight() {
		return weight;
	}

	@Enumerated
	@Column
	public WeightUnit getWeightUnit() {
		return weightUnit;
	}
	
//	@SearchableProperty(store = Store.YES)
	public Integer getStore() {
		return store;
	}

//	@SearchableProperty(store = Store.YES)
	@Column
	public Integer getFreezeStore() {
		return freezeStore;
	}

	@Column
	public Integer getPoint() {
		return point;
	}

//	@SearchableProperty(store = Store.NO)
	@Column
	public Boolean getIsMarketable() {
		return isMarketable;
	}

//	@SearchableProperty(store = Store.NO)
	@Column(nullable = false)
	public Boolean getIsBest() {
		return isBest;
	}

//	@SearchableProperty(store = Store.NO)
	@Column(nullable = false)
	public Boolean getIsNew() {
		return isNew;
	}

//	@SearchableProperty(store = Store.NO)
	@Column(nullable = false)
	public Boolean getIsHot() {
		return isHot;
	}

	@Lob
	public String getIntroduction() {
		return introduction;
	}

	@Column(length = 5000)
	public String getMetaKeywords() {
		return metaKeywords;
	}
	
	@Column(length = 5000)
	public String getMetaDescription() {
		return metaDescription;
	}

//	@SearchableProperty(index = Index.NO, store = Store.YES)
	@Column(nullable=true)
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

//	@SearchableProperty(store = Store.YES)
	@Column(length = 10000)
	public String getProductImageStore() {
		return productImageStore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="fk_goods_brand")
	public Brand getBrand() {
		return brand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="fk_goods_goods_type")
	public ProductType getProductType() {
		return productType;
	}

	@Column
	public String getAttributeMapStore() {
		return attributeMapStore;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="favoriteProductSet")
	@ForeignKey(name="fk_product_favorite_member_set")
	public Set<Members> getFavoriteMemberSet() {
		return favoriteMemberSet;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="favorProductSet")
	@ForeignKey(name="fk_product_favor_member_set")
	public Set<Members> getFavorMemberSet() {
		return favorMemberSet;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="hateProductSet")
	@ForeignKey(name="fk_product_hate_member_set")
	public Set<Members> getHateMemberSet() {
		return hateMemberSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@Cascade(value = { CascadeType.DELETE,CascadeType.PERSIST,CascadeType.REFRESH })
	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	public Set<DeliveryItem> getDeliveryItemSet() {
		return deliveryItemSet;
	}
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)	
	public Set<Farmland> getFarmland() {
		return farmland;
	}


	// 精度处理
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public void setFreezeStore(Integer freezeStore) {
		this.freezeStore = freezeStore;
	}
	public void setPoint(Integer point) {
		if (point == null || point < 0) {
			point = 0;
		}
		this.point = point;
	}
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	public void setProductImageStore(String productImageStore) {
		this.productImageStore = productImageStore;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public void setAttributeMapStore(String attributeMapStore) {
		this.attributeMapStore = attributeMapStore;
	}
	public void setFavoriteMemberSet(Set<Members> favoriteMemberSet) {
		this.favoriteMemberSet = favoriteMemberSet;
	}
	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}
	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFavorMemberSet(Set<Members> favorMemberSet) {
		this.favorMemberSet = favorMemberSet;
	}

	public void setHateMemberSet(Set<Members> hateMemberSet) {
		this.hateMemberSet = hateMemberSet;
	}

	// 精度处理
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public void setFarmland(Set<Farmland> farmland) {
		this.farmland = farmland;
	}

	public void setDeliveryItemSet(Set<DeliveryItem> deliveryItemSet) {
		this.deliveryItemSet = deliveryItemSet;
	}
	public void setIsLand(Boolean isLand) {
		this.isLand = isLand;
	}
	public void setStorePlace(String storePlace) {
		this.storePlace = storePlace;
	}
	public void setProductParameterValue(String productParameterValue) {
		this.productParameterValue = productParameterValue;
	}

//	private List<ProductImage> productImageList;
	
	// 获取商品图片
	@SuppressWarnings("unchecked")
	@Transient
	public List<ProductImage> getProductImageList() {
		if (StringUtils.isEmpty(productImageStore)) {
			return ListUtils.lazyList(new ArrayList<ProductImage>(), FactoryUtils.instantiateFactory(ProductImage.class));
		}
		List<ProductImage> resultProductImageList=JsonUtil.Json2List(productImageStore, new TypeReference<List<ProductImage>>(){});
		return resultProductImageList;
	}
	
	/**
	 * 设置商品图片
	 */
	@Transient
	public void setProductImageList(List<ProductImage> productImageList) {
		if (productImageList == null || productImageList.size() == 0) {
			productImageStore = null;
			return;
		}
		productImageStore=JsonUtil.toJson(productImageList);
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
	
	/**
	 * 商品是否缺货
	 */
	@Transient
	public boolean getIsOutOfStock() {
		if (store != null && freezeStore >= store) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取优惠价格，若member对象为null则返回原价格
	 */
	@Transient
	public BigDecimal getPreferentialPrice(Members member) {
		if (member != null) {
			BigDecimal preferentialPrice = price.multiply(new BigDecimal(member.getMemberRank().getPreferentialScale().toString()).divide(new BigDecimal(100)));
			return preferentialPrice;
		} else {
			return price;
		}
	}
	
	/**
	 * 辅助接受属性值
	 */
	private Map<String,String> goodsAttributeValueMap;
	
	@Transient
	public Map<String, String> getGoodsAttributeValueMap() {
//		goodsAttributeValueMap=JsonUtil.fromJson(attributeMapStore, HashMap.class);
		return goodsAttributeValueMap;
	}
	@Transient
	public void setGoodsAttributeValueMap(Map<String, String> goodsAttributeValueMap) {
		this.goodsAttributeValueMap = goodsAttributeValueMap;
	}
	
	/**
	 * 从属性字符串里面获得属性值
	 * @param attributeMap 数据库表字段
	 */
	@Transient
	public String getProductAttributeMapValue(String attributeMap) {
		if (StringUtils.isEmpty(attributeMapStore)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String,String> mapStore=JsonUtil.fromJson(attributeMapStore, HashMap.class);
		return mapStore.get(attributeMap);
	}	
	
	/**
	 * 辅助接受参数值
	 */
	private Map<String,String> goodsParameterValueMap;
	
	@Transient
	public Map<String, String> getGoodsParameterValueMap() {
		return goodsParameterValueMap;
	}
	@Transient
	public void setGoodsParameterValueMap(Map<String, String> goodsParameterValueMap) {
		this.goodsParameterValueMap = goodsParameterValueMap;
	}

	/**
	 * 从属性字符串里面获得属性值
	 * @param attributeMap 数据库表字段
	 */
	@Transient
	public String getProductParameterMapValue(String parameterKey) {
		if (StringUtils.isEmpty(productParameterValue)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String,String> mapStore=JsonUtil.fromJson(productParameterValue, HashMap.class);
		return mapStore.get(parameterKey);
	}		
	
	
	@Transient
	public void onSave()
	{
	    if ((this.price == null) || (this.price.compareTo(new BigDecimal(0)) < 0))
	      this.price = new BigDecimal(0);
	    if ((this.cost != null) && (this.cost.compareTo(new BigDecimal(0)) < 0))
	      this.cost = new BigDecimal(0);
	    if ((this.marketPrice == null) || (this.marketPrice.compareTo(new BigDecimal(0)) < 0))
	      this.marketPrice = this.price;
	    if ((this.weight == null) || (this.weight.intValue() < 0))
	      this.weight = Double.valueOf(0);
	    if ((this.point == null)  ||  (this.point.intValue() < 0))
	      this.point = Integer.valueOf(0);
	    if ((this.store != null) && (this.store.intValue() < 0))
	    	this.store = Integer.valueOf(0);
	    if ((this.freezeStore == null) || (this.freezeStore.intValue() < 0))
	      this.freezeStore = Integer.valueOf(0);
	    if (this.isMarketable == null)
	      this.isMarketable = Boolean.valueOf(false);
	    if (this.isBest == null)
	      this.isBest = Boolean.valueOf(false);
	    if (this.isNew == null)
	      this.isNew = Boolean.valueOf(false);
	    if (this.isHot == null)
	      this.isHot = Boolean.valueOf(false);
	    if (this.isLand == null)
	      this.isLand = Boolean.valueOf(false);
	    
	    this.freezeStore = Integer.valueOf(0);
	    if (StringUtils.isEmpty(this.productSn))
	      this.productSn = SerialNumberUtil.buildProductSn();
	}

	@Transient
	public void onUpdate()
	{
	    if ((this.price == null) || (this.price.compareTo(new BigDecimal(0)) < 0))
		      this.price = new BigDecimal(0);
		if ((this.cost != null) && (this.cost.compareTo(new BigDecimal(0)) < 0))
		      this.cost = new BigDecimal(0);
		if ((this.marketPrice == null) || (this.marketPrice.compareTo(new BigDecimal(0)) < 0))
		      this.marketPrice = this.price;
		if ((this.weight == null) || (this.weight.intValue() < 0))
		      this.weight = Double.valueOf(0);
		if ((this.point == null)  ||  (this.point.intValue() < 0))
		      this.point = Integer.valueOf(0);
		if ((this.store != null) && (this.store.intValue() < 0))
		    	this.store = Integer.valueOf(0);
		if ((this.freezeStore == null) || (this.freezeStore.intValue() < 0))
		      this.freezeStore = Integer.valueOf(0);
		if (this.isMarketable == null)
		      this.isMarketable = Boolean.valueOf(false);
		if (this.isBest == null)
		      this.isBest = Boolean.valueOf(false);
		if (this.isNew == null)
		      this.isNew = Boolean.valueOf(false);
		if (this.isHot == null)
		      this.isHot = Boolean.valueOf(false);
		if (this.isLand == null)
		      this.isLand = Boolean.valueOf(false);
		    
		this.freezeStore = Integer.valueOf(0);
		if (StringUtils.isEmpty(this.productSn))
		      this.productSn = SerialNumberUtil.buildProductSn();
	}
	
}
