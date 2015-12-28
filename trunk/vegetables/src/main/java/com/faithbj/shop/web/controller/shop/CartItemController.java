package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faithbj.shop.model.configuration.CartItemCookie;
import com.faithbj.shop.model.configuration.PointType;
import com.faithbj.shop.model.entity.CartItem;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.service.CartItemService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;
import com.faithbj.shop.utils.JsonUtil;

/**
 * 前台Action类 - 购物车项
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
@RequestMapping("/product/caritem")
public class CartItemController extends BaseShopController implements Serializable{

	private static final long serialVersionUID = 4404250263935012169L;
	

	@Resource
	private ProductService productService;
	@Resource
	private CartItemService cartItemService;
	
	// 添加购物车项
	@RequestMapping(value="/ajaxAdd",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap ajaxAdd(@CookieValue(value="cartItemList",required=false) String cartItemList,ModelMap map,
			HttpServletResponse response,@RequestParam("quantity") Integer quantity,@RequestParam("id") String id) {

		Product product = productService.load(id);
		if (!product.getIsMarketable()) {
			map.put(STATUS, "error");
			map.put("message", "此商品已下架!");
			return map;
		}
		
		Integer totalQuantity =Integer.valueOf(0);// 总计商品数量
		BigDecimal totalPrice = new BigDecimal(0);// 总计商品价格
		Members loginMemberInfo=getLoginMember();
		Members loginMember = memberService.getMemberByUsername(loginMemberInfo.getUsername());
		
		if (quantity == null || quantity < 1) {
			quantity = 1;
		}
		
		boolean isExist = false;//本次购物id，以前也购物过(!=原来cookie中是否存在正在添加的商品,默认不存在)
/*		if(!StringUtils.isBlank(cartItemList))
			isExist = true;*/
		
		Product localProduct;
//登陆会员，把本次购买的商品，加入购物车，并更新数据库
		if (loginMember != null) 
		{
			Set<CartItem> previousCartItem = loginMember.getCartItemSet();
			for (CartItem localCartItem : previousCartItem)
		    {
				localProduct = localCartItem.getProduct();
				if (StringUtils.equals(localProduct.getId(), id))
				{
					isExist=true;//本次购物id，以前也购物过
					localCartItem.setQuantity(localCartItem.getQuantity() + quantity);
					
		            if ((product.getStore() != null) && (product.getFreezeStore() + localCartItem.getQuantity() > product.getStore())){
		    			map.put(STATUS, "error");
		    			map.put("message", "添加购物车失败,商品库存不足!");
		    			return map;
		            }
		            cartItemService.update(localCartItem);
		         }
				  //购物总数
				  totalQuantity +=localCartItem.getQuantity();
				  //会员按等级打折，如果不要打折 用getPrice()
				  totalPrice = localProduct.getPrice().multiply(new BigDecimal(localCartItem.getQuantity())).add(totalPrice);
		    }
		    if (!isExist)
		    {
		    	 if ((product.getStore() != null) && (product.getFreezeStore() + quantity > product.getStore()))
		    	 {
		    		map.put(STATUS, "error");
		    		map.put("message", "添加购物车失败,商品库存不足!");
		    		return map;
		    	 }
		    	CartItem localCartItem = new CartItem();
		        localCartItem.setMember(loginMember);
		        localCartItem.setProduct(product);
		        localCartItem.setQuantity(quantity);
		        cartItemService.save(localCartItem);
		        //购物总数
				totalQuantity +=localCartItem.getQuantity();
				//会员按等级打折，如果不要打折 用getPrice()
		        totalPrice = product.getPrice().multiply(new BigDecimal(localCartItem.getQuantity())).add(totalPrice);
		    }
		}
