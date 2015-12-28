package com.faithbj.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.MessageDao;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Message;
import com.faithbj.shop.service.MessageService;

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
	
	public Pager getMemberInboxPager(Members member, Pager pager) {
		return messageDao.getMemberInboxPager(member, pager);
	}
	
	public Pager getMemberOutboxPager(Members member, Pager pager) {
		return messageDao.getMemberOutboxPager(member, pager);
	}
	
	public Pager getMemberDraftboxPager(Members member, Pager pager) {
		return messageDao.getMemberDraftboxPager(member, pager);
	}
	
	public Pager getAdminInboxPager(Pager pager) {
		return messageDao.getAdminInboxPager(pager);
	}
	
	public Pager getAdminOutboxPager(Pager pager) {
		return messageDao.getAdminOutboxPager(pager);
	}
	
	public Long getUnreadMessageCount(Members member) {
		return messageDao.getUnreadMessageCount(member);
	}
	
	public Long getUnreadMessageCount() {
		return messageDao.getUnreadMessageCount();
	}

}
