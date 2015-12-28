package com.faithbj.shop.web.controller.shop;

import java.io.Serializable;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Farmland;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Seed;
import com.faithbj.shop.service.FarmLandService;
import com.faithbj.shop.support.annotation.NeedNavigation;


@Controller
@RequestMapping("/cjlhome/farmland")
public class FarmLandController extends BaseShopController  implements Serializable{

	private static final long serialVersionUID = -4969421249817468001L;

	
	@Resource
	private FarmLandService farmlandService;
	
	//耕种块土地列表
	@RequestMapping("{productid}")
	@NeedNavigation
	public String farmland(ModelMap map,Pager pager,@PathVariable String productid){
		Members loginMember = getLoginMember();
		pager = farmlandService.findByPager(productid,pager);
		map.put("pager", pager);
		map.put("loginMember", loginMember);
		return "shop/my_farmland_list";
	}
	
	//播种
	@RequestMapping("farming/{id}")
	@NeedNavigation
	public String farming(ModelMap map,Seed seed,@PathVariable String id){
		Members loginMember = getLoginMember();
		Farmland farmland = farmlandService.get(id);
		farmland.setSeed(seed);
		farmlandService.update(farmland);
		map.put("farmland", farmland);
		map.put("loginMember", loginMember);
		map.put("success", true);
		
		
		return "shop/my_farmland_list";
	}
	
	//清理
		@RequestMapping("clean/{id}")
		@NeedNavigation
		public String clean(ModelMap map,@PathVariable String id){
			Members loginMember = getLoginMember();
			Farmland farmland = farmlandService.get(id);
			farmland.setSeed(null);
			farmlandService.update(farmland);
			map.put("farmland", farmland);
			map.put("loginMember", loginMember);
			map.put("success", true);
			
			
			return "shop/my_farmland_list";
		}
	
}