//没有登陆的情况，把本次购买的商品，加入购物车，写入cookie
		else 
		{
			List<CartItemCookie> cartItemCookieList = new ArrayList<CartItemCookie>();//新cookielist，要保存的cookie
			
			//cookie不是空,说明之前添加过购物车
			if(!StringUtils.isBlank(cartItemList)) 
			{
				List<CartItemCookie> localCartItemCookies=JsonUtil.Json2List(cartItemList, new TypeReference<List<CartItemCookie>>(){});
				
				for (CartItemCookie previousCartItemCookie : localCartItemCookies) 
				{
					Product cartItemProduct = productService.get(previousCartItemCookie.getI());
					if (cartItemProduct == null)//没有该商品
						continue;
					
				    if (StringUtils.equals(previousCartItemCookie.getI(), id)) 
				    {
				    	isExist = true;//cookie里面存在该商品id
				    	if (product.getStore() != null && (product.getFreezeStore() + previousCartItemCookie.getQ()+quantity) > product.getStore()) {
				    		map.put(STATUS, "error");
				    		map.put("message", "添加购物车失败,商品库存不足!");
				    		return map;
				    	}
				    	previousCartItemCookie.setQ(previousCartItemCookie.getQ() + quantity);
				    }
				    cartItemCookieList.add(previousCartItemCookie);
				
				    totalQuantity += previousCartItemCookie.getQ();
				    totalPrice =  cartItemProduct.getPrice().multiply(new BigDecimal(previousCartItemCookie.getQ())).add(totalPrice);
				}
			}
			
			if (!isExist) {
				
		    	if (product.getStore() != null && (product.getFreezeStore() +quantity) > product.getStore()) {
		    		map.put(STATUS, "error");
		    		map.put("message", "添加购物车失败,商品库存不足!");
		    		return map;
		    	}
				
		    	CartItemCookie cartItemCookie = new CartItemCookie();
		    	cartItemCookie.setI(id);
		    	cartItemCookie.setQ(quantity);
		    	cartItemCookieList.add(cartItemCookie);
		    	
		    	totalQuantity += quantity;
		    	totalPrice =  product.getPrice().multiply(new BigDecimal(quantity.toString())).add(totalPrice);
		    }
			
			String jsonArray=JsonUtil.toJson(cartItemCookieList);
			Cookie cookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, jsonArray);
			cookie.setPath("/");
