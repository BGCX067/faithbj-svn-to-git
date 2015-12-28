package com.faithbj.shop.model.configuration;

/**
 * Bean类 - 财付通配置
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

public class TenpayConfig {
	
	public static final String PAYMENT_URL = "http://service.tenpay.com/cgi-bin/v3.0/payservice.cgi";// 财付通支付请求URL
	public static final String QUERY_URL = "http://mch.tenpay.com/cgi-bin/cfbi_query_order_v3.cgi";// 财付通查询请求URL
	public static final String RETURN_URL = "/shop/payment!tenpayReturn.action";// 处理返回结果的URL
	public static final String SHOW_URL = "/shop/payment!result.action";// 支付完成显示URL
	
	// 货币种类（人民币、美元、欧元、英磅、加拿大元、澳元、卢布、港币、新台币、韩元、新加坡元、新西兰元、日元、马元、瑞士法郎、瑞典克朗、丹麦克朗、兹罗提、挪威克朗、福林、捷克克朗、葡币）
	public static enum CurrencyType {
		CNY, USD, EUR, GBP, CAD, AUD, RUB, HKD, TWD, KRW, SGD, NZD, JPY, MYR, CHF, SEK, DKK, PLZ, NOK, HUF, CSK, MOP
	};
	
	// 财付通交易类型（即时交易、担保交易-实物、担保交易-虚拟）
	public enum TenpayType {
		direct, partnerMaterial, partnerVirtual
	}
	
	// 支持货币种类
	public static final CurrencyType[] currencyType = {CurrencyType.CNY};
	
	private TenpayType tenpayType;// 财会通交易类型
	private String bargainorId;// 商户号
	private String key;// 密钥
	
	public TenpayType getTenpayType() {
		return tenpayType;
	}
	
	public void setTenpayType(TenpayType tenpayType) {
		this.tenpayType = tenpayType;
	}
	
	public String getBargainorId() {
		return bargainorId;
	}
	
	public void setBargainorId(String bargainorId) {
		this.bargainorId = bargainorId;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

}
