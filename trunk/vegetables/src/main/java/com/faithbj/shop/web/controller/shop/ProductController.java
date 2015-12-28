package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.service.ProductCategoryService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;

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
@RequestMapping("/product")
public class ProductController extends BaseShopController  implements Serializable{

	private static final long serialVersionUID = -4969421249817468001L;
	
	@Resource
	private ProductService productService;
	@Resource
	private ProductCategoryService productCategoryService;
	
	@RequestMapping("/productCategory")
    @NeedNavigation
	public String list(ModelMap map,Pager pager,@RequestParam(value="viewType",required=false) String viewType) {
		addSystemConfig(map);
		if (StringUtils.equalsIgnoreCase(pager.getOrderBy(), "priceAsc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.asc);
		} else if (StringUtils.equalsIgnoreCase(pager.getOrderBy(), "priceDesc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.desc);
		} else {
			pager.setOrderBy("createDate");
			pager.setOrderType(OrderType.desc);
		} 
		pager= productService.findByPager(false, pager);
		map.put("pager", pager);
		
		List<Product> bestProductList = productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT);
		map.put("goodsList", bestProductList);
		
		List<ProductCategory> goodsCategoryTree = productCategoryService.getRootProductCategoryList();
		map.put("goodsCategoryTree", goodsCategoryTree);
		map.put("goodsCategory", null);
		if (StringUtils.equalsIgnoreCase(viewType, "tableType")) {
			return "shop/goods_table_list";
		} else {
			return "shop/goods_picture_list";
		}
	}
	
	
	@RequestMapping("/showland/{id}")
	@NeedNavigation
	public String show(ModelMap map,Pager pager,@PathVariable("id") String id) {
		Product product = productService.get(id);
		
		List<Product> bestProductList = productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT);
		List<ProductCategory> goodsCategoryTree = productCategoryService.getRootProductCategoryList();
		
		map.put("goodsList", bestProductList);
		map.put("goodsCategoryTree", goodsCategoryTree);
		map.put("goods", product);
		
		return "shop/farmland_content";
	}
	
	@NeedNavigation
	@RequestMapping("/productCategory/{id}")
	public String productCategorylist(ModelMap map,Pager pager,@RequestParam(value="viewType",required=false) String viewType,@PathVariable String id) {
		addSystemConfig(map);
		ProductCategory productCategory = null;
		List<Product> bestProductList = new ArrayList<Product>();
		List<Product> hotProductList = new ArrayList<Product>();
		List<Product> newProductList =		new ArrayList<Product>();
		productCategory = productCategoryService.load(id);
		bestProductList = productService.getBestProductList(productCategory, Product.MAX_BEST_PRODUCT_LIST_COUNT);
		hotProductList = productService.getHotProductList(productCategory, Product.MAX_HOT_PRODUCT_LIST_COUNT);
		newProductList = productService.getNewProductList(productCategory, Product.MAX_NEW_PRODUCT_LIST_COUNT);
		List<ProductCategory> pathList = productCategoryService.getProductCategoryPathList(productCategory);
		
		
		List<ProductCategory> goodsCategoryTree = productCategoryService.getRootProductCategoryList();
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Product.DEFAULT_PRODUCT_LIST_PAGE_SIZE);
		}
		pager.setProperty(null);
		try {
			pager.setKeyword(null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if (StringUtils.equalsIgnoreCase(pager.getOrderBy(), "priceAsc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.asc);
		} else if (StringUtils.equalsIgnoreCase(pager.getOrderBy(), "priceDesc")) {
			pager.setOrderBy("price");
			pager.setOrderType(OrderType.desc);
		} else if (StringUtils.equalsIgnoreCase(pager.getOrderBy(), "dateAsc")) {
			pager.setOrderBy("createDate");
			pager.setOrderType(OrderType.asc);
		} else {
			pager.setOrderBy(null);
			pager.setOrderType(null);
		}
		
		pager= productService.getProductPager(productCategory, pager);
		map.put("productCategory", productCategory);
		map.put("bestProductList", bestProductList);
		map.put("hotProductList", hotProductList);
		map.put("newProductList", newProductList);
		map.put("pathList", pathList);
		map.put("goodsCategoryTree", goodsCategoryTree);
		map.put("pager", pager);
		if (StringUtils.equalsIgnoreCase(viewType, "tableType")) {
			return "shop/goods_table_list";
		} else {
			return "shop/goods_picture_list";
		}
	}
	
	@NeedNavigation
	@RequestMapping("/{id}")
	public String productList(ModelMap map,@PathVariable("id") String id) {
		Product product = productService.get(id);
	
		ProductCategory productCategory = productCategoryService.load(product.getProductCategory().getId());
		List<ProductCategory> pathList = productCategoryService.getProductCategoryPathList(productCategory);
		
		List<Product> bestProductList = productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT);
		List<ProductCategory> goodsCategoryTree = productCategoryService.getRootProductCategoryList();
		
		map.put("goodsList", bestProductList);
		map.put("goodsCategoryTree", goodsCategoryTree);
		map.put("goods", product);
		map.put("pathList", pathList);
		
		return "shop/goods_content";
	}
	
	public String search(ModelMap map,@RequestParam(value="viewType",required=false) String viewType) throws Exception {
		return viewType;
		/*bestProductList = productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT);
		hotProductList = productService.getHotProductList(Product.MAX_HOT_PRODUCT_LIST_COUNT);
		newProductList = productService.getNewProductList(Product.MAX_NEW_PRODUCT_LIST_COUNT);
		
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
			return "shop/goods_table_list";
		} else {
			return "shop/goods_picture_list";
		}*/
	}
	
}
