package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.dao.AgreementDao;
import com.faithbj.shop.entity.Agreement;
import com.faithbj.shop.service.AgreementService;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

/**
 * Service实现类 - 会员注册协议
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class AgreementServiceImpl extends BaseServiceImpl<Agreement, String> implements AgreementService {

	@Resource
	private AgreementDao agreementDao;

	@Resource
	public void setBaseDao(AgreementDao agreementDao) {
		super.setBaseDao(agreementDao);
	}

	@Cacheable(modelId = "caching")
	public Agreement getAgreement() {
		Agreement agreement = agreementDao.getAgreement();
		Hibernate.initialize(agreement);
		return agreement;
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(Agreement entity) {
		agreementDao.delete(entity);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String id) {
		agreementDao.delete(id);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void delete(String[] ids) {
		agreementDao.delete(ids);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public String save(Agreement entity) {
		return agreementDao.save(entity);
	}

	@Override
	@CacheFlush(modelId = "flushing")
	public void update(Agreement entity) {
		agreementDao.update(entity);
	}

}
