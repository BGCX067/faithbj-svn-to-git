package com.faithbj.shop.web.controller.admin;

import javax.annotation.Resource;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.DeliveryCorp;
import com.faithbj.shop.model.entity.DeliveryType;
import com.faithbj.shop.service.DeliveryCorpService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台Action类 - 物流公司
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping("/faith/delivery_corp/")
public class AdminDeliveryCorpController extends BaseAdminController {

	private static final long serialVersionUID = -5162370084764617443L;

	private static String INPUT="admin/delivery_corp_input";
	private static String LIST="admin/delivery_corp_list";
	private final String OPERRATE_RETURN_URL="faith/delivery_corp/list";
	@Resource
	private DeliveryCorpService deliveryCorpService;
	
//	// 是否已存在 ajax验证
//	public String checkName() {
//		String oldValue = getParameter("oldValue");
//		String newValue = deliveryCorp.getName();
//		if (deliveryCorpService.isUnique("name", oldValue, newValue)) {
//			return ajaxText("true");
//		} else {
//			return ajaxText("false");
//		}
//	}

	// 添加
	@RequestMapping("/new")
	public String add(ModelMap map) {
		map.put("isAddAction", true);
		return INPUT;
	}

	// 编辑
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable String id,ModelMap map) {
		DeliveryCorp deliveryCorp = deliveryCorpService.load(id);
		map.put("deliveryCorp", deliveryCorp);
		map.put("isAddAction", false);
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
		pager = deliveryCorpService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}

	// 删除
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id,ModelMap map) {
		deliveryCorpService.delete(id);
		map.put("redirectUrl", "../"+OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "deliveryCorp.name", message = "物流公司名称不允许为空!")
//		}, 
//		requiredFields = {
//			@RequiredFieldValidator(fieldName = "deliveryCorp.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "deliveryCorp.orderList", min = "0", message = "排序必须为零或正整数!")
//		},
//		urls = {
//			@UrlValidator(fieldName = "deliveryCorp.url", message = "网址格式错误!")
//		}
//	)
//	@InputConfig(resultName = "error")
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(DeliveryCorp deliveryCorp,ModelMap map) {
		deliveryCorp.setDeliveryTypeSet(null);
		deliveryCorpService.save(deliveryCorp);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "deliveryCorp.name", message = "物流公司名称不允许为空!")
//		}, 
//		requiredFields = {
//			@RequiredFieldValidator(fieldName = "deliveryCorp.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "deliveryCorp.orderList", min = "0", message = "排序必须为零或正整数!")
//		},
//		urls = {
//			@UrlValidator(fieldName = "deliveryCorp.url", message = "网址格式错误!")
//		}
//	)
//	@InputConfig(resultName = "error")
	@RequestMapping(value="/update")
	public String update(@RequestParam String id,DeliveryCorp deliveryCorp,ModelMap map) {
		DeliveryCorp persistent = deliveryCorpService.load(id);
		BeanUtils.copyProperties(deliveryCorp, persistent, new String[]{"id", "createDate", "modifyDate", "deliveryTypeSet"});
		deliveryCorpService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

}
