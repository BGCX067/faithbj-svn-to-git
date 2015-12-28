<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>预存款列表<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/vegetables/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body orderList">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">预存款记录 <span class="red">[预存款余额: ${loginMember.deposit?string(currencyFormat)}]</span></div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>操作类型</th>
							<th>存入金额</th>
							<th>支出金额</th>
							<th>当前余额</th>
							<th>日期</th>
						</tr>
						<#list pager.result as deposit>
							<tr>
								<td>
									<#if (deposit.payment?? && deposit.payment.order??)!>
										<a href="order!view.action?id=${deposit.payment.order.id}">
											${action.getText("DepositType." + deposit.depositType)}
										</a>
									<#else>
										${action.getText("DepositType." + deposit.depositType)}
									</#if>
								</td>
								<td>
									<#if deposit.credit != 0>
										${deposit.credit?string(currencyFormat)}
									<#else>
										-
									</#if>
								</td>
								<td>
									<#if deposit.debit != 0>
										${deposit.debit?string(currencyFormat)}
									<#else>
										-
									</#if>
								</td>
								<td>
									${deposit.balance?string(currencyFormat)}
								</td>
								<td>
									<span title="${deposit.createDate?string("yyyy-MM-dd HH:mm:ss")}">${deposit.createDate}</span>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
						<#include "/shop/pager.ftl">
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