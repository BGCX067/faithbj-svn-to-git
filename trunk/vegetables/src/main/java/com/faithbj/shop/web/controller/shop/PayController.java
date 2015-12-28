package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.faithbj.shop.model.entity.Order;
import com.faithbj.shop.tenpay.client.ClientResponseHandler;
import com.faithbj.shop.tenpay.client.TenpayHttpClient;
import com.faithbj.shop.tenpay.handler.RequestHandler;
import com.faithbj.shop.tenpay.handler.ResponseHandler;
import com.faithbj.shop.tenpay.util.TenpayUtil;
import com.faithbj.shop.utils.SystemConfigUtil;

/**
 * @author Huan Zhang <huan_zh@yahoo.com>
 * 
 */
@Controller
@RequestMapping("/product/my_cart")
public class PayController extends BaseShopController implements Serializable {
	final static Logger logger = LoggerFactory.getLogger(PayController.class);
	@Resource
	private SystemConfigUtil systemConfigUtil;

	@RequestMapping("list")
	public String getMyCart() {
		return "shop/my_cart_simple_demo";
	}

	/**
	 * 财付通网关支付请求
	 * @param order 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "pay_request")
	public String payRequest(@ModelAttribute("order") Order order,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Payment is requesting...");
		String view = "";
		String requestURL = "";
		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.setSystemConfigUtil(systemConfigUtil);
		reqHandler.init();

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		// 商家订单号[实际应为 order.getOrderSn()]
		reqHandler.setParameter("out_trade_no", getOutTradeNo());
		reqHandler.setParameter("total_fee", order.getPaymentFee().toString()); // 商品金额,以分为单位
		reqHandler.setParameter("body", order.getPaymentConfigName()); // 商品描述
		reqHandler.setParameter("spbill_create_ip", request.getRemoteAddr()); // 用户的公网ip，不是商户服务器IP
		if (order.getMemo() != null) {
			reqHandler.setParameter("subject", order.getMemo()); // 商品名称(中介交易时必填)
		} else {
			reqHandler.setParameter("subject", "");
		}

		// -----------------------------
		// 业务可选参数
		// -----------------------------
		reqHandler.setParameter("attach", ""); // 附加数据，原样返回
		// 商品费用，必须保证transport_fee + product_fee=total_fee
		reqHandler.setParameter("product_fee", "");
		// 物流费用，必须保证transport_fee + product_fee=total_fee
		reqHandler.setParameter("transport_fee", "0");
		reqHandler.setParameter("buyer_id", ""); // 买方财付通账号
		reqHandler.setParameter("goods_tag", ""); // 商品标记
		reqHandler.setParameter("trade_mode", "1"); // 交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
		reqHandler.setParameter("transport_desc", ""); // 物流说明
		reqHandler.setParameter("trans_type", "1"); // 交易类型，1实物交易，2虚拟交易
		reqHandler.setParameter("agentid", ""); // 平台ID
		reqHandler.setParameter("agent_type", ""); // 代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
		reqHandler.setParameter("seller_id", ""); // 卖家商户号，为空则等同于partner

		try {
			requestURL = reqHandler.getRequestURL();
		} catch (UnsupportedEncodingException e) {
			logger.debug("Error occurs when getting request URL!");
		}

		if (requestURL != null && !"".equals(requestURL)) {
			view = "redirect:" + requestURL;
			logger.debug("Redirecting to tenpay page, the request URL is : \n" + requestURL);
		} else {
			view = "shop/error";
		}
		return view;
	}

	/**
	 * 财付通支付应答（处理回调）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("pay_return")
	public ModelAndView payReturn(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("Payment is returning...");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/pay_fail_demo");
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(systemConfigUtil.getKey());

		logger.debug("前台回调返回参数:" + resHandler.getAllParameters());
		
		// 判断签名
		if (resHandler.isTenpaySign()) {
			logger.debug(resHandler.getDebugInfo());
			// 通知id
			String notify_id = resHandler.getParameter("notify_id");
			// 商户订单号
			String out_trade_no = resHandler.getParameter("out_trade_no");
			// 财付通订单号
			String transaction_id = resHandler.getParameter("transaction_id");
			// 金额,以分为单位
			String total_fee = resHandler.getParameter("total_fee");
			// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			String discount = resHandler.getParameter("discount");
			// 支付结果
			String trade_state = resHandler.getParameter("trade_state");
			// 交易模式，1即时到账，2中介担保
			String trade_mode = resHandler.getParameter("trade_mode");

			if ("1".equals(trade_mode)) { // 即时到账
				if ("0".equals(trade_state)) {
					// ------------------------------
					// 即时到账处理业务开始
					// ------------------------------

					// 注意交易单不要重复处理
					// 注意判断返回金额

					// ------------------------------
					// 即时到账处理业务完毕
					// ------------------------------

					mav.addObject("trad_state", "付款成功");
					mav.addObject("trad_mode", "即时到帐");
					mav.addObject("total_fee", total_fee);

					//付款成功页面
					mav.setViewName("shop/pay_success_demo");
					logger.debug("即时到帐付款成功");
				} else {
					logger.debug("即时到帐付款失败");
				}
			} else if ("2".equals(trade_mode)) { // 中介担保
				if ("0".equals(trade_state)) {
					// ------------------------------
					// 中介担保处理业务开始
					// ------------------------------

					// 注意交易单不要重复处理
					// 注意判断返回金额

					// ------------------------------
					// 中介担保处理业务完毕
					// ------------------------------

					//付款成功页面
					mav.setViewName("shop/pay_success_demo");
					logger.debug("中介担保付款成功");
				} else {
					logger.debug("中介担保付款失败， " + "trade_state="
							+ trade_state);
				}
			}
		} else {
			System.out.println("中介担保付款失败");
		}
		return mav;
	}

	/**
	 * 财付通支付通知（后台通知）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("pay_notify")
	@ResponseBody
	public String payNotify(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("Processing notify from tenpay...");
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(systemConfigUtil.getKey());
		logger.debug("后台回调返回参数:" + resHandler.getAllParameters());

		// 判断签名
		if (resHandler.isTenpaySign()) {

			// 通知id
			String notify_id = resHandler.getParameter("notify_id");

			// 应答对象
			ClientResponseHandler queryRes;
			try {
				queryRes = queryNotification(notify_id);
			} catch (Exception e) {
				logger.debug(e.getMessage());
				return "fail";
			}

			// 获取id验证返回状态码，0表示此通知id是财付通发起
			String retcode = queryRes.getParameter("retcode");

			// 商户订单号
			String out_trade_no = resHandler.getParameter("out_trade_no");
			// 财付通订单号
			String transaction_id = resHandler.getParameter("transaction_id");
			// 金额,以分为单位
			String total_fee = resHandler.getParameter("total_fee");
			// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			String discount = resHandler.getParameter("discount");
			// 支付结果
			String trade_state = resHandler.getParameter("trade_state");
			// 交易模式，1即时到账，2中介担保
			String trade_mode = resHandler.getParameter("trade_mode");

			// 判断签名及结果（验证支付结果）
			if (queryRes.isTenpaySign() && "0".equals(retcode)) {
				logger.debug("id验证成功");

				if ("1".equals(trade_mode)) { // 即时到账
					if ("0".equals(trade_state)) {
						// ------------------------------
						// 即时到账处理业务开始
						// ------------------------------

						// 处理数据库逻辑
						// 注意交易单不要重复处理
						// 注意判断返回金额

						// ------------------------------
						// 即时到账处理业务完毕
						// ------------------------------

						logger.debug("即时到账支付成功");
						// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
						return "success";

					} else {
						logger.debug("即时到账支付失败");
						return "fail";
					}
				} else if ("2".equals(trade_mode)) { // 中介担保
					// ------------------------------
					// 中介担保处理业务开始
					// ------------------------------

					// 处理数据库逻辑
					// 注意交易单不要重复处理
					// 注意判断返回金额

					int iStatus = TenpayUtil.toInt(trade_state);
					switch (iStatus) {
					case 0: // 付款成功

						break;
					case 1: // 交易创建

						break;
					case 2: // 收获地址填写完毕

						break;
					case 4: // 卖家发货成功

						break;
					case 5: // 买家收货确认，交易成功

						break;
					case 6: // 交易关闭，未完成超时关闭

						break;
					case 7: // 修改交易价格成功

						break;
					case 8: // 买家发起退款

						break;
					case 9: // 退款成功

						break;
					case 10: // 退款关闭

						break;
					default:
					}

					// ------------------------------
					// 中介担保处理业务完毕
					// ------------------------------

					logger.debug("trade_state = " + trade_state);
					// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
					return "success";
				}
			} else {
				// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				logger.debug("查询验证签名失败或id验证失败" + ",retcode:"
						+ queryRes.getParameter("retcode"));
			}
		} else {
			logger.debug("通知签名验证失败");
		}
		return "fail";
	}

	/**
	 * 获取随机的唯一的out_trade_no，若已有此number， 可忽略此方法
	 */
	private String getOutTradeNo() {
		// 当前时间 yyyyMMddHHmmss
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		return strReq;
	}

