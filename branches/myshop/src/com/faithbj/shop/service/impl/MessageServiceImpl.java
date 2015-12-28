package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.dao.MessageDao;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Message;
import com.faithbj.shop.service.MessageService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 消息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, String> implements MessageService {
	
	@Resource
	private MessageDao messageDao;
	
	@Resource
	public void setBaseDao(MessageDao messageDao) {
		super.setBaseDao(messageDao);
	}
	
	public Pager getMemberInboxPager(Member member, Pager pager) {
		return messageDao.getMemberInboxPager(member, pager);
	}
	
	public Pager getMemberOutboxPager(Member member, Pager pager) {
		return messageDao.getMemberOutboxPager(member, pager);
	}
	
	public Pager getMemberDraftboxPager(Member member, Pager pager) {
		return messageDao.getMemberDraftboxPager(member, pager);
	}
	
	public Pager getAdminInboxPager(Pager pager) {
		return messageDao.getAdminInboxPager(pager);
	}
	
	public Pager getAdminOutboxPager(Pager pager) {
		return messageDao.getAdminOutboxPager(pager);
	}
	
	public Long getUnreadMessageCount(Member member) {
		return messageDao.getUnreadMessageCount(member);
	}
	
	public Long getUnreadMessageCount() {
		return messageDao.getUnreadMessageCount();
	}

}
