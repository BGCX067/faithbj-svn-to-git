package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faithbj.shop.model.configuration.PointType;
import com.faithbj.shop.model.entity.CartItem;
import com.faithbj.shop.model.entity.DeliveryType;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Order;
import com.faithbj.shop.model.entity.OrderItem;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.OrderService;
import com.faithbj.shop.support.annotation.NeedNavigation;
import com.faithbj.shop.utils.ArithUtil;

/**
 * 前台Action类 - 订单处理
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/cjlhome/order")
public class OrderController extends BaseShopController implements Serializable {
	
	private static final long serialVersionUID = 2553137844831167917L;
	
	@Resource
	private OrderService orderService;
	
	
/*	private BigDecimal productTotalPrice;// 总计商品价格
	private String memo;// 附言
	
	private Receiver receiver;// 其它收货地址
	private DeliveryType deliveryType;// 配送方式
	private PaymentConfig paymentConfig;// 支付方式
	private Order order;// 订单
	private Set<CartItem> cartItemSet;// 购物车项
*/	
	
//	@Resource
//	private ReceiverService receiverService;
//	@Resource
//	private AreaService areaService;
//	@Resource
//	private DeliveryTypeService deliveryTypeService;
//	@Resource
//	private PaymentConfigService paymentConfigService;

//	@Resource
//	private OrderLogService orderLogService;
//	@Resource
//	private CartItemService cartItemService;
//	@Resource
//	private FieldBlockCartItemService fieldBlockCartItemService;
//	@Resource
//	private OrderItemService orderItemService;
//	@Resource
//	private ProductService productService;
//	@Resource
//	private HtmlService htmlService;
	
	
	// 订单列表
	@RequestMapping("list")
	@NeedNavigation
	public String list(ModelMap map,HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String usrname = (String) session.getAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME);
		Members loginMember = getLoginMember();
		pager = orderService.getOrderPager(loginMember, pager);
		map.put("loginMember", loginMember);
		map.put("pager", pager);
		return "shop/order_list";
	}
	
	// 我的土地订单列表
	@RequestMapping("/myLandList")
	@NeedNavigation
	public String myLandList(ModelMap map,HttpServletRequest request,HttpServletResponse response) {
		Members loginMember = getLoginMember();
		pager = orderService.getOrderPager(loginMember, pager);
		List<Order> orderlist = (List<Order>) pager.getResult();
		List<Order> landlist = new ArrayList<Order>();
		for(Order order : orderlist){
			Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
			for(OrderItem ot : order.getOrderItemSet()){
				if(ot.getProduct().getIsLand()==true){
					orderItemSet.add(ot);
				}
			}
			order.setOrderItemSet(orderItemSet);
			landlist.add(order);
		}
		map.put("loginMember", loginMember);
		map.put("landlist", landlist);
		return "shop/my_land_list";
	}
	
	/**
	 * 订单信息
	 * @return
	 */
	@RequestMapping("/info")
	public String info(ModelMap map) {
		Boolean isSaveReceiver;// 是否保存收货地址
		Integer totalQuantity=0;// 商品总数
		Integer totalPoint=0;// 总积分
		Double totalWeightGram = 0D;// 商品总重量（单位：g）
		
		Set<CartItem> cartItemSet = getLoginMember().getCartItemSet();
		if ((cartItemSet == null || cartItemSet.size() == 0)) {
			return ERROR;
		}
		
		for (CartItem cartItem : cartItemSet) {
			Product product = cartItem.getProduct();
			if (product.getStore() != null && (cartItem.getQuantity() + product.getFreezeStore()) > product.getStore()) {
				map.put("errorMessage","库存不足，请返回修改！");
				return ERROR;
			}
		}
		
		BigDecimal productTotalPrice = new BigDecimal(0);
		for (CartItem cartItem : cartItemSet) {
			totalQuantity += cartItem.getQuantity();
			if (systemConfigUtil.getPointType() == PointType.productSet) {
				totalPoint = cartItem.getProduct().getPoint() * cartItem.getQuantity() + totalPoint;
			}
			productTotalPrice = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())).add(productTotalPrice);
			Product product = cartItem.getProduct();
			Double weightGram = DeliveryType.toWeightGram(product.getWeight(), product.getWeightUnit());
			totalWeightGram = ArithUtil.add(totalWeightGram, ArithUtil.mul(weightGram, cartItem.getQuantity()));
		}
		
		productTotalPrice = systemConfigUtil.getOrderScaleBigDecimal(productTotalPrice);
		if (systemConfigUtil.getPointType() == PointType.orderAmount) {
			totalPoint = productTotalPrice.divide(new BigDecimal(systemConfigUtil.getPointScale())).setScale(0, RoundingMode.DOWN).intValue();
		}
		
		map.put("totalQuantity",totalQuantity);
		map.put("totalPoint",totalPoint);
		map.put("productTotalPrice",productTotalPrice);
		map.put("totalWeightGram",totalWeightGram);
		
		return "shop/order_info";
	}
