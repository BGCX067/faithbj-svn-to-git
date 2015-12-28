<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>预存款充值<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/template/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
<script type="text/javascript">
$().ready( function() {
	
	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $depositRechargeForm = $("#depositRechargeForm");
	
	// 表单验证
	$depositRechargeForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"rechargeAmount": {
				required: true,
				positive: true
			},
			"paymentConfig.id": "required"
		},
		messages: {
			"rechargeAmount": {
				required: "请填写充值金额",
				positive: "充值金额必须为正数"
			},
			"paymentConfig.id": "请选择支付方式"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body depositRecharge">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">预存款充值</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="depositRechargeForm" action="payment!submit.action" method="post">
						<input type="hidden" name="paymentType" value="recharge" />
						<table class="inputTable">
							<tr>
								<th>
									充值金额: 
								</th>
								<td>
									<input type="text" name="rechargeAmount" class="formText" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									支付方式: 
								</th>
								<td>
									<table class="paymentConfigTable">
										<#list nonDepositOfflinePaymentConfigList as paymentConfig>
											<tr>
												<td class="nameTd">
													<label>
														<input type="radio" name="paymentConfig.id" value="${paymentConfig.id}" /> ${paymentConfig.name}
													</label>
												</td>
												<td>
													<#if paymentConfig.paymentFeeType == "scale" && paymentConfig.paymentFee != 0>
														[支付手续费费率: ${paymentConfig.paymentFee}%]
													<#elseif paymentConfig.paymentFeeType == "fixed" && paymentConfig.paymentFee != 0>
														[支付手续费: ${paymentConfig.paymentFee?string(currencyFormat)}]
													</#if>
													<#if paymentConfig.description??>
														<p>${paymentConfig.description}</p>
													</#if>
												</td>
											</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submitButton" value="立刻充值" hidefocus />
								</td>
							</tr>
						</table>
					</form>
					<div class="blank"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">
</body>
</html>