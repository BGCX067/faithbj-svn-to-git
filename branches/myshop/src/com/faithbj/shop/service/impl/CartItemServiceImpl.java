package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.CartItemDao;
import com.faithbj.shop.entity.CartItem;
import com.faithbj.shop.service.CartItemService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 品牌
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class CartItemServiceImpl extends BaseServiceImpl<CartItem, String> implements CartItemService {

	@Resource
	public void setBaseDao(CartItemDao cartItemDao) {
		super.setBaseDao(cartItemDao);
	}

}
