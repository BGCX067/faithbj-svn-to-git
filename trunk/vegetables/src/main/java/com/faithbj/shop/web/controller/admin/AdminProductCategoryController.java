package com.faithbj.shop.web.controller.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.ArticleCategory;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.service.ProductCategoryService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 后台Action类 - 商品分类
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
@RequestMapping(value="/faith/goods_category")
public class AdminProductCategoryController extends BaseAdminController {

	private static final long serialVersionUID = 3066159260207583127L;
	
	private String parentId;
	private ProductCategory productCategory;
	private List<ProductCategory> productCategoryTreeList ;
	private final String OPERRATE_RETURN_URL="faith/goods_category/list";
	private final String HAS_CHILDREN_CATEGORY="此商品分类存在下级分类，删除失败!";
	private final String HAS_GOODS="此商品分类下存在商品，删除失败!";
	@Resource
	private ProductCategoryService productCategoryService;

	// 添加
	@RequestMapping("/add")
	public String add(ModelMap map) {
		productCategoryTreeList = getProductCategoryTreeList();
		map.put("productCategoryTreeList", productCategoryTreeList);
		return "admin/product_category_input";
	}

	// 编辑
	@RequestMapping("/edit")
	public String edit(@RequestParam String id,ModelMap map) {
		productCategory = productCategoryService.load(id);
		productCategoryTreeList = getProductCategoryTreeList();
		map.put("productCategoryTreeList", productCategoryTreeList);
		map.put("productCategory", productCategory);
		map.put("id", id);
		return "admin/product_category_input";
	}

	// 列表
	@RequestMapping("/list")
	public String list(ModelMap data) {
		pager = productCategoryService.findByPager(pager);
		productCategoryTreeList = getProductCategoryTreeList();
		data.put("pager", pager);
		data.put("productCategoryTreeList", productCategoryTreeList);
		return "admin/product_category_list";
	}
	
	//删除
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id,ModelMap map) {
		ProductCategory productCategory = productCategoryService.load(id);
		Set<ProductCategory> childrenProductCategorySet = productCategory.getChildren();
		if (childrenProductCategorySet != null && childrenProductCategorySet.size() > 0) {
			map.put("errorMessages", HAS_CHILDREN_CATEGORY);
			return ERROR;
		}
		Set<Product> productSet = productCategory.getProductSet();
		if (productSet != null && productSet.size() > 0) {
			map.put("errorMessages", HAS_GOODS);
			return ERROR;
		}
		productCategoryService.delete(id);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 保存
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(ProductCategory productCategory,@RequestParam("parentId") String parentId,ModelMap map) {
		if (StringUtils.isNotEmpty(parentId)) {
			ProductCategory parent = productCategoryService.load(parentId);
			productCategory.setParent(parent);
		} else {
			productCategory.setParent(null);
		}
		productCategoryService.save(productCategory);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}

	// 更新
	@RequestMapping(value="/update")
	public String update(@RequestParam String id,ProductCategory productCategory,ModelMap map) {
		ProductCategory persistent = productCategoryService.load(id);
		BeanUtils.copyProperties(productCategory, persistent, new String[]{"id", "createDate", "modifyDate", "path", "parent", "children", "productSet"});
		productCategoryService.update(persistent);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategory> getProductCategoryTreeList() {
		productCategoryTreeList = productCategoryService.getProductCategoryTreeList();
		return productCategoryTreeList;
	}



}
