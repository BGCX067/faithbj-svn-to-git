package com.faithbj.shop.service;

import java.util.List;

import com.faithbj.shop.entity.Event;
import com.faithbj.shop.entity.PresentCard;

public interface PresentCardService extends BaseService<PresentCard, String> {
	//获得礼品卡
	public List getUserCardList(String mid);
	//激活用户礼品卡
	public void updateUserCardNum(String mid,int id);
	//保存优惠活动
	public void saveCard(Event event);
	//下发
	public void savereleaseCard(String[] memberids,String id) ;
	//获得优惠活动列表
	public List<Event> getEventList();
	
}
