package com.faithbj.shop.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.service.EventService;

@ParentPackage("admin")
public class EventAction extends BaseAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6499173860306305505L;
	@Resource
	private EventService eventService;
	
	public String list() {
		pager = eventService.findByPager(pager);
		return "manageCard";
	}
}
