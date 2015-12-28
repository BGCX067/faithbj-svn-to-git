package com.faithbj.shop.action.admin;

import javax.annotation.Resource;

import com.faithbj.shop.entity.Payment;
import com.faithbj.shop.service.PaymentService;

import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * 后台Action类 - 支付
 * <p>Copyright: Copyright (c) 2011</p> 
 * 
 * <p>Company: www.faithbj.com</p>
 * 
 * @author 	faithbj
 * @date 	2011-12-16
 * @version 1.0
 */

@ParentPackage("admin")
public class PaymentAction extends BaseAdminAction {

	private static final long serialVersionUID = -4276446217262552805L;

	private Payment payment;

	@Resource
	private PaymentService paymentService;

	// 查看
	public String view() {
		payment = paymentService.load(id);
		return VIEW;
	}

	// 列表
	public String list() {
		pager = paymentService.findByPager(pager);
		return LIST;
	}

	// 删除
	public String delete() {
		paymentService.delete(ids);
		return ajaxJsonSuccessMessage("删除成功！");
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
