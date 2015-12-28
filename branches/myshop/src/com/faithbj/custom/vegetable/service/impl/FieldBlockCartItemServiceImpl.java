package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FieldBlockCartItemDao;
import com.faithbj.custom.vegetable.entity.FieldBlockCartItem;
import com.faithbj.custom.vegetable.service.FieldBlockCartItemService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>土地块购物车项Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Repository("fieldBlockCartItemService")
public class FieldBlockCartItemServiceImpl extends BaseServiceImpl<FieldBlockCartItem, String> implements FieldBlockCartItemService
{
	@Resource
	protected FieldBlockCartItemDao fieldBlockCartItemDao = null;

	@Resource
	public void setBaseDao(FieldBlockCartItemDao fieldBlockCartItemDao) {
		super.setBaseDao(fieldBlockCartItemDao);
	}
}
