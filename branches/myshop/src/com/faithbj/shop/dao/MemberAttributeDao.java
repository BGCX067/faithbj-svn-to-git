package com.faithbj.shop.dao;

import java.util.List;

import com.faithbj.shop.entity.MemberAttribute;

/**
 * Dao接口 - 会员属性
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface MemberAttributeDao extends BaseDao<MemberAttribute, String> {

	/**
	 * 获取已启用的会员注册项.
	 * 
	 * @return 已启用的会员注册项集合.
	 */
	public List<MemberAttribute> getEnabledMemberAttributeList();

}
