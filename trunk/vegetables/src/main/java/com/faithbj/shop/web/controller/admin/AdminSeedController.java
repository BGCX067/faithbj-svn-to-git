package com.faithbj.shop.web.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.configuration.Pager.OrderType;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Farmland;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductCategory;
import com.faithbj.shop.model.entity.Seed;
import com.faithbj.shop.service.SeedService;
import com.faithbj.shop.utils.DateUtils;

/**
 * 后台Action类 - 种子
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-28
 * @version 1.0
 */

@Controller
@RequestMapping(value="/faith/seed")
public class AdminSeedController extends BaseAdminController {

	private static final long serialVersionUID = -2080980180511054311L;

	@Resource
	private SeedService seedService;
	
	private final String LIST="admin/seed_list";
	private final String INPUT="admin/seed_input";
	private final String OPERRATE_RETURN_URL="faith/seed/list";
	private final String HAS_FARMLAND="有耕地种植了此种子，删除失败!";
	
	@RequestMapping("/menu")
	public String index(){
		return "admin/menu_land";
	}

	//弹出
//	@InputConfig(resultName = "error")
//	public String popup() {
//		pager = this.seedService.findByPager(pager);
//		return "popup";
//	}
	

	//种子列表
	@RequestMapping("/list")
	public String list(ModelMap map) {

		pager = seedService.findByPager(pager);
		map.put("pager", pager);
		return LIST;
	}
	
//	// 删除
//	public String delete() {
//		seedService.delete(ids);
//		return ajaxJsonSuccessMessage("删除成功！");
//	}
	
	// 添加
	@RequestMapping("/add")
	public String add(ModelMap map) {
		return INPUT;
	}

	// 编辑
	@RequestMapping("/edit")
	public String edit(ModelMap map,@RequestParam String id) {
		Seed seed = seedService.load(id);
		map.put("seed", seed);
		map.put("id", seed.getId());
		return INPUT;
	}
	
	// 是否已存在 ajax验证
//	public String checkName() {
//		String oldValue = getParameter("oldValue");
//		String newValue;
//		try {
//				newValue = new String(seed.getName().getBytes("ISO-8859-1"),"UTF-8");
//			
//		} catch (UnsupportedEncodingException e) {
//			newValue = "";
//			return ajaxText("false");
//		}
//		if (seedService.isUnique("name", oldValue, newValue)) {
//			return ajaxText("true");
//		} else {
//			return ajaxText("false");
//		}
//	}
	//保存
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@RequestParam String name,@RequestParam String startDate,@RequestParam String endDate,ModelMap map) {
		Seed seed = new Seed();
		seed.setSeedName(name);
		seed.setStartDate(DateUtils.getStringToDate(startDate, "yyyy-MM-dd"));
		seed.setEndDate(DateUtils.getStringToDate(endDate, "yyyy-MM-dd"));
		seedService.save(seed);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	//更新
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(ModelMap map,@RequestParam String id,@RequestParam String name,@RequestParam String startDate,@RequestParam String endDate,
			@RequestParam String description,@RequestParam int type) {
		Seed loadSeed = seedService.load(id);
		Seed seed = new Seed();
		seed.setSeedName(name);
		seed.setStartDate(DateUtils.getStringToDate(startDate, "yyyy-MM-dd"));
		seed.setEndDate(DateUtils.getStringToDate(endDate, "yyyy-MM-dd"));
		seed.setDescription(description);
		seed.setType(type);
		BeanUtils.copyProperties(seed, loadSeed, new String[]{"id", "createDate", "modifyDate", "farmland","accounts"});
		seedService.update(loadSeed);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	
	//删除
		@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
		public String delete(@PathVariable String id,ModelMap map) {
			Seed loadSeed = seedService.load(id);
			Set<Farmland> farmLandSet = loadSeed.getFarmland();
			if (farmLandSet != null && farmLandSet.size() > 0) {
				map.put("errorMessages", HAS_FARMLAND);
				return ERROR;
			}
			Set<Accounts> accounts = loadSeed.getAccounts();
			if (accounts != null && accounts.size() > 0) {
				map.put("errorMessages", "有该种子的收获记录，删除失败！");
				return ERROR;
			}
			seedService.delete(id);
			map.put("redirectUrl", OPERRATE_RETURN_URL);
			return SUCCESS;
		}
	

}
