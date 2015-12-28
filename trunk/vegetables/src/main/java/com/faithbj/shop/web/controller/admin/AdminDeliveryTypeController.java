package com.faithbj.shop.web.controller.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.DeliveryCorp;
import com.faithbj.shop.model.entity.DeliveryType;
import com.faithbj.shop.model.entity.DeliveryType.DeliveryMethod;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.Product.WeightUnit;
import com.faithbj.shop.service.DeliveryCorpService;
import com.faithbj.shop.service.DeliveryTypeService;

/**
 * 后台Action类 - 配送方式
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/faith/delivery_type/")
public class AdminDeliveryTypeController extends BaseAdminController {

	private static final long serialVersionUID = -2431663334945495069L;

	private static String INPUT="admin/delivery_type_input";
	private static String LIST="admin/delivery_type_list";
	private final String OPERRATE_RETURN_URL="faith/delivery_type/list";
	@Resource
	private DeliveryTypeService deliveryTypeService;
	@Resource
	private DeliveryCorpService deliveryCorpService;
	
	private List deliveryMethodList = new ArrayList();
			
	private List weightUnitList = new ArrayList();
			
	private List<DeliveryCorp> allDeliveryCorpList = new ArrayList<DeliveryCorp>();
			
	@RequestMapping("menu")
	public String menu(){
		return "admin/menu_delivery";
	}
			
//	// 是否已存在 ajax验证
//	public String checkName() {
//		String oldValue = getParameter("oldValue");
//		String newValue = deliveryType.getName();
//		if (deliveryTypeService.isUnique("name", oldValue, newValue)) {
//			return ajaxText("true");
//		} else {
//			return ajaxText("false");
//		}
//	}

	// 添加

	public List getDeliveryMethodList() {
		return Arrays.asList(DeliveryType.DeliveryMethod.values());
	}

	public List getWeightUnitList() {
		return Arrays.asList(Product.WeightUnit.values());
	}


	public List<DeliveryCorp> getAllDeliveryCorpList() {
		return deliveryCorpService.getAll();
	}

	@RequestMapping("/new")
	public String add(ModelMap map) {
		
		map.put("weightUnitList", getWeightUnitList());
		map.put("allDeliveryCorpList", getAllDeliveryCorpList());
		map.put("deliveryMethodList", getDeliveryMethodList());
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable String id,ModelMap map) {
		map.put("weightUnitList", getWeightUnitList());
		map.put("allDeliveryCorpList", getAllDeliveryCorpList());
		map.put("deliveryMethodList", getDeliveryMethodList());
		DeliveryType deliveryType = deliveryTypeService.load(id);
		map.put("isAddAction", false);
		map.put("deliveryType", deliveryType);
		return INPUT;
	}

	// 列表
	@RequestMapping("/list")
	public String list(ModelMap map) {
		if (pager == null) {
			pager = new Pager();
			pager.setOrderType(OrderType.asc);
			pager.setOrderBy("orderList");
		}
		pager = deliveryTypeService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}

	// 删除
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id,ModelMap map) {
		long totalCount = deliveryTypeService.getTotalCount();
		if (totalCount==1) {
			map.put("errorMessages", "删除失败!必须至少保留一个配送方式");
			return ERROR;
		}
		deliveryTypeService.delete(id);
		map.put("redirectUrl", "../"+OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(DeliveryType deliveryType,ModelMap map) {
		if (deliveryType.getFirstWeightPrice().compareTo(new BigDecimal("0")) < 0) {
			map.put("errorMessages", "首重价格不允许小于0");
			return ERROR;
		}
		if (deliveryType.getContinueWeightPrice().compareTo(new BigDecimal("0")) < 0) {
			map.put("errorMessages", "续重价格不允许小于0");
			return ERROR;
		}
		if (StringUtils.isEmpty(deliveryType.getDefaultDeliveryCorp().getId())) {
			deliveryType.setDefaultDeliveryCorp(null);
		}
		deliveryTypeService.save(deliveryType);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="/update")
	public String update(@RequestParam String id,DeliveryType deliveryType,ModelMap map) {
		if (deliveryType.getFirstWeightPrice().compareTo(new BigDecimal("0")) < 0) {
			map.put("errorMessages", "首重价格不允许小于0");
			return ERROR;
		}
		if (deliveryType.getContinueWeightPrice().compareTo(new BigDecimal("0")) < 0) {
			map.put("errorMessages", "续重价格不允许小于0");
			return ERROR;
		}
		if (StringUtils.isEmpty(deliveryType.getDefaultDeliveryCorp().getId())) {
			deliveryType.setDefaultDeliveryCorp(null);
		}
		DeliveryType persistent = deliveryTypeService.load(id);
		BeanUtils.copyProperties(deliveryType, persistent, new String[]{"id", "createDate", "modifyDate", "orderSet", "shippingSet", "reshipSet"});
		deliveryTypeService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 获取所有物流公司
	public List<DeliveryCorp> getAllDeliveryCorp() {
		return deliveryCorpService.getAll();
	}

	// 获取所有配送类型
	public List<DeliveryMethod> getAllDeliveryMethod() {
		List<DeliveryMethod> allDeliveryMethod = new ArrayList<DeliveryMethod>();
		for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
			allDeliveryMethod.add(deliveryMethod);
		}
		return allDeliveryMethod;
	}

	// 获取所有重量单位
	public List<WeightUnit> getAllWeightUnit() {
		List<WeightUnit> allWeightUnit = new ArrayList<WeightUnit>();
		for (WeightUnit weightUnit : WeightUnit.values()) {
			allWeightUnit.add(weightUnit);
		}
		return allWeightUnit;
	}
	

}
