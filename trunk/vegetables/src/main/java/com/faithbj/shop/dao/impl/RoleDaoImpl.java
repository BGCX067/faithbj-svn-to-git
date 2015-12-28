package com.faithbj.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.RoleDao;
import com.faithbj.shop.model.entity.Roles;

/**
 * Dao实现类 - 角色
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Roles, String> implements RoleDao {

	// 忽略isSystem=true的对象
	@Override
	public void delete(Roles role) {
		if (role.getIsSystem()) {
			return;
		}
		super.delete(role);
	}

	// 忽略isSystem=true的对象
	@Override
	public void delete(String id) {
		Roles role = load(id);
		this.delete(role);
	}

	// 忽略isSystem=true的对象
	@Override
	public void delete(String[] ids) {
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				this.delete(id);
			}
		}
	}

	// 设置isSystem=false
/*	@Override
	public String save(Roles role) {
		role.setIsSystem(false);
		return super.save(role);
	}*/

	// 忽略isSystem=true的对象
	@Override
	public void update(Roles role) {
		if (role.getIsSystem()) {
			return;
		}
		super.update(role);
	}

}