//	
//	// 订单保存
//	@Validations(
//		requiredStrings = {
//			@RequiredStringValidator(fieldName = "deliveryType.id", message = "请选择配送方式！")
//		}
//	)
//	@InputConfig(resultName = "error")
//	public String save() {
//		cartItemSet = getLoginMember().getCartItemSet();
//		this.fieldBlockCartItemSet = getLoginMember().getFieldBlockCartItemSet();
//		if ((cartItemSet == null || cartItemSet.size() == 0) && (this.fieldBlockCartItemSet == null || this.fieldBlockCartItemSet.size() == 0)) {
//			addActionError("购物车目前没有加入任何商品！");
//			return ERROR;
//		}
//		if (StringUtils.isNotEmpty(receiver.getId())) {
//			receiver = receiverService.load(receiver.getId());
//			if (areaService.getAreaString(receiver.getAreaPath()) == null) {
//				addActionError("收货地址信息不完整，请补充收货地址信息！");
//				redirectionUrl = "receiver!edit.action?id=" + receiver.getId();
//				return ERROR;
//			}
//		} else {
//			if (StringUtils.isEmpty(receiver.getName())) {
//				addActionError("收货人不允许为空！");
//				return ERROR;
//			}
//			if (StringUtils.isEmpty(receiver.getAreaPath())) {
//				addActionError("地区不允许为空！");
//				return ERROR;
//			}
//			if (StringUtils.isEmpty(receiver.getAddress())) {
//				addActionError("联系地址不允许为空！");
//				return ERROR;
//			}
//			if (StringUtils.isEmpty(receiver.getZipCode())) {
//				addActionError("邮编不允许为空！");
//				return ERROR;
//			}
//			if (StringUtils.isEmpty(receiver.getPhone()) && StringUtils.isEmpty(receiver.getMobile())) {
//				addActionError("联系电话、联系手机必须填写其中一项！");
//				return ERROR;
//			}
//			if (!areaService.isAreaPath(receiver.getAreaPath())) {
//				addActionError("地区错误！");
//				return ERROR;
//			}
//			if (isSaveReceiver == null) {
//				addActionError("是否保存不允许为空！");
//				return ERROR;
//			}
//			if (isSaveReceiver) {
//				receiver.setIsDefault(false);
//				receiver.setMember(getLoginMember());
//				receiverService.save(receiver);
//			}
//		}
//		for (CartItem cartItem : cartItemSet) {
//			Product product = cartItem.getProduct();
//			if (product.getStore() != null && (cartItem.getQuantity() + product.getFreezeStore() > product.getStore())) {
//				addActionError("商品[" + product.getName() + "]库存不足！");
//				return ERROR;
//			}
//		}
//		for (FieldBlockCartItem fieldBlockCartItem : fieldBlockCartItemSet) {
//			FieldBlock fieldBlock = fieldBlockCartItem.getFieldBlock();
//			if (fieldBlock.getStore() != null && fieldBlockCartItem.getQuantity() > fieldBlock.getStore()) {
//				addActionError("承包土地数量不足，请返回修改！");
//				return ERROR;
//			}
//		}
//		deliveryType = deliveryTypeService.load(deliveryType.getId());
//		if (deliveryType.getDeliveryMethod() == DeliveryMethod.deliveryAgainstPayment && (paymentConfig == null && StringUtils.isEmpty(paymentConfig.getId()))) {
//			addActionError("请选择支付方式！");
//			return ERROR;
//		}
//		totalQuantity = 0;
//		productTotalPrice = new BigDecimal("0");
//		totalWeightGram = 0D;
//		for (CartItem cartItem : cartItemSet) {
//			Product product = cartItem.getProduct();
//			totalQuantity += cartItem.getQuantity();
//			productTotalPrice = cartItem.getProduct().getPreferentialPrice(getLoginMember()).multiply(new BigDecimal(cartItem.getQuantity().toString())).add(productTotalPrice);
//			Double weightGram = DeliveryType.toWeightGram(product.getWeight(), product.getWeightUnit());
//			totalWeightGram = ArithUtil.add(totalWeightGram, ArithUtil.mul(weightGram, cartItem.getQuantity()));
//			cartItemService.delete(cartItem);
//		}
//		for (FieldBlockCartItem cartItem : fieldBlockCartItemSet) {
//			FieldBlock fieldBlock = cartItem.getFieldBlock();
//			totalQuantity += cartItem.getQuantity();
//			productTotalPrice = cartItem.getPreferentialPrice().multiply(new BigDecimal(cartItem.getQuantity().toString())).add(productTotalPrice);
//			fieldBlockCartItemService.delete(cartItem);
//		}
//		productTotalPrice = SystemConfigUtil.getOrderScaleBigDecimal(productTotalPrice);
//		BigDecimal deliveryFee = deliveryType.getDeliveryFee(totalWeightGram);
//		
//		String paymentConfigName = null;
//		BigDecimal paymentFee = null;
//		if (deliveryType.getDeliveryMethod() == DeliveryMethod.deliveryAgainstPayment) {
//			paymentConfig = paymentConfigService.load(paymentConfig.getId());
//			paymentConfigName = paymentConfig.getName();
//			paymentFee = paymentConfig.getPaymentFee(productTotalPrice.add(deliveryFee));
//		} else {
//			paymentConfig = null;
//			paymentConfigName = "货到付款";
//			paymentFee = new BigDecimal("0");
//		}
//		
//		BigDecimal totalAmount = productTotalPrice.add(deliveryFee).add(paymentFee);
//		
//		order = new Order();
//		order.setOrderStatus(OrderStatus.unprocessed);
//		order.setPaymentStatus(PaymentStatus.unpaid);
//		order.setShippingStatus(ShippingStatus.unshipped);
//		order.setDeliveryTypeName(deliveryType.getName());
//		order.setPaymentConfigName(paymentConfigName);
//		order.setProductTotalPrice(productTotalPrice);
//		order.setDeliveryFee(deliveryFee);
//		order.setPaymentFee(paymentFee);
//		order.setTotalAmount(totalAmount);
//		order.setPaidAmount(new BigDecimal("0"));
//		if (totalWeightGram < 1000) {
//			order.setProductWeight(totalWeightGram);
//			order.setProductWeightUnit(WeightUnit.g);
//		} else if(totalWeightGram >= 1000 && totalWeightGram < 1000000) {
//			order.setProductWeight(totalWeightGram / 1000);
//			order.setProductWeightUnit(WeightUnit.kg);
//		} else if(totalWeightGram >= 1000000) {
//			order.setProductWeight(totalWeightGram / 1000000);
//			order.setProductWeightUnit(WeightUnit.t);
//		}
//		order.setProductTotalQuantity(totalQuantity);
//		order.setShipName(receiver.getName());
//		order.setShipArea(areaService.getAreaString(receiver.getAreaPath()));
//		order.setShipAreaPath(receiver.getAreaPath());
//		order.setShipAddress(receiver.getAddress());
//		order.setShipZipCode(receiver.getZipCode());
//		order.setShipPhone(receiver.getPhone());
//		order.setShipMobile(receiver.getMobile());
//		order.setMemo(memo);
//		order.setMember(getLoginMember());
//		order.setDeliveryType(deliveryType);
//		order.setPaymentConfig(paymentConfig);
//		orderService.save(order);
//		
//		// 商品项
//		for (CartItem cartItem : cartItemSet) {
//			Product product = cartItem.getProduct();
//			OrderItem orderItem = new OrderItem();
//			orderItem.setProductSn(product.getProductSn());
//			orderItem.setProductName(product.getName());
//			orderItem.setProductPrice(product.getPreferentialPrice(getLoginMember()));
//			orderItem.setProductQuantity(cartItem.getQuantity());
//			orderItem.setDeliveryQuantity(0);
//			orderItem.setTotalDeliveryQuantity(0);
//			orderItem.setProductHtmlFilePath(product.getHtmlFilePath());
//			orderItem.setOrder(order);
//			orderItem.setProduct(product);
//			orderItemService.save(orderItem);
//		}
//		
//		// 库存处理
//		if (getSystemConfig().getStoreFreezeTime() == StoreFreezeTime.order) {
//			for (CartItem cartItem : cartItemSet) {
//				Product product = cartItem.getProduct();
//				if (product.getStore() != null) {
//					product.setFreezeStore(product.getFreezeStore() + cartItem.getQuantity());
//					if (product.getIsOutOfStock()) {
//						Hibernate.initialize(cartItem.getProduct().getProductAttributeMapStore());
//					}
//					productService.update(product);
//					if (product.getIsOutOfStock()) {
//						flushCache();
//						htmlService.productContentBuildHtml(product);
//					}
//				}
//			}
//		}
//		
//		// 订单日志
//		OrderLog orderLog = new OrderLog();
//		orderLog.setOrderLogType(OrderLogType.create);
//		orderLog.setOrderSn(order.getOrderSn());
//		orderLog.setOperator(null);
//		orderLog.setInfo(null);
//		orderLog.setOrder(order);
//		orderLogService.save(orderLog);
//		
//		setResponseNoCache();
//		return "result";
//	}
//	
//	
//	// 订单详情
//	public String view() {
//		order = orderService.load(id);
//		totalPoint = 0;
//		if (getSystemConfig().getPointType() == PointType.productSet) {
//			for (OrderItem orderItem : order.getOrderItemSet()) {
//				totalPoint = orderItem.getProduct().getPoint() * orderItem.getProductQuantity() + totalPoint;
//			}
//		} else if (getSystemConfig().getPointType() == PointType.orderAmount) {
//			totalPoint = order.getProductTotalPrice().multiply(new BigDecimal(getSystemConfig().getPointScale().toString())).setScale(0, RoundingMode.DOWN).intValue();
//		}
//		return "view";
//	}
//	
//	// 更新页面缓存
//	private void flushCache() {
//		Cache cache = ServletCacheAdministrator.getInstance(getRequest().getSession().getServletContext()).getCache(getRequest(), PageContext.APPLICATION_SCOPE); 
//		cache.flushAll(new Date());
//	}
//	
//	// 获取所有配送方式
//	public List<DeliveryType> getAllDeliveryType() {
//		return deliveryTypeService.getAll();
//	}
//	
//	// 获取所有支付方式
//	public List<PaymentConfig> getAllPaymentConfig() {
//		return paymentConfigService.getAll();
//	}

}
