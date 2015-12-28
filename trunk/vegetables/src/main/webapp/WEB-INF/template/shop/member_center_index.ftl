<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员中心 - Powered By faithbj</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<#include "/common/common_css.ftl">
<#include "/common/common_js.ftl">
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body memberCenterIndex">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">会员中心首页</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<td colspan="4">
								您目前是[${loginMember.memberRank.name}]
								<#if loginMember.memberRank.preferentialScale != 100>
									<span class="red">[优惠百分比: ${loginMember.memberRank.preferentialScale}%]</span>
								</#if>
							</td>
						</tr>
						<tr>
							<th>帐户总积分</th>
							<td>${loginMember.point}</td>
							<th>订单总数量</th>
							<td>
								${loginMember.orderSet?size}&nbsp;&nbsp;
								<a href="order/list">[查看订单列表]</a>
							</td>
						</tr>
						<tr>
							<th>预存款余额</th>
							<td>${loginMember.deposit?string(currencyFormat)}</td>
							<th>未读消息数</th>
							<td>
								${unreadMessageCount}&nbsp;&nbsp;
								<a href="/cjlhome/message/inbox">[查看收件箱]</a>
							</td>
						</tr>
						<tr>
							<th>注册日期</th>
							<td>${loginMember.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
							<th>最后登录IP</th>
							<td>${loginMember.loginIp}</td>
						</tr>
					</table>
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>商品名称</th>
							<th>订单编号</th>
							<th>下单时间</th>
							<th>订单金额</th>
							<th>订单状态</th>
						</tr>
						<#list loginMember.orderSet as order>
							<tr>
								<td width="350">
									<a href="/cjlhome/order/view?id=${order.id}">
										<span title="<#list order.productItemSet as productItem><#if productItem_index != 0>、</#if>${productItem.name}</#list>">
											<#list order.orderItemSet as orderItem>
												<#if orderItem_index != 0>、</#if>
												${orderItem.productName}
												<#if orderItem_index == 3 && orderItem_has_next>
													...<#break />
												</#if>
											</#list>
										</span>
									</a>
								</td>
								<td>
									<a href="/cjlhome/order/view?id=${order.id}">${order.orderSn}</a>
								</td>
								<td>
									<span title="${order.createDate?string("yyyy-MM-dd HH:mm:ss")}">${order.createDate}</span>
								</td>
								<td>
									${order.totalAmount?string(currencyFormat)}
								</td>
								<td>
									<#if order.orderStatus != "completed" && order.orderStatus != "invalid">
										[<@spring.message "PaymentStatus." + order.paymentStatus/>]
										[<@spring.message "ShippingStatus." + order.shippingStatus/>]
									<#else>
										[<@spring.message "OrderStatus." + order.orderStatus/>]
									</#if>
								</td>
							</tr>
							<#if (order_index + 1 > 10)>
								<#break />
							</#if>
						</#list>
						<tr>
							<td colspan="5">
								<a href="/cjlhome/order/list">更多订单>></a>
							</td>
						</tr>
					</table>
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