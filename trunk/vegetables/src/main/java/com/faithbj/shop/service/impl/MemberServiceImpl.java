package com.faithbj.shop.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.faithbj.shop.dao.MemberDao;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.utils.CommonUtil;

/**
 * Service实现类 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service
public class MemberServiceImpl extends BaseServiceImpl<Members, String> implements MemberService {

	@Resource
	private MemberDao memberDao;

	@Resource
	public void setBaseDao(MemberDao userDao) {
		super.setBaseDao(userDao);
	}
	
	public boolean isExistByUsername(String username) {
		return memberDao.isExistByUsername(username);
	}
	
	public Members getMemberByUsername(String username) {
		return memberDao.getMemberByUsername(username);
	}
	
	public boolean verifyMember(String username, String password) {
		Members member = getMemberByUsername(username);
		if (member != null && member.getPassword().equals(DigestUtils.md5Hex(password))) {
			return true;
		} else {
			return false;
		}
	}
	
	public String buildPasswordRecoverKey() {
		return System.currentTimeMillis() + Members.PASSWORD_RECOVER_KEY_SEPARATOR + CommonUtil.getUUID() + DigestUtils.md5Hex(CommonUtil.getRandomString(10));
	}
	
	public Date getPasswordRecoverKeyBuildDate(String passwordRecoverKey) {
		long time = Long.valueOf(StringUtils.substringBefore(passwordRecoverKey, Members.PASSWORD_RECOVER_KEY_SEPARATOR));
		return new Date(time);
	}

}
