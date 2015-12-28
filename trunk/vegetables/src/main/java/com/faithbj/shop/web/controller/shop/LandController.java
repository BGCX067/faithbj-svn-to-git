package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.service.ProductCategoryService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;
import com.faithbj.shop.utils.SystemConfigUtil;

/**
 * 前台Action类 - 商品
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
public class LandController extends BaseShopController  implements Serializable{

	private static final long serialVersionUID = -4969421249817468001L;

//	private ProductCategory productCategory;
	private String orderType;// 排序类型
	private String viewType;// 查看类型
	
//	private List<ProductCategory> rootProductCategoryList;
//	
//	private List<Product> bestProductList;
//	private List<Product> hotProductList;
//	private List<Product> newProductList;
//	private List<ProductCategory> pathList;
	
	@Resource
	private ProductService productService;
//	@Resource
//	private ProductCategoryService productCategoryService;
//	@Resource
//	private SystemConfigUtil systemConfigUtil;
	
	
	
	@RequestMapping("/land")
    @NeedNavigation
	public String list(ModelMap model,Pager pager) {
		
//		newProductList = productService.getNewProductList(productCategory, Product.MAX_NEW_PRODUCT_LIST_COUNT);
//		List<ProductCategory> pathList = productCategoryService.getProductCategoryPathList(productCategory);
		
		pager.setOrderBy("createDate");
		pager.setOrderType(OrderType.asc);
		
		pager= productService.findByPager(true, pager);
		
//		pager.setPageSize(pager.getPageSize()>0?pager.getPageSize():Product.MAX_NEW_PRODUCT_LIST_COUNT);
//		pager.setTotalCount(pager.getTotalCount()>0?pager.getTotalCount():Product.MAX_NEW_PRODUCT_LIST_COUNT);
	
		
		model.put("pager", pager);
		return "shop/farmland_picture_list";
		
/*		pager.setProperty(null);
		try {
			pager.setKeyword(null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		if (StringUtils.equalsIgnoreCase(orderType, "priceAsc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.asc);
		} else if (StringUtils.equalsIgnoreCase(orderType, "priceDesc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.desc);
		} else if (StringUtils.equalsIgnoreCase(orderType, "dateAsc")) {
			pager.setOrderBy("createDate");
			pager.setOrderType(OrderType.asc);
		} else {
			pager.setOrderBy(null);
			pager.setOrderType(null);
		}*/
	}
	
	public String search() throws Exception {
		List<Product> bestProductList = productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT);
		List<Product> hotProductList = productService.getHotProductList(Product.MAX_HOT_PRODUCT_LIST_COUNT);
		List<Product> newProductList = productService.getNewProductList(Product.MAX_NEW_PRODUCT_LIST_COUNT);
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Product.DEFAULT_PRODUCT_LIST_PAGE_SIZE);
		}
		
		if (StringUtils.equalsIgnoreCase(orderType, "priceAsc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.asc);
		} else if (StringUtils.equalsIgnoreCase(orderType, "priceDesc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.desc);
		} else if (StringUtils.equalsIgnoreCase(orderType, "dateAsc")) {
			pager.setOrderBy("createDate");
			pager.setOrderType(OrderType.asc);
		} else {
			pager.setOrderBy(null);
			pager.setOrderType(null);
		}
		
		pager = productService.search(pager);
		
		if (StringUtils.equalsIgnoreCase(viewType, "tableType")) {
			return "table_search";
		} else {
			return "picture_search";
		}
	}
	
}