//			cookie.setPath(request.getContextPath() + "/");
			cookie.setMaxAge(CartItemCookie.CART_ITEM_LIST_COOKIE_MAX_AGE);
			response.addCookie(cookie);
		}
		map.put("status", SUCCESS);
		map.put("message", "添加至购物车成功！");
		map.put("totalQuantity", totalQuantity);
		map.put("totalPrice", totalPrice);
		return map;
	}
	

	@RequestMapping("/ajaxClear")
	@ResponseBody	
	public ModelMap ajaxClear(ModelMap map,HttpServletRequest request,HttpServletResponse response)
	{	
		Members loginMember = getLoginMember();
		if (loginMember == null) {
			Cookie cookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, null);
			cookie.setPath(request.getContextPath() + "/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} 
		else {
			Set<CartItem> cartItemSet = loginMember.getCartItemSet();
			if (cartItemSet != null) {
				for (CartItem cartItem : cartItemSet) {
					cartItemService.delete(cartItem);
				}
			}
		}
		map.put("message", "购物车清空成功!");
		map.put(STATUS, "success");
		return map;
	}
	
	
	@RequestMapping("/ajaxDelete")
	@ResponseBody	
	public ModelMap ajaxDelete(ModelMap map,@CookieValue(value=CartItemCookie.CART_ITEM_LIST_COOKIE_NAME,required=false) String cartItemList,@RequestParam("id") String id,
			HttpServletRequest request,HttpServletResponse response) {
		Members loginMember = getLoginMember();
		Integer totalQuantity=0;// 商品总数
		Integer totalPoint=0;// 总积分
		BigDecimal totalPrice = new BigDecimal("0");
		if (loginMember == null) {
			if (!StringUtils.isEmpty(cartItemList)) 
			{
				List<CartItemCookie> cartItemCookieList=JsonUtil.Json2List(cartItemList, new TypeReference<List<CartItemCookie>>(){});
				
				for(CartItemCookie cartItemCookie:cartItemCookieList){
					if (StringUtils.equals(cartItemCookie.getI(), id)) 
					{
						cartItemCookieList.remove(cartItemCookie);
						break;
					}
					else 
					{
						Product product = productService.load(cartItemCookie.getI());
						
						if(product==null)
							continue;
						
						totalQuantity += cartItemCookie.getQ();
						if (systemConfigUtil.getPointType() == PointType.orderAmount) {
							totalPoint = product.getPoint() * cartItemCookie.getQ() + totalPoint;
						}
						totalPrice = product.getPrice().multiply(new BigDecimal(cartItemCookie.getQ())).add(totalPrice);
					}
				}
				
				Cookie newCookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, JsonUtil.toJson(cartItemCookieList));
				newCookie.setPath(request.getContextPath() + "/");
				newCookie.setMaxAge(CartItemCookie.CART_ITEM_LIST_COOKIE_MAX_AGE);
				response.addCookie(newCookie);
		   }
		}
		else 
		{
			Set<CartItem> cartItemSet = loginMember.getCartItemSet();
			if (cartItemSet != null) {
				for (CartItem cartItem : cartItemSet) {
					if (StringUtils.equals(cartItem.getProduct().getId(), id)) {
						cartItemService.delete(cartItem);
					} else {
						Product product = cartItem.getProduct();
						totalQuantity += cartItem.getQuantity();
						if (systemConfigUtil.getPointType() == PointType.productSet) {
							totalPoint = product.getPoint() * cartItem.getQuantity() + totalPoint;
						}
						totalPrice = product.getPrice().multiply(new BigDecimal(cartItem.getQuantity())).add(totalPrice);
					}
				}
			}
		}
//		totalPrice = systemConfigUtil.getOrderScaleBigDecimal(totalPrice);
		if (systemConfigUtil.getPointType() == PointType.orderAmount) {
			totalPoint = totalPrice.divide(new BigDecimal(systemConfigUtil.getPointScale().toString())).setScale(0, RoundingMode.DOWN).intValue();
		}
		
		String totalPriceString = systemConfigUtil.getCurrencyFormatString(totalPrice);
		map.put("totalQuantity", totalQuantity.toString());
		map.put("totalPoint", totalPoint.toString());
		map.put("totalPrice", totalPriceString);
		map.put(STATUS, "success");
		map.put("message", "商品删除成功！");
		return map;
	}	

	// 购物车项列表
	@RequestMapping("/list")
	@NeedNavigation
	public String list(ModelMap map,@CookieValue(value="cartItemList",required=false) String cartItemList) {
		
//		Members loginMember=getLoginMember();
		Members loginMemberInfo=getLoginMember();
		Members loginMember = memberService.getMemberByUsername(loginMemberInfo.getUsername());
		
		Integer totalQuantity = 0;
		Integer totalPoint = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		List<CartItem> localcartItemList = new ArrayList<CartItem>();
		
		if (loginMember == null) 
		{
			if (!StringUtils.isEmpty(cartItemList)) 
			{
				List<CartItemCookie> cartItemCookieList=JsonUtil.Json2List(cartItemList, new TypeReference<List<CartItemCookie>>(){});
				for (CartItemCookie cartItemCookie : cartItemCookieList) 
				{
					Product product = productService.get(cartItemCookie.getI());
					if (product != null) 
					{
						totalQuantity += cartItemCookie.getQ();
						if (systemConfigUtil.getPointType() == PointType.productSet) {
							totalPoint = product.getPoint() * cartItemCookie.getQ() + totalPoint;
						}
						totalPrice = product.getPrice().multiply(new BigDecimal(cartItemCookie.getQ().toString())).add(totalPrice);
						
						CartItem cartItem = new CartItem();
						cartItem.setProduct(product);
						cartItem.setQuantity(cartItemCookie.getQ());
						localcartItemList.add(cartItem);
					}
				}
			}
		} 
		else 
		{
			Set<CartItem> cartItemSet = loginMember.getCartItemSet();
			if (cartItemSet != null) 
			{
				localcartItemList = new ArrayList<CartItem>(cartItemSet);
				for (CartItem cartItem : cartItemSet) {
					totalQuantity += cartItem.getQuantity();
					if (systemConfigUtil.getPointType() == PointType.productSet) 
					{
						totalPoint = cartItem.getProduct().getPoint() * cartItem.getQuantity() + totalPoint;
					}
					totalPrice = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())).add(totalPrice);
				}
			}
		}
//		totalPrice = systemConfigUtil.getOrderScaleBigDecimal(totalPrice);
		if (systemConfigUtil.getPointType() == PointType.orderAmount) {
			totalPoint = totalPrice.divide(new BigDecimal(systemConfigUtil.getPointScale())).setScale(0, RoundingMode.DOWN).intValue();
		}
		
		map.put("cartItemList", localcartItemList);
		map.put("currencyFormat", systemConfigUtil.getCurrencyFormat());
		map.put("totalQuantity", totalQuantity.toString());
		map.put("totalPoint", totalPoint.toString());
		map.put("totalPrice", totalPrice);
		
