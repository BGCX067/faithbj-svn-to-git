package com.faithbj.shop.action.admin;

import java.util.List;

import javax.annotation.Resource;

import com.faithbj.shop.entity.Online;
import com.faithbj.shop.service.OnlineService;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.struts2.convention.annotation.ParentPackage;


/**
 * 后台Action类 - 在线问答
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("admin")
public class OnlineAction extends BaseAdminAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057934167718769069L;
	private Online online;
	private List<Online> onlinelist;

	@Resource
	private OnlineService onlineService;
	

	// 编辑
	public String edit() {
		
		online = onlineService.load(id);
		return INPUT;
	}

	// 未回答的列表
	public String list() {
		System.out.println("后台");
		onlinelist=onlineService.getAllA();
		return LIST;
	}

	// 删除
//	public String delete() {
//		ProductCategory productCategory = productCategoryService.load(id);
//		Set<ProductCategory> childrenProductCategorySet = productCategory.getChildren();
//		redirectionUrl = "product_category!list.action";
//		if (childrenProductCategorySet != null && childrenProductCategorySet.size() > 0) {
//			addActionError("此商品分类存在下级分类，删除失败!");
//			return ERROR;
//		}
//		Set<Product> productSet = productCategory.getProductSet();
//		if (productSet != null && productSet.size() > 0) {
//			addActionError("此商品分类下存在商品，删除失败!");
//			return ERROR;
//		}
//		productCategoryService.delete(id);
//		return SUCCESS;
//	}

	// 保存
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "productCategory.name", message = "分类名称不允许为空!")
//		}, 
//		requiredFields = { 
//			@RequiredFieldValidator(fieldName = "productCategory.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "productCategory.orderList", min = "0", message = "排序必须为零或正整数!")
//		}
////	)
//	@InputConfig(resultName = "error")
//	public String save() {
//		if (StringUtils.isNotEmpty(parentId)) {
//			ProductCategory parent = productCategoryService.load(parentId);
//			productCategory.setParent(parent);
//		} else {
//			productCategory.setParent(null);
//		}
//		productCategoryService.save(productCategory);
//		redirectionUrl = "product_category!list.action";
//		return SUCCESS;
//	}

//	// 更新
//	@Validations(
//		requiredStrings = { 
//			@RequiredStringValidator(fieldName = "productCategory.name", message = "分类名称不允许为空!")
//		}, 
//		requiredFields = { 
//			@RequiredFieldValidator(fieldName = "productCategory.orderList", message = "排序不允许为空!")
//		},
//		intRangeFields = {
//			@IntRangeFieldValidator(fieldName = "productCategory.orderList", min = "0", message = "排序必须为零或正整数!")
//		}
//	)
	@InputConfig(resultName = "error")
	public String update() {
		Online online1=onlineService.load(id);
		online1.setStatus("1");
		online1.setAnswer(online.getAnswer());
		onlineService.update(online1);
		redirectionUrl = "online!list.action";
		return SUCCESS;
	}

//	public String getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(String parentId) {
//		this.parentId = parentId;
//	}
//
//	public ProductCategory getProductCategory() {
//		return productCategory;
//	}
//
//	public void setProductCategory(ProductCategory productCategory) {
//		this.productCategory = productCategory;
//	}
//
//	public List<ProductCategory> getProductCategoryTreeList() {
//		productCategoryTreeList = productCategoryService.getProductCategoryTreeList();
//		return productCategoryTreeList;
//	}
//
//	public void setProductCategoryTreeList(List<ProductCategory> productCategoryTreeList) {
//		this.productCategoryTreeList = productCategoryTreeList;
//	}

	public Online getOnline() {
		return online;
	}

	public void setOnline(Online online) {
		this.online = online;
	}

	public List<Online> getOnlinelist() {
		return onlinelist;
	}

	public void setOnlinelist(List<Online> onlinelist) {
		this.onlinelist = onlinelist;
	}
	
	
	
	

}