	/**
	 * 根据通知ID查询支付结果
	 * 
	 * @param notifyId
	 * @return
	 * @throws Exception
	 */
	private ClientResponseHandler queryNotification(String notifyId)
			throws Exception {
		// 创建请求对象
		RequestHandler queryReq = new RequestHandler(null, null);
		// 应答对象
		ClientResponseHandler queryRes = new ClientResponseHandler();
		// 通信对象
		TenpayHttpClient httpClient = new TenpayHttpClient();

		// 通过通知ID查询，确保通知来自财付通
		queryReq.init();
		queryReq.setKey(systemConfigUtil.getKey());
		queryReq.setGateUrl(systemConfigUtil.getNotifyGateUrl());
		queryReq.setParameter("partner", systemConfigUtil.getPartner());
		queryReq.setParameter("notify_id", notifyId);

		// 通信对象
		httpClient.setTimeOut(5);
		// 设置请求内容
		httpClient.setReqContent(queryReq.getRequestURL());
		logger.debug("验证ID请求字符串:" + queryReq.getRequestURL());

		// 后台调用
		if (httpClient.call()) {
			// 设置结果参数
			queryRes.setContent(httpClient.getResContent());
			logger.debug("验证ID返回字符串:" + httpClient.getResContent());
			queryRes.setKey(systemConfigUtil.getKey());
		} else {
			logger.debug(String.valueOf(httpClient.getResponseCode()));
			logger.debug(httpClient.getErrInfo());
			throw new Exception("Http Response Code : "
					+ httpClient.getResponseCode() + " Http Error : "
					+ httpClient.getErrInfo());
		}

		return queryRes;
	}
}
