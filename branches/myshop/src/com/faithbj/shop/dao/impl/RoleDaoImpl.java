package com.faithbj.shop.dao.impl;

import com.faithbj.shop.dao.RoleDao;
import com.faithbj.shop.entity.Role;

import org.springframework.stereotype.Repository;

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
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements RoleDao {

	// 忽略isSystem=true的对象
	@Override
	public void delete(Role role) {
		if (role.getIsSystem()) {
			return;
		}
		super.delete(role);
	}

	// 忽略isSystem=true的对象
	@Override
	public void delete(String id) {
		Role role = load(id);
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
	@Override
	public String save(Role role) {
		role.setIsSystem(false);
		return super.save(role);
	}

	// 忽略isSystem=true的对象
	@Override
	public void update(Role role) {
		if (role.getIsSystem()) {
			return;
		}
		super.update(role);
	}

}
