package com.faithbj.shop.web.controller.shop;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.ProductService;
import com.faithbj.shop.support.annotation.NeedNavigation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 前台Action类 - 收藏夹
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@Controller
@RequestMapping("/cjlhome/favorite")
public class FavoriteController extends BaseShopController {

	private static final long serialVersionUID = 6297956848773319710L;
	
	private static final Integer pageSize = 10;// 商品收藏每页显示数
	
	private Product product;

	@Resource
	private MemberService memberService;
	@Resource
	private ProductService productService;

	// 商品收藏列表
	@RequestMapping("list")
	@NeedNavigation
	public String list(ModelMap map,HttpServletRequest request) {
		addSystemConfig(map);
		Members loginMember = getLoginMember(request);
		if (pager == null) {
			pager = new Pager();
		}
		pager.setPageSize(pageSize);
		pager = productService.getFavoriteProductPager(loginMember, pager);
		map.put("loginMember", loginMember);
		map.put("pager", pager);
		return "shop/favorite_list";
	}

	// 添加收藏商品
	@RequestMapping(value="ajaxAdd",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap ajaxAdd(ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
		product = productService.load(id);
		if (!product.getIsMarketable()) {
			map.put(STATUS, "error");
			map.put("message", "此商品已下架!");
			return map;
		}
		Members loginMember = getLoginMember(request);
		Set<Product> favoriteProductSet = (loginMember.getFavoriteProductSet() == null ? new HashSet<Product>() : loginMember.getFavoriteProductSet());
		if (favoriteProductSet.contains(product)) {
			map.put(STATUS, "error");
			map.put("message", "您已经收藏过此商品!");
			return map;
		} else {
			favoriteProductSet.add(product);
			memberService.update(loginMember);
			map.put(STATUS, "success");
			map.put("message", "商品收藏成功!");
			return map;
		}
	}

	// 删除收藏商品
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap delete (ModelMap map,HttpServletRequest request,@RequestParam("id") String id) {
		product = productService.load(id);
		Members loginMember = getLoginMember(request);
		Set<Product> favoriteProductSet = loginMember.getFavoriteProductSet();
		if (!favoriteProductSet.contains(product)) {
			map.put(STATUS, "error");
			map.put("message", "参数错误!");
			return map;
		}
		favoriteProductSet.remove(product);
		memberService.update(loginMember);
//		map.put(redirectUrl, "shop/favorite_list");
		map.put(STATUS, "success");
		map.put("message", "删除成功!");
		return map;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
