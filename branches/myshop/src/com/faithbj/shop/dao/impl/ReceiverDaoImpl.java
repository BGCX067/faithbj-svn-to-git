package com.faithbj.shop.dao.impl;

import java.util.List;

import com.faithbj.shop.dao.ReceiverDao;
import com.faithbj.shop.entity.Receiver;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 收货地址
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class ReceiverDaoImpl extends BaseDaoImpl<Receiver, String> implements ReceiverDao {

	// 重写方法，保存时若对象isDefault=true，则设置其它对象isDefault值为false
	@Override
	@SuppressWarnings("unchecked")
	public String save(Receiver receiver) {
		if (receiver.getIsDefault()) {
			String hql = "from Receiver receiver where receiver.member = ? and receiver.isDefault = ?";
			List<Receiver> receiverList = getSession().createQuery(hql).setParameter(0, receiver.getMember()).setParameter(1, true).list();
			if (receiverList != null) {
				for (Receiver r : receiverList) {
					r.setIsDefault(false);
				}
			}
		}
		return super.save(receiver);
	}

	// 重写方法，更新时若对象isDefault=true，则设置其它对象isDefault值为false
	@Override
	@SuppressWarnings("unchecked")
	public void update(Receiver receiver) {
		if (receiver.getIsDefault()) {
			String hql = "from Receiver receiver where receiver.member = ? and receiver.isDefault = ? and receiver != ?";
			List<Receiver> receiverList = getSession().createQuery(hql).setParameter(0, receiver.getMember()).setParameter(1, true).setParameter(2, receiver).list();
			if (receiverList != null) {
				for (Receiver r : receiverList) {
					r.setIsDefault(false);
				}
			}
		}
		super.update(receiver);
	}

}
