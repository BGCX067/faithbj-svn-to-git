<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>我的耕种土地地列表<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
		<div class="top">耕种块列表</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							
							<th>土地图片</th>
							<th>土地编号</th>
							<th>耕种状态</th>
							<th>种子</th>
							<th>操作</th>
						</tr>
						<#list pager.result as farmland>
							<tr>
								
								<td>
								<div class="goodsContent">
								<div class="goodsTopLeft">
									<div class="goodsImage">
										<#if farmland.productImageList??>
						                	<#list farmland.productImageList as goodsImage>
						                		<a href="${base}/vegetables/inventory/${goodsImage.bigImageView}" class="tabContent zoom<#if (goodsImage_index > 0)> nonFirst</#if>">
													<img src="${base}/vegetables/inventory/${goodsImage.smallImageView}" alt="点击放大" />
												</a>
											</#list>
										<#else>
					            			<a href="${base}/vegetables/shop/images/${setting.defaultBigProductImagePath}" class="zoom">
												<img src="${base}/vegetables/shop/images/${setting.defaultSmallProductImagePath}" alt="点击放大" />
											</a>
					                	</#if>
				                	</div>
									<div class="thumbnailGoodsImage">
										<a class="prev browse" href="javascript: void(0);" hidefocus></a>
										<div id="thumbnailGoodsImageScrollable" class="scrollable">
											<ul id="goodsImageTab" class="items goodsImageTab">
												<#if farmland.productImageList??>
													<#list farmland.productImageList as goodsImage>
														<li><img src="${base}/vegetables/inventory/${goodsImage.thumbnailImageView}" alt="${goodsImage.description}" /></li>
													</#list>
												<#else>
													<li><img src="${base}/vegetables/shop/images/${setting.defaultSmallProductImagePath}" /></li>
					                        	</#if>
											</ul>
										</div>
										<a class="next browse" href="javascript: void(0);" hidefocus></a>
									</div>
						</div>
					</div>
								<#--	<img src="${base}/vegetables/inventory/${farmland.productImageList[0].thumbnailImageView}" />-->
								</td>
								<td >
									${farmland.rendBlockCode}
								</td>
								<td>
									${farmland.landStatus}
								</td>
								<td>
									${(farmland.seed.seedName)!}
								</td>
								<td width="250">
									<input type="button" class="addButton" id="bozhong" value="播种" />
									
									<input type="button"  class="addButton" value="清理" />
									<input type="button"  class="addButton" value="下载图片"/>
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