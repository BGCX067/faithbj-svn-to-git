package com.faithbj.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import net.sf.json.JSONArray;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ForeignKey;

import com.faithbj.shop.utils.JsonUtil;

/**
 * 实体类 - 商品属性
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Entity
@JsonIgnoreProperties({"JavassistLazyInitializer", "handler", "createDate", "modifyDate", "optionStore", "productType"})
//@Table(
//	uniqueConstraints = {
//		@UniqueConstraint(columnNames = {"name", "productType_id"})
//	}
//)
public class ProductAttribute extends BaseEntity {

	private static final long serialVersionUID = 2989102568413246570L;


	private String name;// 属性名称
	private AttributeType attributeType;// 属性类型
	private Integer orderList;// 排序
	/**
	 * 可选项储存
	 * 如有效像素有：1000万以下,1000-1199万,1200-1599万,1600万以上
	 */
	private String optionStore;
	
	private ProductType productType;// 商品类型
	
	public ProductAttribute(){
		
	}
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	
	@Enumerated
	@Column(nullable=false, updatable=false)
	public AttributeType getAttributeType() {
		return attributeType;
	}
	
	@Column(nullable = false)	
	public Integer getOrderList() {
		return orderList;
	}

	@Column(nullable = false)
	public String getOptionStore() {
		return optionStore;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@ForeignKey(name="fk_product_attribute_product_type")
	public ProductType getProductType() {
		return productType;
	}

	/**
	 * 获取可选项List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transient
    public List<String> getOptionList(){
		if (StringUtils.isEmpty(optionStore)) {
			return ListUtils.lazyList(new ArrayList<String>(), FactoryUtils.instantiateFactory(String.class));
		}
		List<String> optionlist= JsonUtil.fromJson(this.optionStore,List.class);
		return optionlist;
	}
	

	
	// 设置可选项
	@Transient
	public void setOptionList(List<String> attributeOptionList) {
		if (attributeOptionList == null || attributeOptionList.size() == 0) {
			optionStore = null;
			return;
		}
		JSONArray jsonArray = JSONArray.fromObject(attributeOptionList);
		optionStore = jsonArray.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public String getOptionText() {
		 if (StringUtils.isEmpty(this.optionStore))
		      return null;
		 List<String> localList = (List<String>)JsonUtil.fromJson(this.optionStore, List.class);
		 return StringUtils.join(localList, ",");
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}
	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}
	public void setOptionStore(String optionStore) {
		this.optionStore = optionStore;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	
	@Transient
	public void setOptionText(String optionText) {
		if (StringUtils.isEmpty(optionText))
	    {
	      this.optionStore = null;
	      return;
	    }
	    this.optionStore = JsonUtil.toJson(optionText.split(","));
	}
	

	

}
