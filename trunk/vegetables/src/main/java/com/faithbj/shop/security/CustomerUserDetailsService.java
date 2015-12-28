package com.faithbj.shop.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faithbj.shop.dao.MemberDao;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Roles;
import com.faithbj.shop.utils.SystemConfigUtil;



/**
 * User: yfxue
 * Date: 12-02-19
 * Time: 上午07:39
 */
@Service("customerUserDetailsService")
@Transactional
public class CustomerUserDetailsService implements UserDetailsService {

    protected static final Logger log = LoggerFactory.getLogger(CustomerUserDetailsService.class);

    @Resource
	private MemberDao memberDao;
    @Resource
    private SystemConfigUtil systemConfigUtil;
    

	public Members loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Members member = memberDao.getMemberByUsername(username);
		if (member == null) {
			throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
		}
		
		// 解除管理员账户锁定
		if (member.isAccountNonLocked() == true) {
			
			System.out.println("locked");
			
			if (systemConfigUtil.getIsLoginFailureLock() == true) {
				int loginFailureLockTime = systemConfigUtil.getLoginFailureLockTime();
				if (loginFailureLockTime != 0) {
					Date lockedDate = member.getLockedDate();
					Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					Date now = new Date();
					if (now.after(nonLockedTime)) {
						member.setLoginFailureCount(0);
						member.setAccountNonLocked(true);
						member.setLockedDate(null);
						memberDao.update(member);
					}
				}
			} else {
				member.setLoginFailureCount(0);
				member.setAccountNonLocked(true);
				member.setLockedDate(null);
				memberDao.update(member);
			}
		}
		member.setAuthorities(getGrantedAuthorities(member));
		
		return member;
	}
    
	// 获得管理角色数组
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(Members member) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Roles role : member.getRoleSet()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getValue()));
		}
		return grantedAuthorities;
//		return grantedAuthorities.toArray(new GrantedAuthority[grantedAuthorities.size()]);
	}
}
