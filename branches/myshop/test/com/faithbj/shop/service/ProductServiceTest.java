package com.faithbj.shop.service;

import java.util.List;

import com.faithbj.BaseSpringTest;
import com.faithbj.shop.entity.Product;

public class ProductServiceTest extends BaseSpringTest
{
	protected ProductService productServiceImpl = null;
	
	public void testSelect()
	{
		List<Product> productList = this.productServiceImpl.getAll();
		
		System.out.println(productList.size());
	}
}
