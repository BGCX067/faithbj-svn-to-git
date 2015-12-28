package com.faithbj.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.dao.DeliveryTypeDao;
import com.faithbj.shop.entity.DeliveryType;
import com.faithbj.shop.service.DeliveryTypeService;

import org.springframework.stereotype.Service;
import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

/**
 * Service实现类 - 配送方式
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class DeliveryTypeServiceImpl extends BaseServiceImpl<DeliveryType, String> implements DeliveryTypeService {

	@Resource
	DeliveryTypeDao deliveryTypeDao;
	
	@Resource
	public void setBaseDao(DeliveryTypeDao deliveryTypeDao) {
		super.setBaseDao(deliveryTypeDao);
	}
	
	@Override
	@Cacheable(modelId = "caching")
	public List<DeliveryType> getAll() {
		return deliveryTypeDao.getAll();
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(DeliveryType deliveryType) {
		deliveryTypeDao.delete(deliveryType);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String id) {
		deliveryTypeDao.delete(id);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String[] ids) {
		deliveryTypeDao.delete(ids);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public String save(DeliveryType deliveryType) {
		return deliveryTypeDao.save(deliveryType);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void update(DeliveryType deliveryType) {
		deliveryTypeDao.update(deliveryType);
	}

}
