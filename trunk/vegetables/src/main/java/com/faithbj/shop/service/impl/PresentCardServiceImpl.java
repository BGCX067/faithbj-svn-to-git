package com.faithbj.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faithbj.shop.dao.PresentCardDao;
import com.faithbj.shop.model.entity.PresentCard;
import com.faithbj.shop.model.entity.PresentCard.PresentCardStatus;
import com.faithbj.shop.model.entity.PresentCardCategory;
import com.faithbj.shop.service.PresentCardService;
import com.faithbj.shop.utils.CommonUtil;

@Service("presentCardService")
public class PresentCardServiceImpl extends BaseServiceImpl<PresentCard, String> implements PresentCardService {
	
	@Resource
	private PresentCardDao presentCardDao;
	
	@Resource
	public void setBaseDao(PresentCardDao presentCardDao) {
		super.setBaseDao(presentCardDao);
	}


	@Override
	@Transactional(readOnly=false)
	public Integer batchGenerateCards(PresentCardCategory cardCategory,Integer amount) {
		if(amount<=0)
			return 0;
		
		List<PresentCard> presentcards=new ArrayList<PresentCard>();
		
		while(presentcards.size()!=amount){
			PresentCard card=new PresentCard();
			card.setCategoryid(cardCategory);
			
			String presentNumber=CommonUtil.getRandomString(8).toUpperCase();
			String presentPassword=getUUID().substring(24).toUpperCase();
			
			card.setPresentNumber(presentNumber);
			card.setPresentPassword(presentPassword);
			card.setPresentStatus(PresentCardStatus.activated.ordinal());
			
			if(isNotDuplicated(presentPassword, presentPassword))
				presentcards.add(card);
		}
		
		presentCardDao.batchGenerateCards(presentcards);
		return amount;
	}
	
	public  String getUUID(){
		UUID uuid = UUID.randomUUID();   
		return uuid.toString();
	}
	
	@Transactional(readOnly=true)
	public boolean isNotDuplicated(String presentNumber,String presentPassword) {
		Long count=presentCardDao.count("from PresentCard where presentNumber=? and presentPassword=?", presentNumber,presentPassword);
		return (count==null||count==0)?true:false;
	}

	
	

}
