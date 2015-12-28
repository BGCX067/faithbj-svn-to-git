package com.faithbj.shop.model.configuration;

import java.util.List;

import com.faithbj.shop.model.entity.ProductAttribute;

public class AcessoryAtrributeAndParameter {
	private List<ProductAttribute> productAttributeList;
	private List<ProductParameter> productParameterList;

	public List<ProductAttribute> getProductAttributeList() {
		return productAttributeList;
	}

	public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
		this.productAttributeList = productAttributeList;
	}

	public List<ProductParameter> getProductParameterList() {
		return productParameterList;
	}

	public void setProductParameterList(List<ProductParameter> productParameterList) {
		this.productParameterList = productParameterList;
	}
	

}
