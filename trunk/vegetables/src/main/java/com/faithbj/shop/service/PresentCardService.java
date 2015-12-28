package com.faithbj.shop.service;

import com.faithbj.shop.model.entity.PresentCard;
import com.faithbj.shop.model.entity.PresentCardCategory;

/**
 * Service接口 - 礼品卡
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-03-26
 * @version 1.0
 */

public interface PresentCardService extends BaseService<PresentCard, String> {

	/**
	 * 根据PresentCardCategory礼品卡类型对象和礼品卡数量，批量产生礼品卡
	 * @param cardCategory 礼品卡类型
	 * @param amount 礼品卡数量
	 * @return 产生礼品卡数量
	 */
	public Integer batchGenerateCards(PresentCardCategory cardCategory,Integer amount);
	
	

}
