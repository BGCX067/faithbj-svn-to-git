package com.faithbj.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.dao.OnlineDao;
import com.faithbj.shop.entity.Online;
import com.faithbj.shop.service.OnlineService;

import org.springframework.stereotype.Service;

/**
 * Service实现类 - 在线问答
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Service("onlineService")
public class OnlineServiceImpl extends BaseServiceImpl<Online, String> implements OnlineService {
	
	@Resource
	private OnlineDao onlineDao;
	
	
    public List<Online> getAllQ(){
	    return onlineDao.getAllQ();
    };
	
	public List<Online> getAllA(){
		return onlineDao.getAllA();
	};
	
	
	
	@Resource
	public void setBaseDao(OnlineDao onlineDao) {
		super.setBaseDao(onlineDao);
	}


}
