package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.faithbj.shop.dao.PresentCardCategoryDao;
import com.faithbj.shop.dao.impl.PresentCardCategoryDaoImpl;
import com.faithbj.shop.model.entity.PresentCardCategory;
import com.faithbj.shop.service.PresentCardCategoryService;

@Service("presentCardCategoryService")
public class PresentCardCategoryServiceImpl extends BaseServiceImpl<PresentCardCategory, String> implements PresentCardCategoryService {
	
	@Resource
	private PresentCardCategoryDaoImpl presentCardCategoryDao;
	
	@Resource
	public void setBaseDao(PresentCardCategoryDao presentCardCategoryDao) {
		super.setBaseDao(presentCardCategoryDao);
	}
}
