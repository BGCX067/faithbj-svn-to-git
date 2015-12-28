package com.faithbj.custom.vegetable.service;

import java.util.List;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.BaseService;

/**
 * 
 * <p>土地块Service</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

public interface FieldBlockService extends BaseService<FieldBlock, String>
{

	List<FieldBlock> getFieldBlockList();

	public Pager findFavoriteFieldBlockByPager(Member member, Pager pager);
	
}
