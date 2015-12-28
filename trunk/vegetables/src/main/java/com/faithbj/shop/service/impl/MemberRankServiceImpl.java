package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.MemberRankDao;
import com.faithbj.shop.model.entity.MemberRank;
import com.faithbj.shop.service.MemberRankService;

/**
 * Service实现类 - 会员分类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class MemberRankServiceImpl extends BaseServiceImpl<MemberRank, String> implements MemberRankService {
	
	@Resource
	MemberRankDao memberRankDao;

	@Resource
	public void setBaseDao(MemberRankDao memberRankDao) {
		super.setBaseDao(memberRankDao);
	}
	
//	@Cacheable(modelId = "caching")
	public MemberRank getDefaultMemberRank() {
		MemberRank defaultMemberRank = memberRankDao.getDefaultMemberRank();
		if (defaultMemberRank != null) {
			Hibernate.initialize(defaultMemberRank);
		}
		return defaultMemberRank;
	}
	
	public MemberRank getMemberRankByPoint(Integer point) {
		return memberRankDao.getMemberRankByPoint(point);
	}
	
	public MemberRank getUpMemberRankByPoint(Integer point) {
		return memberRankDao.getUpMemberRankByPoint(point);
	}

	@Override
//	@CacheFlush(modelId = "flushing")
	public void delete(MemberRank memberRank) {
		memberRankDao.delete(memberRank);
	}

	@Override
//	@CacheFlush(modelId = "flushing")
	public void delete(String id) {
		memberRankDao.delete(id);
	}

	@Override
//	@CacheFlush(modelId = "flushing")
	public void delete(String[] ids) {
		memberRankDao.delete(ids);
	}

	@Override
//	@CacheFlush(modelId = "flushing")
	public String save(MemberRank memberRank) {
		return memberRankDao.save(memberRank);
	}

	@Override
//	@CacheFlush(modelId = "flushing")
	public void update(MemberRank memberRank) {
		memberRankDao.update(memberRank);
	}

}
