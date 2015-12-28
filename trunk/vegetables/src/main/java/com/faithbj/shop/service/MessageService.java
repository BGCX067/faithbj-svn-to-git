package com.faithbj.shop.service;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Message;

/**
 * Service接口 - 消息
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface MessageService extends BaseService<Message, String>{
	
	/**
	 * 根据Member、Pager获取会员收件箱分页对象
	 * 
	 * @param member
	 *            Member对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 收件箱分页对象
	 */
	public Pager getMemberInboxPager(Members member, Pager pager);

	/**
	 * 根据Member、Pager获取会员发件箱分页对象
	 * 
	 * @param member
	 *            Member对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 发件箱页对象
	 */
	public Pager getMemberOutboxPager(Members member, Pager pager);
	
	/**
	 * 根据Member、Pager获取会员草稿箱分页对象
	 * 
	 * @param member
	 *            Member对象
	 * 
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 草稿箱分页对象
	 */
	public Pager getMemberDraftboxPager(Members member, Pager pager);
	
	/**
	 * 根据Pager获取管理员收件箱消息分页对象
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return 收件箱分页对象
	 */
	public Pager getAdminInboxPager(Pager pager);
	
	/**
	 * 根据Pager获取管理员发件箱消息分页对象
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return 发件箱分页对象
	 */
	public Pager getAdminOutboxPager(Pager pager);
	
	/**
	 * 根据Member获取未读消息数量
	 * 
	 * @param member
	 *            Member对象
	 * 
	 * @return 未读消息数量
	 */
	public Long getUnreadMessageCount(Members member);
	
	/**
	 * 获取管理员未读消息数
	 * 
	 * @return 未读消息数量
	 */
	public Long getUnreadMessageCount();
	
}
