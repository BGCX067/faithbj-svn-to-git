package com.faithbj.custom.vegetable.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faithbj.custom.util.LoggerUtil;
import com.faithbj.custom.vegetable.dao.FieldBlockDao;
import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.service.FieldBlockService;
import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.HtmlService;
import com.faithbj.shop.service.impl.BaseServiceImpl;

/**
 * 
 * <p>土地块Service实现类</p> 
 * 
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	Barney Woo
 * @date 	2012-01-01
 * @version 1.0
 */

@Service("fieldBlockService")
public class FieldBlockServiceImpl extends BaseServiceImpl<FieldBlock, String> implements FieldBlockService
{
	@Resource
	protected FieldBlockDao fieldBlockDao = null;

	@Resource 
	protected HtmlService htmlService;
	@Resource
	public void setBaseDao(FieldBlockDao fieldBlockDao) {
		super.setBaseDao(fieldBlockDao);
	}
	
	@Override
	public List<FieldBlock> getFieldBlockList() {
		return fieldBlockDao.getFieldBlockList();
	}
	
	public Pager findFavoriteFieldBlockByPager(Member member, Pager pager)
	{
		return fieldBlockDao.findFavoriteFieldBlockByPager(member, pager);
	}
	
	@Override
	public String save(FieldBlock fieldBlock) {
		String id = fieldBlockDao.save(fieldBlock);
		fieldBlockDao.flush();
		fieldBlockDao.evict(fieldBlock);
		fieldBlock = fieldBlockDao.load(id);
		return id;
	}

	@Override
	public void update(FieldBlock fieldBlock) {
		String id = fieldBlock.getId();
		fieldBlockDao.update(fieldBlock);
		fieldBlockDao.flush();
		fieldBlockDao.evict(fieldBlock);
		fieldBlock = fieldBlockDao.load(id);
	}
	
	protected void info(String msg)
	{
		LoggerUtil.fieldInfo(msg);
	}
}
