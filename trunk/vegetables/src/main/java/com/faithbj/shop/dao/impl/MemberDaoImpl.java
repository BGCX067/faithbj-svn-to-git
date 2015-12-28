package com.faithbj.shop.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.MemberDao;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Order;

/**
 * Dao实现类 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Members, String> implements MemberDao {

	@SuppressWarnings("unchecked")
	public boolean isExistByUsername(String username) {
		String hql = "from Members members where lower(members.username) = lower(?)";
		Members member = (Members) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Members getMemberByUsername(String username) {
		String hql = "from Members members where lower(members.username) = lower(?)";
		return (Members) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}
	
	// 关联处理
	@Override
	public void delete(Members member) {
		Set<Order> orderSet = member.getOrderSet();
		if (orderSet != null) {
			for (Order order : orderSet) {
				order.setMember(null);
			}
		}
		super.delete(member);
	}

	// 关联处理
	@Override
	public void delete(String id) {
		Members member = load(id);
		this.delete(member);
	}

	// 关联处理
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			Members member = load(id);
			this.delete(member);
		}
	}

}
