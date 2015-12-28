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
<link href="${base}/template/shop/css/product_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/product.js"></script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body memberCenterIndex productList">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
		
			<form id="productListForm" action="${base}/fieldmember/field_block!list.action" method="get">
				<input type="hidden" id="viewType" name="viewType" value="pictureType" />
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}" />
				<div class="listBar">
					<div class="left"></div>
					<div class="middle">
						<div class="path">
							<a href="${base}/" class="home"><span class="icon">&nbsp;</span>首页</a>>
							<a href="${base}/fieldmember/field_block!list.action" class="home"><span class="icon">&nbsp;</span>承包土地</a>>
						</div>
						<div class="total">共计: ${pager.totalCount} 块土地</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middle">
						
						<#if viewType == 'tableType'>
							<span class="tableDisabledIcon">&nbsp;</span>列表
							<span class="pictureIcon">&nbsp;</span><a id="pictureType" class="pictureType" href="#">图片</a>
						<#else>
							<span class="tableIcon">&nbsp;</span><a id="tableType" class="tableType" href="#">列表</a>
							<span class="pictureDisabledIcon">&nbsp;</span>图片
						</#if>
						
						<span class="separator">&nbsp;</span>
						
	                    显示数量:
						<select name="pager.pageSize" id="pageSize">
							<option value="12" <#if pager.pageSize == 12>selected="selected" </#if>>
								12
							</option>
							<option value="24" <#if pager.pageSize == 24>selected="selected" </#if>>
								24
							</option>
							<option value="60" <#if pager.pageSize == 60>selected="selected" </#if>>
								60
							</option>
							<option value="120" <#if pager.pageSize == 120>selected="selected" </#if>>
								120
							</option>
						</select>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				
				<#if viewType == 'tableType'>
					<div class="productTableList">
				<#else>
					<div class="productPictureList">
				</#if>
				
					<ul class="productListDetail">
						<#if viewType == 'tableType'>
							<#list pager.list as list>
							<li>
								<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" class="productImage">
									<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
								</a>
								<div class="productTitle">
									<#if (list.name?length < 28)>
										<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" alt="${list.name}">${list.name}</a>
									<#else>
										<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" alt="${list.name}">${list.name[0..25]}...</a>
									</#if>
								</div>
								<div class="productRight">
									<div class="productPrice">
										<span class="price">${list.rent?string(priceCurrencyFormat)}</span>
										<span class="marketPrice">${list.rent?string(priceCurrencyFormat)}</span>
									</div>
									<div class="productButton">
										<input type="button" onclick="javascript:window.location.href='${base}/fieldmember/field_block!content.action?id=${list.id}'" value="查看" hidefocus="true" />
									</div>
								</div>
							</li>
							</#list>
						<#else>
							<#list pager.list as list>
								<li<#if (list_index + 1) % 4 == 0> class="end"</#if>>
									<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" class="productImage">
										<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
									</a>
									<div class="productTitle">
										<#if (list.name?length < 28)>
											<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" alt="${list.name}">${list.name}</a>
										<#else>
											<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" alt="${list.name}">${list.name[0..25]}...</a>
										</#if>
									</div>
									<div class="productBottom">
										<div class="productPrice">
											<span class="price">${list.rent?string(priceCurrencyFormat)}</span>
											<span class="marketPrice">${list.rent?string(priceCurrencyFormat)}</span>
										</div>
										<div class="productButton">
											<input type="button" onclick="javascript:window.location.href='${base}/fieldmember/field_block!content.action?id=${list.id}'" value="查看" hidefocus="true" />
										</div>
									</div>
								</li>
							</#list>
						</#if>
						<#if (pager.list?size == 0)!>
                			<li class="noRecord">非常抱歉，没有找到相关土地！</li>
                		</#if>
					</ul>
					<div class="blank"></div>
         			<link href="${base}/template/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "/WEB-INF/template/shop/pager.ftl" as p>
         			<#assign parameterMap = {"id": (productCategory.id)!, "orderType": (orderType)!, "viewType": (viewType)!} />
         			<@p.pager pager = pager baseUrl = "/shop/product!list.action" parameterMap = parameterMap />
				</div>
			</form>
			
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>
