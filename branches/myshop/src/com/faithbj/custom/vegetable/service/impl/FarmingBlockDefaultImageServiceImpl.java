package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.dao.FarmingBlockDefaultImageDao;
import com.faithbj.custom.vegetable.entity.FarmingBlockDefaultImage;
import com.faithbj.custom.vegetable.service.FarmingBlockDefaultImageService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>耕种地块默认图片Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-02-03
 * @version 1.0
 */

@Repository("farmingBlockDefaultImageService")
public class FarmingBlockDefaultImageServiceImpl extends BaseServiceImpl<FarmingBlockDefaultImage, String> implements FarmingBlockDefaultImageService
{
	@Resource
	protected FarmingBlockDefaultImageDao farmingBlockDefaultImagDao = null;

	@Resource
	public void setBaseDao(FarmingBlockDefaultImageDao farmingBlockDefaultImagDao) {
		super.setBaseDao(farmingBlockDefaultImagDao);
	}
}
