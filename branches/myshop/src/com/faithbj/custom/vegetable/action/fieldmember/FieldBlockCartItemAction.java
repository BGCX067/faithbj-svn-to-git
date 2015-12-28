package com.faithbj.custom.vegetable.action.fieldmember;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.custom.vegetable.entity.FieldBlock;
import com.faithbj.custom.vegetable.entity.FieldBlockCartItem;
import com.faithbj.custom.vegetable.entity.MemberAuth;
import com.faithbj.custom.vegetable.service.FieldBlockCartItemService;
import com.faithbj.custom.vegetable.service.FieldBlockService;
import com.faithbj.shop.action.shop.BaseShopAction;
import com.faithbj.shop.bean.SystemConfig.PointType;
import com.faithbj.shop.entity.CartItem;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.util.SystemConfigUtil;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@ParentPackage("shop")
public class FieldBlockCartItemAction extends BaseShopAction
{
	private static final long serialVersionUID = -1011629481509715547L;
	
	private Integer quantity;
	private List<FieldBlockCartItem> fieldBlockCartItemList = new ArrayList<FieldBlockCartItem>();

	private Integer totalQuantity;// 商品总数
	private Integer totalPoint;// 总积分
	private BigDecimal totalPrice;// 总计商品价格
	
	private String isTrusteeFeeEnabled = null;
	private String isDeliveryFeeEnabled = null;
	
	@Resource
	private FieldBlockService fieldBlockService;
	
	@Resource
	private FieldBlockCartItemService fieldBlockCartItemService = null;
	
	// 添加购物车项
	@Validations(
		requiredStrings = {
			@RequiredStringValidator(fieldName = "id", message = "ID不允许为空!")
		}
	)
	@InputConfig(resultName = "error")
	public String ajaxAdd() {
		Member member = this.getLoginMember();
		List<MemberAuth> authList = member.getAuthList();
		boolean isAuth = false;
		for(MemberAuth auth : authList){
			if(auth.getType().equals("0")){
				isAuth = true;
				break;
			}
		}
		if(!isAuth){
			return ajaxJsonErrorMessage("您没有承包土地的权限！");
		}
		FieldBlock fieldBlock = fieldBlockService.load(id);
		if (!fieldBlock.getIsMarketable()) {
			return ajaxJsonErrorMessage("此商品已下架!");
		}
		if (quantity == null || quantity < 1) {
			quantity = 1;
		}
		Integer totalQuantity = 0;// 总计商品数量
		BigDecimal totalPrice = new BigDecimal("0");// 总计商品价格
		Member loginMember = getLoginMember();
		if (loginMember == null) {
			return ajaxJsonErrorMessage("请先登录");
		} else {
			boolean isExist = false;
			Set<FieldBlockCartItem> previousFieldBlockCartItemList = loginMember.getFieldBlockCartItemSet();
			if (previousFieldBlockCartItemList != null) {
				for (FieldBlockCartItem previousCartItem : previousFieldBlockCartItemList) {
					if (StringUtils.equals(previousCartItem.getFieldBlock().getId(), id)) {
						isExist = true;
						previousCartItem.setQuantity(previousCartItem.getQuantity() + quantity);
						if (fieldBlock.getStore() != null && previousCartItem.getQuantity() > fieldBlock.getStore()) {
							return ajaxJsonErrorMessage("添加购物车失败，商品库存不足!");
						}
						this.fieldBlockCartItemService.update(previousCartItem);
					}
					totalQuantity += previousCartItem.getQuantity();
					totalPrice =  previousCartItem.getPreferentialPrice().multiply(new BigDecimal(previousCartItem.getQuantity().toString())).add(totalPrice);
				}
			}
			Set<CartItem> previousCartItemList = loginMember.getCartItemSet();
			if (previousCartItemList.size()!=0) {
				for (CartItem previousCartItem : previousCartItemList) {
					
					totalQuantity += previousCartItem.getQuantity();
					totalPrice =  previousCartItem.getPreferentialPrice().multiply(new BigDecimal(previousCartItem.getQuantity().toString())).add(totalPrice);
				}
			}
			if (!isExist) {
				FieldBlockCartItem cartItem = new FieldBlockCartItem();
				cartItem.setMember(loginMember);
				cartItem.setFieldBlock(fieldBlock);
				cartItem.setQuantity(quantity);
				cartItem.setIsTrusteeFeeEnabled("true".equals(isTrusteeFeeEnabled));
				cartItem.setIsDeliveryFeeEnabled("true".equals(isDeliveryFeeEnabled));
				if (fieldBlock.getStore() != null && cartItem.getQuantity() > fieldBlock.getStore()) {
					return ajaxJsonErrorMessage("添加购物车失败，商品库存不足!");
				}
				this.fieldBlockCartItemService.save(cartItem);
				totalQuantity += quantity;
				totalPrice =  cartItem.getPreferentialPrice().multiply(new BigDecimal(quantity.toString())).add(totalPrice);
			}
		}
		totalPrice = SystemConfigUtil.getOrderScaleBigDecimal(totalPrice);
		DecimalFormat decimalFormat = new DecimalFormat(getOrderUnitCurrencyFormat());
		String totalPriceString = decimalFormat.format(totalPrice);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGE, "添加至购物车成功！");
		jsonMap.put("totalQuantity", totalQuantity.toString());
		jsonMap.put("totalPrice", totalPriceString);
		return ajaxJson(jsonMap);
	}
	
