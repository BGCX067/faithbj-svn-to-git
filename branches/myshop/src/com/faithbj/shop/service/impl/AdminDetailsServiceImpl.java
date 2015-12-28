package com.faithbj.shop.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.faithbj.shop.bean.SystemConfig;
import com.faithbj.shop.dao.AdminDao;
import com.faithbj.shop.entity.Admin;
import com.faithbj.shop.entity.Role;
import com.faithbj.shop.util.SystemConfigUtil;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类 - 后台权限认证
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
@Transactional
public class AdminDetailsServiceImpl implements UserDetailsService {

	private static final long serialVersionUID = 2653636739190406891L;

	@Resource
	private AdminDao adminDao;

	public Admin loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Admin admin = adminDao.getAdminByUsername(username);
		if (admin == null) {
			throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
		}
		
		// 解除管理员账户锁定
		SystemConfig systemConfig = SystemConfigUtil.getSystemConfig();
		if (admin.getIsAccountLocked() == true) {
			
			System.out.println("locked");
			
			if (systemConfig.getIsLoginFailureLock() == true) {
				int loginFailureLockTime = systemConfig.getLoginFailureLockTime();
				if (loginFailureLockTime != 0) {
					Date lockedDate = admin.getLockedDate();
					Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					Date now = new Date();
					if (now.after(nonLockedTime)) {
						admin.setLoginFailureCount(0);
						admin.setIsAccountLocked(false);
						admin.setLockedDate(null);
						adminDao.update(admin);
					}
				}
			} else {
				admin.setLoginFailureCount(0);
				admin.setIsAccountLocked(false);
				admin.setLockedDate(null);
				adminDao.update(admin);
			}
		}
		admin.setAuthorities(getGrantedAuthorities(admin));
		
		return admin;
	}

	// 获得管理角色数组
	public GrantedAuthority[] getGrantedAuthorities(Admin admin) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : admin.getRoleSet()) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role.getValue()));
		}
		return grantedAuthorities.toArray(new GrantedAuthority[grantedAuthorities.size()]);
	}

}
