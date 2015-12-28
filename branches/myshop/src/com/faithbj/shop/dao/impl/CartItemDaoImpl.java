package com.faithbj.shop.dao.impl;

import com.faithbj.shop.dao.CartItemDao;
import com.faithbj.shop.entity.CartItem;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 购物车项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class CartItemDaoImpl extends BaseDaoImpl<CartItem, String> implements CartItemDao {

	// 重写方法，若保存对象的member、product属性值相同，则只更新已有对象的quantity属性值
	@Override
	public String save(CartItem cartItem) {
		String hql = "from CartItem cartItem where cartItem.member = ? and cartItem.product = ?";
		CartItem persistent = (CartItem) getSession().createQuery(hql).setParameter(0, cartItem.getMember()).setParameter(1, cartItem.getProduct()).uniqueResult();
		if (persistent == null) {
			return super.save(cartItem);
		} else {
			persistent.setQuantity(persistent.getQuantity() + cartItem.getQuantity());
			super.update(persistent);
			return persistent.getId();
		}
	}

}
