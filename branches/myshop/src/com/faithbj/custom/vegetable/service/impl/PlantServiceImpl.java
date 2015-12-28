package com.faithbj.custom.vegetable.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.dao.crud.BaseCRUDServiceImpl;
import com.faithbj.custom.dao.crud.CommonCRUDDao;
import com.faithbj.custom.vegetable.dao.PlantDAO;
import com.faithbj.custom.vegetable.entity.Plant;
import com.faithbj.custom.vegetable.service.PlantService;

/**
 * 
 * <p>耕种作物Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2011-12-31
 * @version 1.0
 */
@Deprecated
@Service("plantService")
public class PlantServiceImpl extends BaseCRUDServiceImpl<Plant> implements PlantService
{
	@Resource
	private PlantDAO plantDAO = null;

	@Override
	protected CommonCRUDDao<Plant> getDao()
	{
		return this.plantDAO;
	}
	

}
