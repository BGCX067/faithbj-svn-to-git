package com.faithbj.shop.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.EventDao;
import com.faithbj.shop.entity.Event;
import com.faithbj.shop.service.EventService;
@Service
public class EventServiceImpl extends BaseServiceImpl<Event, String> implements
		EventService {

	@Resource
	private EventDao eventDao;
	@Resource
	public void setBaseDao(EventDao eventDao) {
		super.setBaseDao(eventDao);
	}
	@Override
	public void delete(String id) {
		eventDao.delete(id);
	}
	@Override
	public void delete(String[] ids) {
		eventDao.delete(ids);
	}
}
