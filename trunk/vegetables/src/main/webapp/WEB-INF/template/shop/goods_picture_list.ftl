<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><#if goodsCategory??>${(goodsCategory.name)!}</#if> 农产品列表<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<#if (goodsCategory.metaKeywords)! != ""><meta name="keywords" content="${goodsCategory.metaKeywords}" /></#if>
<#if (goodsCategory.metaDescription)! != ""><meta name="description" content="${goodsCategory.metaDescription}" /></#if>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/vegetables/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body class="goodsList">
	<#include "/shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="goodsCategory">
            	<div class="top">商品分类</div>
            	<div class="middle">
            		<ul id="goodsCategoryMenu" class="menu">
	            			<#list goodsCategoryTree as firstGoodsCategory>
	            				<li class="mainCategory">
									<a href="${base}/product/productCategory/${firstGoodsCategory.id}">${firstGoodsCategory.name}</a>
								</li>
								<#if (firstGoodsCategory.children?? && firstGoodsCategory.children?size > 0)>
									<#list firstGoodsCategory.children as secondGoodsCategory>
										<li>
											<a href="${base}/product/productCategory/${secondGoodsCategory.id}">
												<span class="icon">&nbsp;</span>${secondGoodsCategory.name}
											</a>
										</li>
										<#-- if secondGoodsCategory_index + 1 == 5>
											<#break />
										</#if -->
									</#list>
								</#if>
								<#if firstGoodsCategory_index + 1 == 4>
									<#break />
								</#if>
	            			</#list>
					</ul>
            	</div>
                <div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="hotGoods">
				<div class="top">热销排行</div>
				<div class="middle">
					<ul>
							<#list goodsList as goods>
								<li class="number${goods_index + 1}">
									<span class="icon">&nbsp;</span>
									<a href="${base}${goods.htmlPath}" title="${goods.name}"><#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..24]}...</#if></a>
								</li>
							</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div id="goodsHistory" class="goodsHistory">
				<div class="top">浏览记录</div>
				<div class="middle">
					<ul id="goodsHistoryListDetail"></ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<form id="goodsListForm" action="${base}/product/productCategory/${(goodsCategory.id)!}" method="post">
				<input type="hidden" name="id" value="${(goodsCategory.id)!}" />
				<input type="hidden" id="viewType" name="viewType" value="pictureType" />
				<input type="hidden" id="pageNumber" name="pager.pageNumber" value="${pager.pageNumber}" />
				<input type="hidden" id="brandId" name="brand.id" value="${(brand.id)!}" />
				<div class="listBar">
					<div class="left"></div>
					<div class="middle">
						<div class="path">
							<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
							<#list pathList as path>
								<a href="${base}/product/productCategory/${path.id}">${path.name}</a> &gt;
							</#list>
						</div>
						<div class="total">共计: ${pager.totalCount} 款商品</div>
					</div>
					<div class="right"></div>
				</div>
				<#if (goodsCategory?? && goodsCategory.children?? && goodsCategory.children?size > 0) || (goodsCategory?? && goodsCategory.goodsType??)>
					<#assign goodsType = goodsCategory.goodsType />
					<div class="blank"></div>
					<div id="filter" class="filter">
						<#if (goodsCategory.children?? && goodsCategory.children?size > 0)>
							<dl>
								<dt>商品分类: </dt>
								<dd>
									<a href="${base}/productCategory/402881882ba8753a012ba9545eb901c1" class="current">全部</a>
								</dd>
								<#list goodsCategory.children as childrenGoodsCategory>
									<dd>
										<a href="${base}/productCategory/${childrenGoodsCategory.id}">${childrenGoodsCategory.name}</a>
									</dd>
								</#list>
							</dl>
						</#if>
						<#if goodsType != null>
							<#if (goodsType.brandSet?? && goodsType.brandSet?size > 0)>
								<dl>
									<dt>商品品牌: </dt>
									<dd>
										<a href="#" class="brand all<#if !brand??> current</#if>">全部</a>
									</dd>
									<#list goodsType.brandSet as b>
										<dd>
											<a href="#" class="brand<#if b == brand> current</#if>" brandId="${b.id}">${b.name}</a>
										</dd>
									</#list>
								</dl>
							</#if>
							<#if (goodsType.goodsAttributeSet?? && goodsType.goodsAttributeSet?size > 0)>
								<#assign hasInputGoodsAttribute = false />
								<#list goodsType.goodsAttributeSet as goodsAttribute>
									<#if goodsAttribute.attributeType == "filter">
										<#assign goodsAttributeOption = (goodsAttributeIdMap[goodsAttribute.id])!"" />
										<dl>
											<dt>${goodsAttribute.name}: </dt>
											<dd>
												<a href="#" class="goodsAttributeOption all<#if goodsAttributeOption == ""> current</#if>">全部</a>
											</dd>
											<#list goodsAttribute.optionList as option>
												<dd>
													<input type="hidden" name="goodsAttributeIdMap['${goodsAttribute.id}']" value="${option}"<#if option != goodsAttributeOption> disabled</#if> />
													<a href="#" class="goodsAttributeOption<#if option == goodsAttributeOption> current</#if>">${option}</a>
												</dd>
											</#list>
										</dl>
									<#else>
										<#assign hasInputGoodsAttribute = true />
										<dl>
											<dt>${goodsAttribute.name}: </dt>
											<dd>
												<input type="text" name="goodsAttributeIdMap['${goodsAttribute.id}']" value="${(goodsAttributeIdMap[goodsAttribute.id])!}" />
											</dd>
										</dl>
									</#if>
								</#list>
								<#if hasInputGoodsAttribute>
									<div class="buttonArea">
										<input type="submit" value="筛选商品" />
									</div>
								</#if>
							</#if>
						</#if>
						<div class="clear"></div>
					</div>
				</#if>
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middle">
						<span class="tableIcon">&nbsp;</span><a id="tableType" class="tableType" href="#">列表</a>
						<span class="pictureDisabledIcon">&nbsp;</span>图片
						<span class="separator">&nbsp;</span>
						<select id="orderType" name="orderBy">
							<option value="default"<#if orderType == "default"> selected="selected"</#if>>默认排序</option>
							<option value="priceAsc"<#if orderType == "priceAsc"> selected="selected"</#if>>价格从低到高</option>
							<option value="priceDesc"<#if orderType == "priceDesc"> selected="selected"</#if>>价格从高到低</option>
							<option value="dateAsc"<#if orderType == "dateAsc"> selected="selected"</#if>>按上架时间排序</option>
	                    </select>
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
					<ul class="goodsListDetail">
						<#list pager.result as goods>
							<li<#if (goods_index + 1) % 4 == 0> class="end"</#if>>
								<a href="${base}/product/${goods.id}" class="goodsImage" target="_blank">
									<img src="${base}/vegetables/inventory/${goods.productImageList[0].thumbnailImageView}" alt="${goods.name}" />
								</a>
								<div class="goodsTitle">
									<a href="${base}/product/${goods.id}" alt="${goods.name}" target="_blank">
									<#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..24]}...</#if>
									</a>
								</div>
								<div class="goodsBottom">
									<div class="goodsPrice">
										<span class="price">${goods.price?string(currencyFormat)}</span>
										<#if setting.isShowMarketPrice>
											<span class="marketPrice">${goods.marketPrice?string(currencyFormat)}</span>
										</#if>
									</div>
									<div class="buttonArea">
										<a href="${base}/product/${goods.id}" class="addCartItemButton">查看详细</a>
									</div>
								</div>
							</li>
						</#list>
						<#if (pager.result?size == 0)!>
                			<li class="noRecord">非常抱歉,没有找到相关商品!</li>
                		</#if>
					</ul>
					<div class="blank"></div>
         			<#assign parameterMap = {"orderType": (orderType)!, "viewType": (viewType)!} />
         				<#include "/shop/pager.ftl">
				</div>
			</form>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">
	<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
	<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
	<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
</body>
</html>