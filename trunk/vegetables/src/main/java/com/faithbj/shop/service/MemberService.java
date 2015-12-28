package com.faithbj.shop.service;

import java.util.Date;

import com.faithbj.shop.model.entity.Members;

/**
 * Service接口 - 会员
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface MemberService extends BaseService<Members, String> {
	
	/**
	 * 根据用户名判断此用户是否存在（不区分大小写）
	 * 
	 */
	public boolean isExistByUsername(String username);
	
	/**
	 * 根据用户名获取会员对象，若会员不存在，则返回null（不区分大小写）
	 * 
	 */
	public Members getMemberByUsername(String username);
	
	/**
	 * 根据用户名、密码验证会员
	 * 
	 * @param username
	 *            用户名
	 *            
	 * @param password
	 *            密码
	 * 
	 * @return 验证是否通过
	 */
	public boolean verifyMember(String username, String password);
	
	/**
	 * 生成密码找回Key
	 * 
	 * @return 密码找回Key
	 */
	public String buildPasswordRecoverKey();
	
	/**
	 * 根据密码找回Key获取生成日期
	 * 
	 * @return 生成日期
	 */
	public Date getPasswordRecoverKeyBuildDate(String passwordRecoverKey);

}
