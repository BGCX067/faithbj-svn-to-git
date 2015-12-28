package com.faithbj.shop.web.controller.shop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faithbj.shop.model.configuration.Pager;
import com.faithbj.shop.model.entity.Accounts;
import com.faithbj.shop.model.entity.Members;
import com.faithbj.shop.model.entity.Seed;
import com.faithbj.shop.service.AccountService;
import com.faithbj.shop.service.SeedService;
import com.faithbj.shop.support.annotation.NeedNavigation;

@Controller
@RequestMapping("/cjlhome/account")
public class AccountController extends BaseShopController{

	@Resource
	private AccountService accountService;
	@Resource
	private SeedService seedService;
	
	@RequestMapping("list")
	@NeedNavigation
	private String list(ModelMap map,Pager pager){
		Members member = getLoginMember();
		pager = accountService.findByPager(pager,member);
		map.put("pager", pager);
		map.put("loginMember", member);
		return "shop/accounts_list";
	}
	
	@RequestMapping("new")
	@NeedNavigation
	private String add(ModelMap map){
		List<Seed> seedlist = new ArrayList<Seed>();
		Members member = getLoginMember();
		map.put("loginMember", member);
		seedlist = seedService.getAll();
		map.put("seeds", seedlist);
		return "shop/accounts_input";
	}
	
	@RequestMapping("{accountId}/edit")
	@NeedNavigation
	private String edit(ModelMap map,@PathVariable String accountId){
		Members member = getLoginMember();
		map.put("loginMember", member);
		Accounts account = accountService.get(accountId);
		List<Seed> seedlist = new ArrayList<Seed>();
		seedlist = seedService.getAll();
		map.put("seeds", seedlist);
		map.put("account", account);
		return "shop/accounts_input";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	private String save(ModelMap map,Accounts account){
		Members member = getLoginMember();
		account.setMember(member);
		Seed seed = seedService.get(account.getSeed().getId());
		account.setSeed(seed);
		accountService.save(account);
		map.put("redirectUrl", "/cjlhome/account/list");
		return SUCCESS;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	private String update(ModelMap map,Accounts account){
		Accounts accounts = accountService.get(account.getId());
		Seed seed = seedService.get(account.getSeed().getId());
		account.setSeed(seed);
		BeanUtils.copyProperties(account, accounts, new String[] {"id","createDate","modifyDate","member"});
		accountService.update(accounts);
		map.put("redirectUrl", "/cjlhome/account/list");
		return SUCCESS;
	}
}
