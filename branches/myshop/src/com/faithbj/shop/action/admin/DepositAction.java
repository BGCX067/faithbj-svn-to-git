package com.faithbj.shop.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.bean.Pager;
import com.faithbj.shop.entity.Event;
import com.faithbj.shop.entity.Member;
import com.faithbj.shop.service.EventService;
import com.faithbj.shop.service.MemberService;
import com.faithbj.shop.service.PresentCardService;
import com.faithbj.shop.util.CommonUtil;
import com.faithbj.shop.util.DateUtils;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

@ParentPackage("admin")
public class DepositAction extends BaseAdminAction {
	
	private static final long serialVersionUID = 4002168355939385230L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	private List<Event> list;
	private List<Member> memberList;
	private Event event;
	private Pager pager1;
	private String[] memberids;

	@Resource
	private PresentCardService presentCardService;
	@Resource
	private MemberService memberService;
	@Resource
	private EventService eventService;

	/**
	 * 进入优惠活动生成
	 * @return
	 */
	public String createCard() {
		return "createCard";
	}

	/**
	 * 优惠活动管理
	 * @return
	 */
	public String manageCard() {
		pager = eventService.findByPager(pager);
		return "manageCard";
	}

	/**
	 * 保存优惠活动
	 * @return
	 */
	@InputConfig(resultName = "error")
	public String saveCard() {
		Date now = new Date();
		String noww = sdf.format(now);
		event.setCreateDate(new Date());
		event.setExpiredate(DateUtils.getAYearLater(noww));// 礼品卡有效期为1年
		presentCardService.saveCard(event);
		flushCache();
		redirectionUrl = "deposit!createCard.action";
		return SUCCESS;
	}

	/**
	 * 编辑优惠活动
	 * @return
	 */
	public String edit() {
		String id = getRequest().getParameter("id");
		event = this.eventService.load(id);
		return INPUT;
	}

	/**
	 * 更新优惠活动
	 * @return
	 */
	public String update() {
		eventService.update(event);
		flushCache();
		redirectionUrl = "deposit!manageCard.action";
		return SUCCESS;
	}

	/**
	 * 删除优惠活动
	 * @return
	 */
	public String delete() {
		this.eventService.delete(ids);
		flushCache();
		redirectionUrl = "deposit!manageCard.action";
		return ajaxJsonSuccessMessage("删除成功！");
	}

	/**
	 * 会员列表
	 * @return
	 */
	public String list() {
		list = eventService.getAll();
		pager = memberService.findByPager(pager);
		return LIST;
	}

	/**
	 * 给会员下发礼品卡
	 * @return
	 */
	public String releaseCard() {
		String[] memberids = CommonUtil.memberidConverter(getRequest()
				.getParameter("memberids"));
		String eventid = getRequest().getParameter("eventid");
		presentCardService.savereleaseCard(memberids, eventid);
		flushCache();
		return ajaxJsonSuccessMessage("下发成功！");
	}

	/**
	 * 更新页面缓存
	 */
	private void flushCache() {
		Cache cache = ServletCacheAdministrator.getInstance(
				getRequest().getSession().getServletContext()).getCache(
				getRequest(), PageContext.APPLICATION_SCOPE);
		cache.flushAll(new Date());
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getList() {
		return list;
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public Pager getPager1() {
		return pager1;
	}

	public void setPager1(Pager pager1) {
		this.pager1 = pager1;
	}

	public String[] getMemberids() {
		return memberids;
	}

	public void setMemberids(String[] memberids) {
		this.memberids = memberids;
	}
}
