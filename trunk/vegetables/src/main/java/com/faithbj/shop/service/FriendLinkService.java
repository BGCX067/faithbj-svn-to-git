package com.faithbj.shop.service;

import java.util.List;

import com.faithbj.shop.model.entity.FriendLink;


/**
 * Service接口 - 友情链接
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface FriendLinkService extends BaseService<FriendLink, String> {

	/**
	 * 获取所有图片友情链接集合;
	 * 
	 * @return 图片友情链接集合
	 * 
	 */
	public List<FriendLink> getPictureFriendLinkList();
	
	/**
	 * 获取所有文字友情链接集合;
	 * 
	 * @return 图片友情链接集合
	 * 
	 */
	public List<FriendLink> getTextFriendLinkList();

}
