package com.faithbj.shop.dao;

import java.util.List;

import com.faithbj.shop.entity.Event;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.PresentCard;

public interface PresentCardDao extends BaseDao<PresentCard, String> {
	
		public List getUserCardList(String mid);
		
		public void updateUserCardNum(String userName,int id);
		
		public void saveCard(Event event);
		
		public List<Event> getEventList();
		
		public List<Member> getUserList(String username);
		
		public void savereleaseCard(String[] memberids,String ids) ;
		
}