//		map.put("setting", systemConfigUtil);
		map.put("loginMember", loginMember);
		
		return "shop/cart_item_list";
	}	
	

	/**
	 * 修改购物车项
	 * @return
	 */
	@RequestMapping("/ajaxEdit")
	@ResponseBody
	public ModelMap ajaxEdit(ModelMap map,@CookieValue(value=CartItemCookie.CART_ITEM_LIST_COOKIE_NAME,required=false) String cartItemList,
			@RequestParam("id") String id,@RequestParam("quantity") Integer quantity,
			HttpServletRequest request,HttpServletResponse response) {
		if (quantity == null || quantity < 1) {
			quantity = 1;
		}
		
		Members loginMember = getLoginMember();
		Integer totalQuantity = 0;
		Integer totalPoint = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		BigDecimal subtotalPrice = new BigDecimal(0);//修改后的价格
		
		if (loginMember != null)
		{
			Set<CartItem> cartItemSet = loginMember.getCartItemSet();
			if (cartItemSet != null) {
				for (CartItem cartItem : cartItemSet) {
					Product product = cartItem.getProduct();
					if (StringUtils.equals(id, cartItem.getProduct().getId())) {
						cartItem.setQuantity(quantity);
						if (product.getStore() != null && (product.getFreezeStore() + cartItem.getQuantity()) > product.getStore()) {
							map.put("message", "商品库存不足！");
							return map;
						}
						cartItemService.update(cartItem);
						subtotalPrice = cartItem.getSubtotalPrice();
					}
					totalQuantity += cartItem.getQuantity();
					if (systemConfigUtil.getPointType() == PointType.productSet) {
						totalPoint = product.getPoint() * cartItem.getQuantity() + totalPoint;
					}
					totalPrice = product.getPrice().multiply(new BigDecimal(cartItem.getQuantity())).add(totalPrice);
				}
			}
		}
		else if(StringUtils.isNotEmpty(cartItemList)) //用户没有登录,且cookie里面有商品
	    {
			List<CartItemCookie> cartItemCookieList = JsonUtil.Json2List(cartItemList, new TypeReference<List<CartItemCookie>>(){});
			for (CartItemCookie cartItemCookie : cartItemCookieList) 
			{
				Product product = productService.get(cartItemCookie.getI());
				if (product != null) 
				{
				    if (StringUtils.equals(id, cartItemCookie.getI()))//根据id，找到了要修改数量的商品
				    {
				    	if (product.getStore() != null && (product.getFreezeStore() + quantity) > product.getStore()) {
							map.put("message", "商品库存不足！");
							return map;
						}
						cartItemCookie.setQ(quantity);
						subtotalPrice = product.getPrice().multiply(new BigDecimal(quantity));
				    }
					totalQuantity += cartItemCookie.getQ();
					if (systemConfigUtil.getPointType() == PointType.productSet)
						totalPoint += product.getPoint() * cartItemCookie.getQ();
					
					totalPrice = product.getPrice().multiply(new BigDecimal(cartItemCookie.getQ())).add(totalPrice);
				}
				else
				{
					cartItemCookieList.remove(cartItemCookie);
				}
				addCookies(request, response, JsonUtil.toJson(cartItemCookieList));
			}
	    }
		
		if (systemConfigUtil.getPointType() == PointType.orderAmount) {
			totalPoint = totalPrice.divide(new BigDecimal(systemConfigUtil.getPointScale())).setScale(0, RoundingMode.DOWN).intValue();
		}
		
		map.put("subtotalPrice", subtotalPrice);
		map.put("totalQuantity", totalQuantity);
		map.put("totalPoint", totalPoint);
		map.put("currencyFormat", systemConfigUtil.getCurrencyFormat());
		map.put("totalPrice", totalPrice);
		map.put(STATUS, "success");
		return map;
	}
	
	private void addCookies(HttpServletRequest request,HttpServletResponse response,String value)
	{
		Cookie cookie = new Cookie(CartItemCookie.CART_ITEM_LIST_COOKIE_NAME, value);
		cookie.setPath(request.getContextPath() + "/");
		cookie.setMaxAge(CartItemCookie.CART_ITEM_LIST_COOKIE_MAX_AGE);
		response.addCookie(cookie);
	}
	
	
	/*// 购物车项列表
	@InputConfig(resultName = "error")
	@SuppressWarnings("unchecked")
	public String ajaxList() {
		List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
		Member loginMember = getLoginMember();
		totalQuantity = 0;
		totalPrice = new BigDecimal("0");
		if (loginMember == null) {
			Cookie[] cookies = getRequest().getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (StringUtils.equalsIgnoreCase(cookie.getName(), CartItemCookie.CART_ITEM_LIST_COOKIE_NAME)) {
						if (StringUtils.isNotEmpty(cookie.getValue())) {
							JsonConfig jsonConfig = new JsonConfig();
							jsonConfig.setRootClass(CartItemCookie.class);
							JSONArray jsonArray = JSONArray.fromObject(cookie.getValue());
							List<CartItemCookie> cartItemCookieList = (List<CartItemCookie>) JSONSerializer.toJava(jsonArray, jsonConfig);
							for (CartItemCookie cartItemCookie : cartItemCookieList) {
								Product product = productService.load(cartItemCookie.getI());
								if (product != null) {
									totalQuantity += cartItemCookie.getQ();
									totalPrice = product.getPreferentialPrice(getLoginMember()).multiply(new BigDecimal(cartItemCookie.getQ().toString())).add(totalPrice);
									DecimalFormat decimalFormat = new DecimalFormat(getPriceCurrencyFormat());
									String priceString = decimalFormat.format(product.getPreferentialPrice(getLoginMember()));
									Map<String, String> jsonMap = new HashMap<String, String>();
									jsonMap.put("name", product.getName());
									jsonMap.put("price", priceString);
									jsonMap.put("quantity", cartItemCookie.getQ().toString());
									jsonMap.put("htmlFilePath", product.getHtmlFilePath());
									jsonList.add(jsonMap);
								}
							}
						}
					}
				}
			}
		} else {
			Set<CartItem> cartItemSet = loginMember.getCartItemSet();
			if (cartItemSet != null) {
				for (CartItem cartItem : cartItemSet) {
					Product product = cartItem.getProduct();
					totalQuantity += cartItem.getQuantity();
					totalPrice = product.getPreferentialPrice(getLoginMember()).multiply(new BigDecimal(cartItem.getQuantity().toString())).add(totalPrice);
					DecimalFormat decimalFormat = new DecimalFormat(getPriceCurrencyFormat());
					String priceString = decimalFormat.format(cartItem.getProduct().getPreferentialPrice(getLoginMember()));
					Map<String, String> jsonMap = new HashMap<String, String>();
					jsonMap.put("name", product.getName());
					jsonMap.put("price", priceString);
					jsonMap.put("quantity", cartItem.getQuantity().toString());
					jsonMap.put("htmlFilePath", cartItem.getProduct().getHtmlFilePath());
					jsonList.add(jsonMap);
				}
			}
			
			Set<FieldBlockCartItem> fieldBlockCartItemSet = loginMember.getFieldBlockCartItemSet();
			if (fieldBlockCartItemSet != null) {
				for (FieldBlockCartItem cartItem : fieldBlockCartItemSet) {
					FieldBlock fieldBlock = cartItem.getFieldBlock();
					totalQuantity += cartItem.getQuantity();
					totalPrice = cartItem.getPreferentialPrice().multiply(new BigDecimal(cartItem.getQuantity().toString())).add(totalPrice);
					DecimalFormat decimalFormat = new DecimalFormat(getPriceCurrencyFormat());
					String priceString = decimalFormat.format(cartItem.getPreferentialPrice());
					Map<String, String> jsonMap = new HashMap<String, String>();
					jsonMap.put("name", fieldBlock.getName());
					jsonMap.put("price", priceString);
					jsonMap.put("quantity", cartItem.getQuantity().toString());
					jsonMap.put("htmlFilePath", "/fieldmember/field_block!content.action?id=" + fieldBlock.getId());
					jsonList.add(jsonMap);
				}
			}
		}
		totalPrice = SystemConfigUtil.getOrderScaleBigDecimal(totalPrice);
		DecimalFormat decimalFormat = new DecimalFormat(getOrderUnitCurrencyFormat());
		String totalPriceString = decimalFormat.format(totalPrice);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("totalQuantity", totalQuantity.toString());
		jsonMap.put("totalPrice", totalPriceString);
		jsonList.add(0, jsonMap);
		JSONArray jsonArray = JSONArray.fromObject(jsonList);
		return ajaxJson(jsonArray.toString());
	}
*/

}
