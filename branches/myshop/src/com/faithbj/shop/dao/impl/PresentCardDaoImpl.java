package com.faithbj.shop.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.PresentCardDao;
import com.faithbj.shop.entity.Event;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.PresentCard;
import com.faithbj.shop.util.CommonUtil;

@Repository
public class PresentCardDaoImpl extends BaseDaoImpl<PresentCard,String> implements PresentCardDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	private Event event;
	

	/**
	 * 得到用户礼品卡列表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getUserCardList(String mid) {
		String sql = "select e.eventname,p.userNum,e.account,e.createdate,e.expiredate,p.activate,p.id " +
							"from presentcard p left join event e on p.eventid = e.id where p.memberid = '" + mid +"'";
		List list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 激活礼品卡码
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateUserCardNum(String memberid,int pid) {
		List<PresentCard> list = getSession().createCriteria(PresentCard.class).add(Restrictions.eq("id", new Integer(pid))).list();
		String eventid = list.get(0).getEventid();
		List<Event> eventlist = getSession().createCriteria(Event.class).add(Restrictions.eq("id", new String(eventid))).list();
		BigDecimal account = eventlist.get(0).getAccount();
		if(0 == list.get(0).getActivate() && memberid.equals(list.get(0).getMemberid())){
			List<Member> memberList = getSession().createCriteria(Member.class).add(Restrictions.eq("id", memberid)).list();
			BigDecimal originDeposit = memberList.get(0).getDeposit();
			String sql = "update presentcard set activate = 1 where id = " +  pid  ;
			getSession().createSQLQuery(sql).executeUpdate();
			String charge = "update member set deposit = " + (originDeposit.add(account)) + " where id ='" + memberid + "'";
			getSession().createSQLQuery(charge).executeUpdate();
		}
	}
	

	
	/**
	 * 下发礼品卡
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void savereleaseCard(String[] memberids,String id) {
		//下发到用户账户时礼品卡状态为未激活
		int activate = 0;
		List<Event> eventlist = getSession().createCriteria(Event.class).add(Restrictions.eq("id", id)).list();
		String create = sdf.format(eventlist.get(0).getCreateDate());
		String expire = sdf.format(eventlist.get(0).getExpiredate());
		
		for(String mid:memberids){
			String cardnum = CommonUtil.getRandomString(20);
			String sql = "insert into presentcard(eventid,memberid,userNum,activate,createDate,expireDate) values("
					+ "'"+id + "' , '" + mid + "' , '"
							+ cardnum + "' , " +  activate +",'"+ create + "','" + expire  + "' )";
			getSession().createSQLQuery(sql).executeUpdate();
		}
	}
	
	
	/**
	 * 保存促销送礼品卡活动
	 */
	@Override
	public void saveCard(Event event) {
		getSession().save(event);
		
	}
	
	
	/**
	 * 获得优惠活动列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventList() {
		List<Event> eventList = getSession().createQuery("from Event").list();
		return eventList;
	}

	/**
	 * 根据条件查询用户
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Member> getUserList(String username) {
		String sql = "select * from member where 1=1";
		if(!(username == null || username.equals(""))){
			sql +=" and username =" + username;
		}
		List<Member> memberList = getSession().createSQLQuery(sql).list();
		return null;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