//	// 修改购物车项
//	@InputConfig(resultName = "error")
//	public String ajaxEdit() {
//		if (quantity == null || quantity < 1) {
//			quantity = 1;
//		}
//		Member loginMember = getLoginMember();
//		totalQuantity = 0;
//		totalPoint = 0;
//		totalPrice = new BigDecimal("0");
//		BigDecimal subtotalPrice = new BigDecimal("0");
//		if (loginMember == null) {
//			return ajaxJsonErrorMessage("请先登录");
//		} else {
//			Set<FieldBlockCartItem> fieldBlockCartItemSet = loginMember.getFieldBlockCartItemSet();
//			if (fieldBlockCartItemSet != null) {
//				for (FieldBlockCartItem cartItem : fieldBlockCartItemSet) {
//					FieldBlock fieldBlock = cartItem.getFieldBlock();
//					if (StringUtils.equals(id, cartItem.getFieldBlock().getId())) {
//						cartItem.setQuantity(quantity);
//						if (fieldBlock.getStore() != null && cartItem.getQuantity() > fieldBlock.getStore()) {
//							return ajaxJsonErrorMessage("商品库存不足！");
//						}
//						fieldBlockCartItemService.update(cartItem);
//						subtotalPrice = cartItem.getSubtotalPrice();
//					}
//					totalQuantity += cartItem.getQuantity();
//					if (getSystemConfig().getPointType() == PointType.productSet) {
//						totalPoint = fieldBlock.getPoint() * cartItem.getQuantity() + totalPoint;
//					}
//					totalPrice = cartItem.getPreferentialPrice().multiply(new BigDecimal(cartItem.getQuantity().toString())).add(totalPrice);
//				}
//			}
//		}
//		DecimalFormat orderUnitCurrencyFormat = new DecimalFormat(getOrderUnitCurrencyFormat());
//		DecimalFormat orderCurrencyFormat = new DecimalFormat(getOrderCurrencyFormat());
//		totalPrice = SystemConfigUtil.getOrderScaleBigDecimal(totalPrice);
//		if (getSystemConfig().getPointType() == PointType.orderAmount) {
//			totalPoint = totalPrice.divide(new BigDecimal(getSystemConfig().getPointScale().toString())).setScale(0, RoundingMode.DOWN).intValue();
//		}
//		String subtotalPriceString = orderCurrencyFormat.format(subtotalPrice);
//		String totalPriceString = orderUnitCurrencyFormat.format(totalPrice);
//		Map<String, String> jsonMap = new HashMap<String, String>();
//		jsonMap.put("subtotalPrice", subtotalPriceString);
//		jsonMap.put("totalQuantity", totalQuantity.toString());
//		jsonMap.put("totalPoint", totalPoint.toString());
//		jsonMap.put("totalPrice", totalPriceString);
//		jsonMap.put(STATUS, SUCCESS);
//		return ajaxJson(jsonMap);
//	}
//	
//	// 删除购物车项
//	@InputConfig(resultName = "error")
//	@SuppressWarnings("unchecked")
//	public String ajaxDelete() {
//		Member loginMember = getLoginMember();
//		totalQuantity = 0;
//		totalPoint = 0;
//		totalPrice = new BigDecimal("0");
//		if (loginMember == null) {
//			return ajaxJsonErrorMessage("请先登录");
//		} else {
//			Set<FieldBlockCartItem> cartItemSet = loginMember.getFieldBlockCartItemSet();
//			if (cartItemSet != null) {
//				for (FieldBlockCartItem cartItem : cartItemSet) {
//					if (StringUtils.equals(cartItem.getFieldBlock().getId(), id)) {
//						fieldBlockCartItemService.delete(cartItem);
//					} else {
//						FieldBlock fieldBlock = cartItem.getFieldBlock();
//						totalQuantity += cartItem.getQuantity();
//						if (getSystemConfig().getPointType() == PointType.productSet) {
//							totalPoint = fieldBlock.getPoint() * cartItem.getQuantity() + totalPoint;
//						}
//						totalPrice = cartItem.getPreferentialPrice().multiply(new BigDecimal(cartItem.getQuantity().toString())).add(totalPrice);
//					}
//				}
//			}
//		}
//		totalPrice = SystemConfigUtil.getOrderScaleBigDecimal(totalPrice);
//		if (getSystemConfig().getPointType() == PointType.orderAmount) {
//			totalPoint = totalPrice.divide(new BigDecimal(getSystemConfig().getPointScale().toString())).setScale(0, RoundingMode.DOWN).intValue();
//		}
//		DecimalFormat decimalFormat = new DecimalFormat(getOrderUnitCurrencyFormat());
//		String totalPriceString = decimalFormat.format(totalPrice);
//		Map<String, String> jsonMap = new HashMap<String, String>();
//		jsonMap.put("totalQuantity", totalQuantity.toString());
//		jsonMap.put("totalPoint", totalPoint.toString());
//		jsonMap.put("totalPrice", totalPriceString);
//		jsonMap.put(STATUS, SUCCESS);
//		jsonMap.put(MESSAGE, "商品删除成功！");
//		return ajaxJson(jsonMap);
//	}
	
	public Integer getQuantity()
	{
		return quantity;
	}
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}
	public List<FieldBlockCartItem> getFieldBlockCartItemList()
	{
		return fieldBlockCartItemList;
	}
	public void setFieldBlockCartItemList(List<FieldBlockCartItem> fieldBlockCartItemList)
	{
		this.fieldBlockCartItemList = fieldBlockCartItemList;
	}
	public String getIsTrusteeFeeEnabled()
	{
		return isTrusteeFeeEnabled;
	}
	public void setIsTrusteeFeeEnabled(String isTrusteeFeeEnabled)
	{
		this.isTrusteeFeeEnabled = isTrusteeFeeEnabled;
	}
	public String getIsDeliveryFeeEnabled()
	{
		return isDeliveryFeeEnabled;
	}
	public void setIsDeliveryFeeEnabled(String isDeliveryFeeEnabled)
	{
		this.isDeliveryFeeEnabled = isDeliveryFeeEnabled;
	}

	public Integer getTotalQuantity()
	{
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity)
	{
		this.totalQuantity = totalQuantity;
	}

	public Integer getTotalPoint()
	{
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint)
	{
		this.totalPoint = totalPoint;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}
}
