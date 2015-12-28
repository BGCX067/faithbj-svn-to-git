package com.faithbj.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.PresentCardDao;
import com.faithbj.shop.entity.Event;
import com.faithbj.shop.entity.PresentCard;
import com.faithbj.shop.service.PresentCardService;

@Service
public class PresentCardServiceImpl extends BaseServiceImpl<PresentCard, String> implements
		PresentCardService {
	private List<Event> list;
	@Resource
	private PresentCardDao cardDao;
	
	@Resource
	public void setBaseDao(PresentCardDao cardDao) {
		super.setBaseDao(cardDao);
	}
	
	public List getUserCardList(String mid) {
		return cardDao.getUserCardList(mid);

	}
	
	public void updateUserCardNum(String mid,int pid) {
		cardDao.updateUserCardNum(mid,pid);
	}

	@Override
	public void saveCard(Event event) {
		cardDao.saveCard(event);
	}

	@Override
	public void savereleaseCard(String[] memberids, String id) {
		cardDao.savereleaseCard(memberids, id);		
	}

	@Override
	public List<Event> getEventList() {
		list =  cardDao.getEventList();
		return list;
	}
	

}
