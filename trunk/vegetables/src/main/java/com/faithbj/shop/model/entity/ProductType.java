package com.faithbj.shop.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;

import com.faithbj.shop.model.configuration.ProductParameter;
import com.faithbj.shop.utils.JsonUtil;

/**
 * 实体类 - 商品类型
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
public class ProductType extends BaseEntity {

	private static final long serialVersionUID = -6173231303962800416L;
	/**
	 * 商品类型名称
	 */
	private String name;
	/**
	 * 商品属性
	 */
	private Set<ProductAttribute> productAttributeSet;
	/**
	 * 商品参数
	 */
	private String productParameterStore;
	/**
	 * 一种类型可以有多个商品
	 */
	private Set<Product> productSet;	
	
	
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="productType", cascade={javax.persistence.CascadeType.ALL})
	@OrderBy( "orderList asc")
	public Set<ProductAttribute> getProductAttributeSet() {
		return productAttributeSet;
	}
	
	@Column(length=3000)
	public String getProductParameterStore() {
		return productParameterStore;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
	public Set<Product> getProductSet() {
		return productSet;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public List<ProductParameter> getProductParameters()
	{                             
      if (StringUtils.isEmpty(this.productParameterStore))
         return ListUtils.lazyList(new ArrayList<ProductParameter>(), FactoryUtils.instantiateFactory(ProductParameter.class));
      try
      {
	    return JsonUtil.Json2List(this.productParameterStore,new TypeReference<List<ProductParameter>>() {});
//	    return JsonUtil.getMapper().readValue(this.productParameterStore,new TypeReference<List<ProductParameter>>() {});
	  }
	  catch (Exception localException)
	  {
	  }
	  return null;
	}	
	
//	@Transient
//    public List<ProductAttribute> getProductAttributes()
//	{
//		if (null==this.productAttributeSet)
//			return null;
//		    
//		return  new ArrayList<ProductAttribute>(productAttributeSet);
//	}
	

	public void setName(String name) {
		this.name = name;
	}

	public void setProductAttributeSet(Set<ProductAttribute> productAttributeSet) {
		this.productAttributeSet = productAttributeSet;
	}

	public void setProductParameterStore(String productParameterStore) {
		this.productParameterStore = productParameterStore;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}
	
	@Transient
	public void setProductParameters(List<ProductParameter> paramList)
	{
	   if ((paramList == null) || (paramList.size() == 0))
	   {
	     this.productParameterStore = null;
	     return;
	   }
       Collections.sort(paramList);
       this.productParameterStore = JsonUtil.toJson(paramList);
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

/*	@ManyToMany(fetch=FetchType.LAZY)
@OrderBy("name asc")
@ForeignKey(name="fk_goods_type_brand_set")
public Set<Brand> getBrands() {
	return brands;
}
private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

private Set<Brand> brands = new HashSet<Brand>();
public void setBrands(Set<Brand> brands) {
this.brands = brands;
}*/
