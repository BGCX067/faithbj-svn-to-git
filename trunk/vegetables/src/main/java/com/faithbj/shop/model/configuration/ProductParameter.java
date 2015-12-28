package com.faithbj.shop.model.configuration;

import com.faithbj.shop.utils.CommonUtil;

public class ProductParameter implements Comparable<ProductParameter>{
	
	  private String id = CommonUtil.getUUID();
	  private String name;
	  private Integer orderList;
	  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Override
	public int compareTo(ProductParameter param) {
		if (param.getOrderList() == null)
			return 1;
		if (getOrderList() == null)
			return -1;
		    return getOrderList().compareTo(param.getOrderList());
	}

	@Override
	public int hashCode() {
		return this.id == null ? System.identityHashCode(this) : (getClass().getName() + getId()).hashCode();
	}

	@Override
	public boolean equals(Object paramObject) {
	    if (paramObject == null)
	      return false;
	    if ((paramObject instanceof ProductParameter))
	    {
	    	ProductParameter localParameter = (ProductParameter)paramObject;
	      if ((getId() == null) || (localParameter.getId() == null))
	        return false;
	      return getId().equals(localParameter.getId());
	    }
	    return false;
	}
	 
}
