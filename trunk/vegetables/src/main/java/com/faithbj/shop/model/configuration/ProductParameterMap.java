package com.faithbj.shop.model.configuration;

import java.util.Map;

import org.codehaus.jackson.type.TypeReference;

import com.faithbj.shop.model.entity.Product;

@Deprecated
public class ProductParameterMap extends TypeReference<Map<String, String>> {
	public ProductParameterMap(Product paramProduct){}

}
