package com.faithbj.shop.web.controller.admin;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.configuration.AcessoryAtrributeAndParameter;
import com.faithbj.shop.model.entity.AttributeType;
import com.faithbj.shop.model.entity.ProductAttribute;
import com.faithbj.shop.model.entity.ProductType;
import com.faithbj.shop.service.ProductAttributeService;
import com.faithbj.shop.service.ProductTypeService;

/**
 * 后台Action类 - 商品类型
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */
@Controller
@RequestMapping(value="/faith/goods_type")
public class AdminProductTypeController extends BaseAdminController {

	private static final long serialVersionUID = 8895838200173152426L;

	private final String OPERRATE_RETURN_URL="faith/goods_type/list";
	private final String HAS_CHILDREN_CATEGORY="此商品类型存在属性列表，删除失败!";
	private final String HAS_GOODS="此商品类型下存在商品，删除失败!";
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductAttributeService productAttributeService;

	// 是否已存在 ajax验证
	@RequestMapping(value="checkName", method= RequestMethod.GET)
	public String checkName(@RequestParam String oldValue,@RequestParam String newValue) {
//		String oldValue = getParameter("oldValue");
//		String newValue = productType.getName();
		if (productTypeService.isUnique("name", oldValue, newValue)) {
			return "success";
		} else {
			return "error";
		}
	}

	// 添加
	@RequestMapping(value="new", method= RequestMethod.GET)
	public String add(ModelMap map) {
		map.put("isAddAction", true);
		map.put("attributeTypeList", Arrays.asList(AttributeType.values()));
		
		return "admin/product_type_input";
	}
	
	// 编辑
	@RequestMapping(value="{id}/edit", method= RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable String id) {
		ProductType productType = productTypeService.get(id);
		map.put("productType", productType);
		map.put("attributeTypeList", Arrays.asList(AttributeType.values()));//传一个enum参数，作为下拉筛选项
		map.put("id", productType.getId());
		return "admin/product_type_input";
	}

	// 列表
	@RequestMapping(value="list", method= RequestMethod.GET)
	public String list(ModelMap data) {
		pager = productTypeService.findByPager(pager);
		data.put("pager", pager);
		return "admin/product_type_list";
	}

	// 删除
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable String id,ModelMap map) {
//		ProductType productType = productTypeService.load(id);
//		Set<ProductAttribute> productAttributeList = productType.getProductAttributeSet();
//		if(productAttributeList!=null && productAttributeList.size()>0){
//			map.put("errorMessages", HAS_CHILDREN_CATEGORY);
//			return ERROR;
//		}
//		Set<Product> productSet = productType.getProductSet();
//		if (productSet != null && productSet.size() > 0) {
//			map.put("errorMessages", HAS_GOODS);
//			return ERROR;
//		}
//		productTypeService.delete(id);
//		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存

	@RequestMapping(value="save", method= RequestMethod.POST)
	public String save(ProductType productType,AcessoryAtrributeAndParameter acessoryAtrributes,ModelMap map) {
		
		productType.setProductAttributeSet(new  HashSet<ProductAttribute>(acessoryAtrributes.getProductAttributeList()));
		for(ProductAttribute pa:acessoryAtrributes.getProductAttributeList()){
			pa.setProductType(productType);
		}
		
		productType.setProductParameters(acessoryAtrributes.getProductParameterList());
		
//		productType.setProductAttributeSet(null);
		productTypeService.save(productType);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="/update")
	public String update(@RequestParam String id,ProductType productType,ModelMap map) {
		ProductType persistent = productTypeService.load(id);
		BeanUtils.copyProperties(productType, persistent, new String[] {"id", "createDate", "modifyDate", "productAttributeList", "productParameterStore","productSet"});
		productTypeService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

}
