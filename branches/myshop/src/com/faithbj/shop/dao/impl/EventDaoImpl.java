package com.faithbj.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.EventDao;
import com.faithbj.shop.entity.Event;

@Repository
public class EventDaoImpl extends BaseDaoImpl<Event, String> implements EventDao {

	@Override
	public void delete(String id) {
		Event event = load(id);
		delete(event);

	}

	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			Event event = load(id);
			delete(event);
		}
	}

}
