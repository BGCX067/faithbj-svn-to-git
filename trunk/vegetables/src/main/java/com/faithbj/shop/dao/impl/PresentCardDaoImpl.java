package com.faithbj.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.faithbj.shop.dao.PresentCardDao;
import com.faithbj.shop.model.entity.PresentCard;

@Repository("presentCardDao")
public class PresentCardDaoImpl extends BaseDaoImpl<PresentCard,String> implements PresentCardDao {

	@Override
	public Integer batchGenerateCards(final List<PresentCard> presentCards) {
		Integer count = 0;
		for(PresentCard presentCard:presentCards){
			getSession().save(presentCard);
			count ++;
			if(count%20000 == 0){
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
			
		return count;
	}
}