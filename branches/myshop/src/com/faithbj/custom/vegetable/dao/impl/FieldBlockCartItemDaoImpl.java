package com.faithbj.custom.vegetable.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FieldBlockCartItemDao;
import com.faithbj.custom.vegetable.entity.FieldBlockCartItem;
import com.faithbj.shop.dao.impl.BaseDaoImpl;

/**
 * 
 * <p>土地块购物车项Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-02
 * @version 1.0
 */

@Repository("fieldBlockCartItemDao")
public class FieldBlockCartItemDaoImpl extends BaseDaoImpl<FieldBlockCartItem, String> implements FieldBlockCartItemDao
{

}
