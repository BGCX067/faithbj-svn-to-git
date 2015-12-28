<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地会员中心 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/product_content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/product.js"></script>
<script type="text/javascript" src="${base}/template/vegetable/js/product.js"></script>
<script type='text/javascript'>
	function jump(code)
	{
		window.location.href = '${base}/fieldmember/my_farming_block!list.action?pager.property=rendBlockCode&pager.keyword=' + code;
	}
	
	var rent = ${fieldBlock.rent};
	var trusteeFee = ${fieldBlock.trusteeFee};
	var deliveryFee = ${fieldBlock.deliveryFee};
</script>
</head>
<body class="memberCenter">
	<div id="addCartItemTip" class="addCartItemTip">
		<div class="top">
			<div class="tipClose addCartItemTipClose"></div>
		</div>
		<div class="middle">
			<p>
				<span id="addCartItemTipMessageIcon">&nbsp;</span>
				<span id="addCartItemTipMessage"></span>
			</p>
			<p id="addCartItemTipInfo" class="red"></p>
			<input type="button" class="formButton tipClose" value="继续承包" hidefocus="true" />
			<input type="button" class="formButton" onclick="location.href='${base}/shop/cart_item!list.action'" value="进入购物车" hidefocus="true" />
		</div>
		<div class="bottom"></div>
	</div>
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body memberCenterIndex productContent">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
		
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="home"><span class="icon">&nbsp;</span>首页</a>>
						<a href="${base}/fieldmember/field_block!list.action" class="home"><span class="icon">&nbsp;</span>承包土地</a>>
						<a href='#'>详细信息</a>>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="productTop">
				<div class="productTopLeft">
					<div class="productImage">
	                	<#list fieldBlock.productImageList as list>
	                		<a href="${base}${list.bigProductImagePath}" class="tabContent zoom<#if (list_index > 0)> nonFirst</#if>">
								<img src="${base}${list.smallProductImagePath}" />
							</a>
						</#list>
						<#if fieldBlock.productImageList == null>
	            			<a href="${base}${systemConfig.defaultBigProductImagePath}" class="zoom">
								<img src="${base}${systemConfig.defaultSmallProductImagePath}" />
							</a>
	                	</#if>
                	</div>
					<div class="thumbnailProductImage">
						<a class="prev browse" href="javascript:void(0);" hidefocus="true"></a>
						<div class="scrollable">
							<ul class="items productImageTab">
								<#if (fieldBlock.productImageList == null)!>
									<li>
										<img src="${base}${systemConfig.defaultThumbnailProductImagePath}" />
									</li>
	                        	</#if>
	                        	<#list (fieldBlock.productImageList)! as list>
									<li>
										<img src="${base}${list.thumbnailProductImagePath}" />
									</li>
								</#list>
							</ul>
						</div>
						<a class="next browse" href="javascript:void(0);" hidefocus="true"></a>
					</div>
				</div>
				<div class="productTopRight">
					<h1>
						<#if (fieldBlock.name?length > 50)>${fieldBlock.name[0..46]}...<#else>${fieldBlock.name}</#if>
					</h1>
					<ul class="productAttribute">
                        <li>
                			<strong>年托管费:</strong> ${fieldBlock.trusteeFee?string(priceCurrencyFormat)}
                        </li>
                        <li>
                			<strong>年配送费:</strong> ${fieldBlock.deliveryFee?string(priceCurrencyFormat)}
                        </li>
                        <li>
                			<strong>年租金:</strong> ${fieldBlock.rent?string(priceCurrencyFormat)}
                        </li>
                        <li>
                			<strong>经纬度:</strong> ${fieldBlock.longitudeLatitude}
                        </li>
                        <li>
                			<strong>地址:</strong> ${fieldBlock.address}
                        </li>
					</ul>
					<div class="blank"></div>
					<div class="productInfo">
						<div class="left"></div>
						<div class="right">
							<div class="top">
								总年租金：<span class="price">${(fieldBlock.rent + fieldBlock.trusteeFee + fieldBlock.deliveryFee)?string(priceCurrencyFormat)}</span>
							</div>
							<div class="bottom">
									面积：<span class="weight">30 平方米</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									积分：<span>${fieldBlock.point}</span>
							</div>
						</div>
					</div>
					<div class="blank"></div>
					<div class="productNumber">
						代码: <span>${fieldBlock.code}</span>
					</div>
					<div class="blank"></div>
					<div class="productBuy">
						<div class="buyCount">
							我要承包: <input type="text" id="quantity" value="1" /> 块 
							<input type="checkbox" id="isTrusteeFeeEnabled" checked/>托管
							<input type="checkbox" id="isDeliveryFeeEnabled" checked/>配送
						</div>
						<#if fieldBlock.isOutOfStock>
							<input type="button" class="outOfStockButton" value="" hidefocus="true" />
						<#else>
							<input type="button" class="addCartItemButton addCartItemField {id: '${fieldBlock.id}'}" value="" hidefocus="true" />
						</#if>
                        <input type="button" class="addFavoriteButton addFavoriteField {id: '${fieldBlock.id}'}" value="" hidefocus="true" />
					</div>
				</div>
			</div>
			<div class="blank"></div>
			<div class="productBottom">
				<ul class="productAttributeTab">
					<li>
						<a href="javascript:void(0);" class="current" hidefocus="true">土地块介绍</a>
					</li>
				</ul>
				<div class="tabContent productDescription">
					${fieldBlock.description}
				</div>
				
			</div>
			
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>
