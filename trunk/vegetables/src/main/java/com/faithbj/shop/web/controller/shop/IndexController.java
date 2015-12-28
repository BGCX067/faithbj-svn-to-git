package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faithbj.shop.model.entity.Article;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.service.ArticleService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.ProductCategoryService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;

@Controller
public class IndexController extends BaseShopController  implements Serializable{

	private static final long serialVersionUID = 1L;


	@Resource
	private ArticleService articleService;
	@Resource
	private ProductService productService;
	@Resource
	private ProductCategoryService productCategoryService;
	
    @RequestMapping(value="/index",method = RequestMethod.GET)
    @NeedNavigation
    public String index(ModelMap data) {
		data.put("bestProductList", productService.getBestProductList(Product.MAX_BEST_PRODUCT_LIST_COUNT));
    	data.put("hotProductList", productService.getHotProductList(Product.MAX_HOT_PRODUCT_LIST_COUNT));
    	
		List<ProductCategory> allProductCategory = productCategoryService.getRootProductCategoryList();
		data.put("productCategoryList", allProductCategory);
		
		Map<String, List<Product>> newProductMap = new HashMap<String, List<Product>>();
		for (ProductCategory productCategory : allProductCategory) {
			newProductMap.put(productCategory.getId(), productService.getNewProductList(productCategory, Product.MAX_NEW_PRODUCT_LIST_COUNT));
		}
		data.put("newProductMap", newProductMap);		
		
		data.put("newArticleList", articleService.getNewArticleList(Article.MAX_NEW_NEWS_LIST_COUNT));
    	
//		HttpSession session = request.getSession();
//		String id = (String) session.getAttribute(Members.LOGIN_MEMBER_ID_SESSION_NAME);
//		if(id!=null){
//			Members loginMember = memberService.get(id);
//			data.put("loginMember", loginMember);
//		}
        return "shop/index";
    }
}
