package com.faithbj.custom.vegetable.service;

import com.faithbj.BaseSpringTest;
import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.entity.Product;

public class FieldBlockServiceTest extends BaseSpringTest
{
	protected FieldBlockService fieldBlockService = null;
	
	public void testCRUD()
	{
		FieldBlock fieldBlock = new FieldBlock();
		fieldBlock.setCode("code1");
		fieldBlock.setAddress("address1");
		
		this.fieldBlockService.save(fieldBlock);
		
	}
	
	public void testRetrieve()
	{
		FieldBlock fieldBlock = this.fieldBlockService.get("402881e83498080301349808269b0000");
		
		System.out.println(fieldBlock.getCode());
		System.out.println(fieldBlock.getAddress());
		
	}
	
	public void testFindFavoriteFieldBlockByPager()
	{
		Member member = new Member();
		member.setId("402881e4351fcc3701351fcd1e5c0000");
		Pager pager = new Pager();
		pager.setPageSize(10);
		pager = fieldBlockService.findFavoriteFieldBlockByPager(member, pager);
		
		System.out.println(pager.getList().size());
	}
	
}
