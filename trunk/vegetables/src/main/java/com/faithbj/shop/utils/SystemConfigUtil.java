package com.faithbj.shop.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.faithbj.shop.model.configuration.PointType;

/**
 * 工具类 - 系统配置
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Component
public class SystemConfigUtil {

	// 货币种类（人民币、美元、欧元、英磅、加拿大元、澳元、卢布、港币、新台币、韩元、新加坡元、新西兰元、日元、马元、瑞士法郎、瑞典克朗、丹麦克朗、兹罗提、挪威克朗、福林、捷克克朗、葡币）
		public enum CurrencyType {
			CNY, USD, EUR, GBP, CAD, AUD, RUB, HKD, TWD, KRW, SGD, NZD, JPY, MYR, CHF, SEK, DKK, PLZ, NOK, HUF, CSK, MOP
		};
		
		// 小数位精确方式（四舍五入、向上取整、向下取整）
		public enum RoundType {
			roundHalfUp, roundUp, roundDown
		}
		
		// 库存预占时间点（下订单、订单付款、订单发货）
		public enum StoreFreezeTime {
			order, payment, ship
		}
		
		// 水印位置（无、左上、右上、居中、左下、右下）
		public enum WatermarkPosition {
			no, topLeft, topRight, center, bottomLeft, bottomRight
		}
		
		// 积分获取方式（禁用积分获取、按订单总额计算、为商品单独设置）
//		public enum PointType {
//			disable, orderAmount, productSet
//		}	
	
	public static final String CONFIG_FILE_NAME = "shop.properties";// 系统配置文件名称
//	public static final String SYSTEM_CONFIG_CACHE_KEY = "systemConfig";// systemConfig缓存Key

	
    //系统名称
    @Value("${shop.systemName}")
    private String systemName;
    //系统版本
    @Value("${shop.systemVersion}")
    private String systemVersion;
    //系统描述    
    @Value("${shop.systemDescription}")
    private String systemDescription;    
    //是否已经安装 
    @Value("${shop.isInstalled}")
    private Boolean isInstalled;    
    //网站名称
    @Value("${shop.shopName}")
    private String shopName;    
    //网站URL
    @Value("${shop.shopUrl}")
    private static String shopUrl;    
    //网站Logo
    @Value("${shop.shopLogo}")
    private String shopLogo;    
    //热门搜索
    @Value("${shop.hotSearch}")
    private String hotSearch;    
    //metaKeywords
    @Value("${shop.metaKeywords}")
    private String metaKeywords;    
    //metaDescription
    @Value("${shop.metaKeywords}")
    private String metaDescription;    
    //公司地址
    @Value("${shop.address}")
    private String address;    
    //电话
    @Value("${shop.phone}")
    private String phone;    
    //邮编
    @Value("${shop.zipCode}")
    private String zipCode;    
    //email
    @Value("${shop.email}")
    private String email;    
    //货币类型
    @Value("${shop.currencyType}")
    private String currencyType;    
    //货币符号
    @Value("${shop.currencySign}")
    private String currencySign;    
    //货币单位
    @Value("${shop.currencyUnit}")
    private String currencyUnit;    
    //货币单位
    @Value("${shop.priceScale}")
    private Integer priceScale;    
    //四舍五入
    @Value("${shop.priceRoundType}")
    private RoundType priceRoundType;    
    //orderRoundType
    @Value("${shop.orderRoundType}")
    private RoundType orderRoundType;    
    //备案信息
    @Value("${shop.certtext}")
    private String certtext;    
    //storeAlertCount
    @Value("${shop.storeAlertCount}")
    private Integer storeAlertCount;    
    //storeFreezeTime
    @Value("${shop.storeFreezeTime}")
    private String storeFreezeTime;    
    //上传照片大小限制，单位(M)兆
    @Value("${shop.uploadLimit}")
    private Integer uploadLimit;  
    //是否启动自动锁定账号
    @Value("${shop.isLoginFailureLock}")
    private Boolean isLoginFailureLock;  
    //连续登录失败最大次数
    @Value("${shop.loginFailureLockCount}")
    private Integer loginFailureLockCount;  
    //自动解锁时间
    @Value("${shop.loginFailureLockTime}")
    private Integer loginFailureLockTime;  
    
    
    
    //图片文件根路径
    @Value("${shop.uploadImageDIR}")
    private String uploadImageDIR;    
    
    
    //水印图片文件路径
    @Value("${shop.watermarkImagePath}")
    private String watermarkImagePath;    
    //水印图片位置
    @Value("${shop.watermarkPosition}")
    private String watermarkPosition;    
    //水印图片透明度
    @Value("${shop.watermarkAlpha}")
    private String watermarkAlpha;    
    //商品大图宽度
    @Value("${shop.bigProductImageWidth}")
    private Integer bigProductImageWidth;    
    //商品大图高度
    @Value("${shop.bigProductImageHeight}")
    private Integer bigProductImageHeight;    
    //商品小图宽度
    @Value("${shop.smallProductImageWidth}")
    private Integer smallProductImageWidth;    
    //商品小图高度
    @Value("${shop.smallProductImageHeight}")
    private Integer smallProductImageHeight;    
    //商品缩略小图宽度
    @Value("${shop.thumbnailProductImageWidth}")
    private Integer thumbnailProductImageWidth;    
    //商品极缩略图高度
    @Value("${shop.thumbnailProductImageHeight}")
    private Integer thumbnailProductImageHeight;    
    //默认大图片路径
    @Value("${shop.defaultBigProductImagePath}")
    private String defaultBigProductImagePath;    
    //默认小图片路径
    @Value("${shop.defaultSmallProductImagePath}")
    private String defaultSmallProductImagePath;    
    //允许上传的图片后缀
    @Value("${shop.allowedUploadImageExtension}")
    private String allowedUploadImageExtension;    
    //允许上传的多媒体后缀
    @Value("${shop.allowedUploadMediaExtension}")
    private String allowedUploadMediaExtension;    
    //允许上传的文件后缀
    @Value("${shop.allowedUploadFileExtension}")
    private String allowedUploadFileExtension;    
    //smtpFrom邮件
    @Value("${shop.smtpFromMail}")
    private String smtpFromMail;    
    //smtpHost
    @Value("${shop.smtpHost}")
    private String smtpHost;    
    //smtpHost
    @Value("${shop.smtpPort}")
    private String smtpPort;    
    //smtpUsername
    @Value("${shop.smtpUsername}")
    private String smtpUsername;    
    //smtpPassword
    @Value("${shop.smtpPassword}")
    private String smtpPassword;    
    //pointType
    @Value("${shop.pointType}")
    private PointType pointType;    
/*    @Value("${shop.pointType}")
    private String pointType;    
*/    //pointScale
    @Value("${shop.pointScale}")
    private Integer pointScale;    
    //土地商品的id，生成数据库后，可以再改
    @Value("${shop.isCommentEnabled}")
    private Boolean isCommentEnabled;    
    //是否显示市场价格
    @Value("${shop.isShowMarketPrice}")
    private Boolean isShowMarketPrice;    

	//支付网关
	@Value("${shop.tenpay.request.gate_url}")
	private String requestGateUrl;
	//通知网关
	@Value("${shop.tenpay.notify.gate_url}")
	private String notifyGateUrl;
	//商户号
	@Value("${shop.tenpay.partner}")
	private String partner;
	//密钥
	@Value("${shop.tenpay.key}")
	private String key;
	//交易完成后跳转URL
	@Value("${shop.tenpay.return_url}")
	private String returnUrl;
	//接收财付通通知的URL
	@Value("${shop.tenpay.notify_url}")
	private String notifyUrl;
	
	/**
	 * 刷新系统配置信息
	 * 
	 */
	public void flush() {
//		OsCacheConfigUtil.flushEntry(SYSTEM_CONFIG_CACHE_KEY);
	}
	
	/**
	 * 获取精度处理后的商品价格
	 * 
	 */
	public BigDecimal getPriceScaleBigDecimal(BigDecimal price) {
		if (priceRoundType == RoundType.roundHalfUp) {
			return price.setScale(priceScale, BigDecimal.ROUND_HALF_UP);
		} else if (priceRoundType == RoundType.roundUp) {
			return price.setScale(priceScale, BigDecimal.ROUND_UP);
		} else {
			return price.setScale(priceScale, BigDecimal.ROUND_DOWN);
		}
	}
	
	
	/**
	 * 获取商品价格货币格式字符串
	 * 
	 */
	public String getPriceCurrencyFormat() {
		if (priceScale == 0) {
			return currencySign + "#0";
		} else if (priceScale == 1) {
			return currencySign + "#0.0";
		} else if (priceScale == 2) {
			return currencySign + "#0.00";
		} else if (priceScale == 3) {
			return currencySign + "#0.000";
		} else if (priceScale == 4) {
			return currencySign + "#0.0000";
		} else {
			return currencySign + "#0.00000";
		}
	}
	
	/**
	 * 获取商品价格货币格式字符串（包含货币单位）
	 * 
	 */
	public String getCurrencyFormat() {
		if (priceScale == 0) {
			return currencySign + "#0" + currencyUnit;
		} else if (priceScale == 1) {
			return currencySign + "#0.0" + currencyUnit;
		} else if (priceScale == 2) {
			return currencySign + "#0.00" + currencyUnit;
		} else if (priceScale == 3) {
			return currencySign + "#0.000" + currencyUnit;
		} else if (priceScale == 4) {
			return currencySign + "#0.0000" + currencyUnit;
		} else {
			return currencySign + "#0.00000" + currencyUnit;
		}
	}

	public  String getCurrencyFormatString(BigDecimal paramBigDecimal)
	{
	    String str = getCurrencyFormat();
	    DecimalFormat localDecimalFormat = new DecimalFormat(str);
	    return localDecimalFormat.format(paramBigDecimal);
	}

	/**
	 * 获取精度处理后的订单额
	 * 
	 */
	public BigDecimal getOrderScaleBigDecimal(BigDecimal orderAmount) {
	
		
		if (orderRoundType == RoundType.roundHalfUp) {
			return orderAmount.setScale(priceScale, BigDecimal.ROUND_HALF_UP);
		} else if (orderRoundType == RoundType.roundUp) {
			return orderAmount.setScale(priceScale, BigDecimal.ROUND_UP);
		} else {
			return orderAmount.setScale(priceScale, BigDecimal.ROUND_DOWN);
		}
	}
	
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public Boolean getIsInstalled() {
		return isInstalled;
	}

	public void setIsInstalled(Boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public static String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getHotSearch() {
		return hotSearch;
	}

	public void setHotSearch(String hotSearch) {
		this.hotSearch = hotSearch;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencySign() {
		return currencySign;
	}

	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public Integer getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	public RoundType getPriceRoundType() {
		return priceRoundType;
	}

	public void setPriceRoundType(RoundType priceRoundType) {
		this.priceRoundType = priceRoundType;
	}

	public RoundType getOrderRoundType() {
		return orderRoundType;
	}

	public void setOrderRoundType(RoundType orderRoundType) {
		this.orderRoundType = orderRoundType;
	}

	public String getCerttext() {
		return certtext;
	}

	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}

	public Integer getStoreAlertCount() {
		return storeAlertCount;
	}

	public void setStoreAlertCount(Integer storeAlertCount) {
		this.storeAlertCount = storeAlertCount;
	}

	public String getStoreFreezeTime() {
		return storeFreezeTime;
	}

	public void setStoreFreezeTime(String storeFreezeTime) {
		this.storeFreezeTime = storeFreezeTime;
	}

	public Integer getUploadLimit() {
		return uploadLimit;
	}

	public void setUploadLimit(Integer uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	public Boolean getIsLoginFailureLock() {
		return isLoginFailureLock;
	}

	public void setIsLoginFailureLock(Boolean isLoginFailureLock) {
		this.isLoginFailureLock = isLoginFailureLock;
	}

	public Integer getLoginFailureLockCount() {
		return loginFailureLockCount;
	}

	public void setLoginFailureLockCount(Integer loginFailureLockCount) {
		this.loginFailureLockCount = loginFailureLockCount;
	}

	public Integer getLoginFailureLockTime() {
		return loginFailureLockTime;
	}

	public void setLoginFailureLockTime(Integer loginFailureLockTime) {
		this.loginFailureLockTime = loginFailureLockTime;
	}

	public String getWatermarkImagePath() {
		return watermarkImagePath;
	}

	public void setWatermarkImagePath(String watermarkImagePath) {
		this.watermarkImagePath = watermarkImagePath;
	}

	public String getWatermarkPosition() {
		return watermarkPosition;
	}

	public void setWatermarkPosition(String watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	public String getWatermarkAlpha() {
		return watermarkAlpha;
	}

	public void setWatermarkAlpha(String watermarkAlpha) {
		this.watermarkAlpha = watermarkAlpha;
	}

	public Integer getBigProductImageWidth() {
		return bigProductImageWidth;
	}

	public void setBigProductImageWidth(Integer bigProductImageWidth) {
		this.bigProductImageWidth = bigProductImageWidth;
	}

	public Integer getBigProductImageHeight() {
		return bigProductImageHeight;
	}

	public void setBigProductImageHeight(Integer bigProductImageHeight) {
		this.bigProductImageHeight = bigProductImageHeight;
	}

	public Integer getSmallProductImageWidth() {
		return smallProductImageWidth;
	}

	public void setSmallProductImageWidth(Integer smallProductImageWidth) {
		this.smallProductImageWidth = smallProductImageWidth;
	}

	public Integer getSmallProductImageHeight() {
		return smallProductImageHeight;
	}

	public void setSmallProductImageHeight(Integer smallProductImageHeight) {
		this.smallProductImageHeight = smallProductImageHeight;
	}

	public Integer getThumbnailProductImageWidth() {
		return thumbnailProductImageWidth;
	}

	public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth) {
		this.thumbnailProductImageWidth = thumbnailProductImageWidth;
	}

	public Integer getThumbnailProductImageHeight() {
		return thumbnailProductImageHeight;
	}

	public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight) {
		this.thumbnailProductImageHeight = thumbnailProductImageHeight;
	}

	public String getDefaultBigProductImagePath() {
		return defaultBigProductImagePath;
	}

	public void setDefaultBigProductImagePath(String defaultBigProductImagePath) {
		this.defaultBigProductImagePath = defaultBigProductImagePath;
	}

	public String getDefaultSmallProductImagePath() {
		return defaultSmallProductImagePath;
	}

	public void setDefaultSmallProductImagePath(String defaultSmallProductImagePath) {
		this.defaultSmallProductImagePath = defaultSmallProductImagePath;
	}

	public String getAllowedUploadImageExtension() {
		return allowedUploadImageExtension;
	}

	public void setAllowedUploadImageExtension(String allowedUploadImageExtension) {
		this.allowedUploadImageExtension = allowedUploadImageExtension;
	}

	public String getAllowedUploadMediaExtension() {
		return allowedUploadMediaExtension;
	}

	public void setAllowedUploadMediaExtension(String allowedUploadMediaExtension) {
		this.allowedUploadMediaExtension = allowedUploadMediaExtension;
	}

	public String getAllowedUploadFileExtension() {
		return allowedUploadFileExtension;
	}

	public void setAllowedUploadFileExtension(String allowedUploadFileExtension) {
		this.allowedUploadFileExtension = allowedUploadFileExtension;
	}

	public String getSmtpFromMail() {
		return smtpFromMail;
	}

	public void setSmtpFromMail(String smtpFromMail) {
		this.smtpFromMail = smtpFromMail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public PointType getPointType() {
		return pointType;
	}

	public void setPointType(PointType pointType) {
		this.pointType = pointType;
	}

	public Integer getPointScale() {
		return pointScale;
	}

	public void setPointScale(Integer pointScale) {
		this.pointScale = pointScale;
	}

	public Boolean getIsCommentEnabled() {
		return isCommentEnabled;
	}

	public void setIsCommentEnabled(Boolean isCommentEnabled) {
		this.isCommentEnabled = isCommentEnabled;
	}

	public String getUploadImageDIR() {
		return uploadImageDIR;
	}

	public void setUploadImageDIR(String uploadImageDIR) {
		this.uploadImageDIR = uploadImageDIR;
	}

	public Boolean getIsShowMarketPrice() {
		return isShowMarketPrice;
	}

	public void setIsShowMarketPrice(Boolean isShowMarketPrice) {
		this.isShowMarketPrice = isShowMarketPrice;
	}

	public String getRequestGateUrl() {
		return requestGateUrl;
	}

	public void setRequestGateUrl(String requestGateUrl) {
		this.requestGateUrl = requestGateUrl;
	}

	public String getNotifyGateUrl() {
		return notifyGateUrl;
	}

	public void setNotifyGateUrl(String notifyGateUrl) {
		this.notifyGateUrl = notifyGateUrl;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
