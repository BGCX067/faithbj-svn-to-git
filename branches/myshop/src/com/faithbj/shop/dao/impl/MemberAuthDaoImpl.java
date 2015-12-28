package com.faithbj.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.faithbj.custom.vegetable.entity.MemberAuth;
import com.faithbj.shop.dao.MemberAuthDao;
import com.faithbj.shop.entity.Member;

/**
 * 
 * <p>蔬菜会员资格Dao实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-02
 * @version 1.0
 */

@Repository("memberAuthDao")
public class MemberAuthDaoImpl extends BaseDaoImpl<MemberAuth, String> implements MemberAuthDao
{

}
