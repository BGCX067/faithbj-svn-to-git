package com.faithbj.shop.action.shop;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.faithbj.shop.entity.PaymentConfig;
import com.faithbj.shop.entity.PresentCard;
import com.faithbj.shop.service.DepositService;
import com.faithbj.shop.service.PaymentConfigService;
import com.faithbj.shop.service.PresentCardService;

/**
 * 前台Action类 - 预存款
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: www.faithbj.com
 * </p>
 * 
 * @author faithbj
 * @date 2011-12-16
 * @version 1.0
 */

@ParentPackage("member")
public class DepositAction extends BaseShopAction {

	private static final long serialVersionUID = -3091246496095700007L;
	private List<PresentCard> result;

	@Resource
	private DepositService depositService;
	@Resource
	private PaymentConfigService paymentConfigService;
	@Resource
	private PresentCardService presentCardService;

	// 预存款列表
	public String list() {
		pager = depositService.getDepositPager(getLoginMember(), pager);
		return "list";
	}

	// 预存款充值
	public String recharge() {
		return "recharge";
	}

	// 获取支付配置（不包含预存款、线下支付方式）
	public List<PaymentConfig> getNonDepositOfflinePaymentConfigList() {
		return paymentConfigService.getNonDepositOfflinePaymentConfigList();
	}

	// 个人礼品卡列表
	public String getUserCardList() {
		String mid = getLoginMember().getId();
		result = presentCardService.getUserCardList(mid);
		return "mypresentcard";
	}

	// 礼品卡号码激活
	public String updateUserCardNum() {
		String memberid = getLoginMember().getId();
		int pid = Integer.parseInt(getRequest().getParameter("id"));
		String all_path = ServletActionContext.getRequest().getRequestURL().toString();
		String path = all_path.substring(0, all_path.lastIndexOf("/")) + "/";
		presentCardService.updateUserCardNum(memberid, pid);
		try {
			ServletActionContext.getResponse().sendRedirect(path + "deposit!getUserCardList.action");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "mypresentcard";
	}
	
	
	public List<PresentCard> getResult() {
		return result;
	}

	public void setResult(List<PresentCard> result) {
		this.result = result;
	}


}
