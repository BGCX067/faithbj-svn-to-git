package com.faithbj.shop.tenpay.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faithbj.shop.tenpay.util.MD5Util;
import com.faithbj.shop.tenpay.util.TenpayUtil;
import com.faithbj.shop.utils.SystemConfigUtil;

/**
 * 请求处理类
 * 请求处理类继承此类，重写createSign方法即可。
 * @author miklchen
 * @author Huan Zhang <huan_zh@yahoo.com>
 *
 */
public class RequestHandler {

	private SystemConfigUtil systemConfigUtil;

	/** 网关url地址 */
	private String gateUrl;
	
	/** 密钥 */
	private String key;
	
	/** 请求的参数 */
	private SortedMap parameters;
	
	/** debug信息 */
	private String debugInfo;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	/**
	 * 构造函数
	 * @param request
	 * @param response
	 */
	public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		this.gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
		this.key = "";
		this.parameters = new TreeMap();
		this.debugInfo = "";
	}
	
	/**
	*初始化函数。
	*/
	public void init() {
		// 设置密钥
		this.setKey(systemConfigUtil.getKey());
		// 设置支付网关
		this.setGateUrl(systemConfigUtil.getRequestGateUrl());
		// 商户号
		this.setParameter("partner", systemConfigUtil.getPartner());

		// 系统可选参数
		this.setParameter("sign_type", "MD5"); // 签名类型,默认：MD5
		this.setParameter("service_version", "1.0"); // 版本号，默认为1.0
		this.setParameter("input_charset", "UTF-8"); // 字符编码
		this.setParameter("sign_key_index", "1"); // 密钥序号

		// 设置部分支付参数(如果return_url和notify_url不是固定值，请在Pay handler里设置下面两个参数)
		this.setParameter("return_url", systemConfigUtil.getReturnUrl()); // 交易完成后跳转的URL
		this.setParameter("notify_url", systemConfigUtil.getNotifyUrl()); // 接收财付通通知的URL
		this.setParameter("bank_type", "DEFAULT"); // 银行类型(中介担保时此参数无效)
		this.setParameter("fee_type", "1"); // 币种，1人民币

		// 设置付款有效期
		String currentTimeStr = TenpayUtil.getCurrTime();
		Long currentTimeInt = Long.valueOf(currentTimeStr);
		Long expireTimeInt = currentTimeInt + 1600;// 16分钟后过期
		this.setParameter("time_start", currentTimeStr); // 订单生成时间，格式为yyyymmddhhmmss
		this.setParameter("time_expire", expireTimeInt.toString()); // 订单失效时间，格式为yyyymmddhhmmss
	}

	/**
	*获取入口地址,不包含参数值
	*/
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	*设置入口地址,不包含参数值
	*/
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}

	/**
	*获取密钥
	*/
	public String getKey() {
		return key;
	}

	/**
	*设置密钥
	*/
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String 
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter); 
		return (null == s) ? "" : s;
	}
	
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}
	
	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {		
		return this.parameters;
	}

	/**
	*获取debug信息
	*/
	public String getDebugInfo() {
		return debugInfo;
	}
	
	/**
	 * 获取带参数的请求URL
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public String getRequestURL() throws UnsupportedEncodingException {
		
		this.createSign();
		
		StringBuffer sb = new StringBuffer();
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
		}
		
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		
		return this.getGateUrl() + "?" + reqPars;
		
	}
	
	public void doSend() throws UnsupportedEncodingException, IOException {
		this.response.sendRedirect(this.getRequestURL());
	}
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());
		
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
		
		this.setParameter("sign", sign);
		
		//debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
		
	}
	
	/**
	*设置debug信息
	*/
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	
	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}
	
	protected HttpServletResponse getHttpServletResponse() {
		return this.response;
	}

	public SystemConfigUtil getSystemConfigUtil() {
		return systemConfigUtil;
	}

	public void setSystemConfigUtil(SystemConfigUtil systemConfigUtil) {
		this.systemConfigUtil = systemConfigUtil;
	}
}
