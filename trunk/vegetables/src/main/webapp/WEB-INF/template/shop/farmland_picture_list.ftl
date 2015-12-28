<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地列表- Powered By faithbj</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<#include "/common/common_css.ftl">
<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?sensor=false"></script>     
<script type="text/javascript" src="${base}/vegetables/shop/js/googlemap.js"></script>    
     
</head>
<body class="goodsList" onload="initialize()">
	<#include "/shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">

			<div class="hotGoods">
				<div class="top">农园地址</div>
				<div id="map_canvas" style="clear: both;width:208px; height:336px"></div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div id="goodsHistory" class="goodsHistory">
			 <a href="http://cjlny.vicp.net:88/"><img src="${base}/vegetables/shop/images/terminal.png" /></a>
			</div>
		</div>
		<div class="bodyRight">
			<form id="goodsListForm" action="${base}/land" method="post">
				<input type="hidden" name="id" value="${(productCategory.id)!}" />
				<input type="hidden" id="viewType" name="viewType" value="pictureType" />
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}" />
				<input type="hidden" id="brandId" name="brand.id" value="${(brand.id)!}" />
				<div class="listBar">
					<div class="left"></div>
					<div class="middle">
						<div class="path">
							<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
							<#list pathList as path>
								<a href="${base}${path.url}">${path.name}</a> &gt;
							</#list>
						</div>
						<div class="total">共计: ${pager.totalCount} 块土地</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middle">
						<span class="separator">&nbsp;</span>
						<#--
						<select id="orderType" name="orderBy">
							<option value="default"<#if orderType == "default"> selected="selected"</#if>>默认排序</option>
							<option value="priceAsc"<#if orderType == "priceAsc"> selected="selected"</#if>>价格从低到高</option>
							<option value="priceDesc"<#if orderType == "priceDesc"> selected="selected"</#if>>价格从高到低</option>
							<option value="dateAsc"<#if orderType == "dateAsc"> selected="selected"</#if>>按上架时间排序</option>
	                    </select>
	                    -->
	                    <span class="separator">&nbsp;</span> 显示数量: 
						<select name="pager.pageSize" id="pageSize">
							<option value="12" <#if pager.pageSize == 12>selected="selected" </#if>>
								12
							</option>
							<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
								20
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
				<div class="goodsPictureList">
					<ul class="landListDetail">
						<#list pager.result as goods>
							<li<#if (goods_index + 1) % 4 == 0> class="end"</#if>>
								<a href="${base}/product/showland/${goods.id}" class="goodsImage" target="_blank">
								<img src="${base}/vegetables/inventory/${(goods.productImageList[0].thumbnailImageView)!"available.png"}" alt="${goods.name}" />
								</a>
								<div class="goodsTitle">
									<a href="${base}/product/${goods.id}" alt="${goods.name}" target="_blank">
									编号：${goods.productSn}  <#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..24]}...</#if>
									</a>
								</div>
							</li>
						</#list>
					</ul>
					<div class="blank"></div>
         			<#assign parameterMap = {"orderType": (orderType)!, "viewType": (viewType)!} />
         				<#include "shop/pager.ftl">
				</div>
			</form>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">
	<#include "/common/common_js.ftl">
	
</body>
</html>