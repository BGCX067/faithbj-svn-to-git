package com.faithbj.shop.service;

import com.faithbj.BaseSpringHibernateTest;
import com.faithbj.shop.entity.Member;

public class MemberServiceTest extends BaseSpringHibernateTest
{
	protected MemberService memberServiceImpl = null;
	
	public void testLoadFieldBlockCartItem()
	{
		Member member = this.memberServiceImpl.load("4028bcec3487e0a7013487e32c6e0000");
		
		System.out.println(member.getFieldBlockCartItemSet().size());
		
	}
}
