package com.faithbj.custom.vegetable.dao;

import java.util.List;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.dao.BaseDao;
import com.faithbj.shop.entity.Member;

/**
 * 
 * <p>土地块Dao</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

public interface FieldBlockDao extends BaseDao<FieldBlock, String>
{

	List<FieldBlock> getFieldBlockList();
	
	public Pager findFavoriteFieldBlockByPager(Member member, Pager pager);

}
