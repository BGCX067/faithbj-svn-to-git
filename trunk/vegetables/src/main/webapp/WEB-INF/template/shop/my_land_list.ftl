<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>我的土地列表<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
		<div class="total">共计: ${(landlist?size)!} 块土地</div>
			<div class="goodsPictureList">
					<ul class="goodsListDetail">
						<#list landlist as order>
							<#list order.orderItemSet as productItem>
							<li<#if (productItem_index + 1) % 4 == 0> class="end"</#if>>
							<#if productItem.product.isLand==true>
								<img src="${base}/vegetables/inventory/${productItem.product.productImageList[0].thumbnailImageView}" alt="${productItem.product.name}" />
								<div class="goodsTitle">
									<#if productItem.product.name?length lt 24>${productItem.product.name}<#else>${productItem.product.name[0..24]}...</#if>
								</div>
								<div class="goodsBottom">
									<div class="goodsPrice">
										<span class="price">${productItem.product.price?string(currencyFormat)}</span>
									</div>
									<div class="buttonArea">
										<a href="${base}/cjlhome/farmland/${productItem.product.id}" class="addCartItemButton">查看我的耕种地块</a>
									</div>
								</div>
								</#if>
							</li>
							</#list>
						</#list>
						<#if (landlist?size == 0)!>
                			<li class="noRecord">非常抱歉,没有找到相关商品!</li>
                		</#if>
					</ul>
					<div class="blank"></div>
         			<#assign parameterMap = {"orderType": (orderType)!, "viewType": (viewType)!} />
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