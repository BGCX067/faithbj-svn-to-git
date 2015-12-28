package com.faithbj.shop.dao;

import java.util.List;

import com.faithbj.shop.model.entity.PresentCard;

/**
 * Dao接口 - 礼品卡
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public interface PresentCardDao extends BaseDao<PresentCard, String> {
	
	Integer batchGenerateCards(List<PresentCard> presentCards);
}
