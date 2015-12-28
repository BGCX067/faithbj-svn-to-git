package com.faithbj.custom.vegetable.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FarmingBlockDefaultImageDao;
import com.faithbj.custom.vegetable.entity.FarmingBlockDefaultImage;
import com.faithbj.shop.dao.impl.BaseDaoImpl;

/**
 * 
 * <p>耕种地块默认图片Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-03
 * @version 1.0
 */

@Repository("farmingBlockDefaultImageDao")
public class FarmingBlockDefaultImageDaoImpl extends BaseDaoImpl<FarmingBlockDefaultImage, String> implements FarmingBlockDefaultImageDao
{

}
