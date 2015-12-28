package com.faithbj.shop.dao.impl;

import com.faithbj.shop.dao.AgreementDao;
import com.faithbj.shop.entity.Agreement;

import org.springframework.stereotype.Repository;

/**
 * Dao实现类 - 会员注册协议
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Repository
public class AgreementDaoImpl extends BaseDaoImpl<Agreement, String> implements AgreementDao {

	public Agreement getAgreement() {
		return load(Agreement.AGREEMENT_ID);
	}

}
