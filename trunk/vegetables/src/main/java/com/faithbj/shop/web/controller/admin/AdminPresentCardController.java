package com.faithbj.shop.web.controller.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.PresentCard;
import com.faithbj.shop.model.entity.PresentCardCategory;
import com.faithbj.shop.model.entity.Product;
import com.faithbj.shop.model.entity.ProductAttribute;
import com.faithbj.shop.model.entity.ProductType;
import com.faithbj.shop.service.PresentCardService;

@Controller
@RequestMapping("/faith/presentcard")
public class AdminPresentCardController extends BaseAdminController {

	private static final long serialVersionUID = -13900679721036437L;
	
	@Resource
	PresentCardService presentCardService;
	private final String HAS_MEMBERS="此礼品卡存在所属会员，删除失败!";
	private final String OPERRATE_RETURN_URL="faith/presentcard/list";
	@RequestMapping("/menu")
	public String index(){
		return "admin/menu_presentcard";
	}
	
	@RequestMapping("/new")
	public String add(){
		return "admin/presentcard_input";
	}
	
	@RequestMapping("/list")
	public String list(ModelMap map){
		pager = presentCardService.findByPager(pager);
		map.put("pager", pager);
		return "admin/presentcard_list";
	}
	


	// 删除
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable String id,ModelMap map) {
		PresentCard pc  = presentCardService.load(id);
		Members m = pc.getMemberid();
		if(m!=null){
			map.put("errorMessages", HAS_MEMBERS);
			return ERROR;
		}
		presentCardService.delete(id);
		map.put("redirectUrl", OPERRATE_RETURN_URL);
		return SUCCESS;
	}
	
	@RequestMapping("/generateCards")
	public String generateCards(PresentCardCategory cardCategory,Integer amount){
		
		presentCardService.batchGenerateCards(cardCategory, amount);
		return SUCCESS;
	}

}
