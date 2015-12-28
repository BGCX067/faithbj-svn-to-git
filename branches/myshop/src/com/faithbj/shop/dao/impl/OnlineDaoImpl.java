package com.faithbj.shop.dao.impl;

import java.util.List;

import com.faithbj.shop.dao.OnlineDao;
import com.faithbj.shop.entity.Online;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 在线问答
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository("onlineDao")
public class OnlineDaoImpl extends BaseDaoImpl<Online, String> implements OnlineDao {
	
	@SuppressWarnings("unchecked")
	public List<Online> getAllQ() {
		//查询所有已经回答了的问题
		String hql = "from  Online as o where o.status='1' " ;
		return getSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Online> getAllA() {
		//查询所有未回答的问题
		String hql = "from  Online as o where o.status='0' " ;
		return getSession().createQuery(hql).list();
	}
	
	
}
