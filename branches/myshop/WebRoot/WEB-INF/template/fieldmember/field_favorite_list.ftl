<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>承包土地收藏 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	// 删除
	$(".deleteFavorite").click( function() {
		if (confirm("您确定要删除吗？") == false) {
			return false;
		}
	});
	
});
</script>
</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body favoriteList">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">
					承包土地收藏
				</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>承包土地图片</th>
							<th>承包土地名称</th>
							<th>承包土地价格</th>
							<th>操作</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" class="productImage" target="_blank">
										<img src="${base}${(list.productImageList[0].thumbnailProductImagePath)!systemConfig.defaultThumbnailProductImagePath}" alt="${list.name}" />
									</a>
								</td>
								<td>
									<a href="${base}/fieldmember/field_block!content.action?id=${list.id}" target="_blank">${list.name}</a>
								</td>
								<td>${list.rent}</td>
								<td>
									<a href="${base}/fieldmember/field_favorite!delete.action?id=${list.id}" class="deleteFavorite">删除</a>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         			<link href="${base}/template/shop/css/pager.css" rel="stylesheet" type="text/css" />
         			<#import "/WEB-INF/template/shop/pager.ftl" as p>
         			<@p.pager pager = pager baseUrl = "/shop/favorite!list.action" />
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>
