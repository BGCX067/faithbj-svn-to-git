package com.faithbj.shop.dao.impl;

import java.util.List;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.bean.Pager.OrderType;
import com.faithbj.shop.dao.NavigationDao;
import com.faithbj.shop.entity.Navigation;
import com.faithbj.shop.entity.Navigation.Position;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 导航
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class NavigationDaoImpl extends BaseDaoImpl<Navigation, String> implements NavigationDao {

	@SuppressWarnings("unchecked")
	public List<Navigation> getTopNavigationList() {
		String hql = "from Navigation as navigation where position = ? and isVisible = ? order by navigation.orderList asc";
		return getSession().createQuery(hql).setParameter(0, Position.top).setParameter(1, true).list();
	}

	@SuppressWarnings("unchecked")
	public List<Navigation> getMiddleNavigationList() {
		String hql = "from Navigation as navigation where position = ? and isVisible = ? order by navigation.orderList asc";
		return getSession().createQuery(hql).setParameter(0, Position.middle).setParameter(1, true).list();
	}

	@SuppressWarnings("unchecked")
	public List<Navigation> getBottomNavigationList() {
		String hql = "from Navigation as navigation where position = ? and isVisible = ? order by navigation.orderList asc";
		return getSession().createQuery(hql).setParameter(0, Position.bottom).setParameter(1, true).list();
	}
	
	// 根据orderList排序
	@SuppressWarnings("unchecked")
	@Override
	public List<Navigation> getAll() {
		String hql = "from Navigation navigation order by navigation.orderList asc navigation.createDate desc";
		return getSession().createQuery(hql).list();
	}

	// 根据orderList排序
	@Override
	@SuppressWarnings("unchecked")
	public List<Navigation> getList(String propertyName, Object value) {
		String hql = "from Navigation navigation where navigation." + propertyName + "=? order by navigation.orderList asc navigation.createDate desc";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}
	
	// 根据orderList排序
	@Override
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new Pager();
			pager.setOrderBy("orderList");
			pager.setOrderType(OrderType.asc);
		}
		return super.findByPager(pager, detachedCriteria);
	}

	// 根据orderList排序
	@Override
	public Pager findByPager(Pager pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Navigation.class);
		return this.findByPager(pager, detachedCriteria);
	}

}
