<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜精灵</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
	<#include "/common/common_css.ftl">
	<#include "/common/common_js.ftl">
</head>
<body class="index">
	<#include "/shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="hotArticle">
            	<div class="top">最新新闻</div>
            	<div class="middle">
            		<ul>
						<#list newArticleList as article>
							<li class="number${article_index + 1}">
								<span class="icon">&nbsp;</span>
								<a href="${base}/article/content/${article.id}" title="${article.title}">${article.title?substring(0)}</a>
							</li>
						</#list>
					</ul>
            	</div>
                <div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="slider">
				<div id="sliderScrollable" class="scrollable">
					<div class="items">
						<div>
							<img src="${base}/vegetables/shop/images/beginbusiness.png" />
						</div>
						<div>
							<img src="${base}/vegetables/shop/images/landtolet.png" />
						</div>
						<div>
							<img src="${base}/vegetables/shop/images/farmview.png" />
						</div>
					</div>
					<div class="navi"></div>
					<div class="prevNext">
						<a class="prev browse left"></a>
						<a class="next browse right"></a>
					</div>
				</div>
			</div>
			<div class="blank"></div>
			<div class="hotGoodsSlider">
				<div class="title">
					<strong>热卖蔬菜</strong>HOT
				</div>
				<a class="prev browse"></a>
					<#if (hotProductList?? && hotProductList?size > 0)>
						<div id="hotGoodsScrollable" class="scrollable">
							<div class="items">
								<#list hotProductList as goods>
									<#if goods_index + 1 == 1>
										<div>
										<ul>
									</#if>
									<li>
										<a href="${base}/product/${goods.id}">
											<img src="${base}/vegetables/inventory/${goods.productImageList[0].thumbnailImageView}" alt="${goods.name}" />
											<p title="${goods.name}"><#if goods.name?length lt 18>${goods.name}<#else>${goods.name[0..18]}...</#if></p>
										</a>
									</li>
									<#if (goods_index + 1) % 4 == 0 || !goods_has_next || goods_index + 1 == 12>
										</ul>
										</div>
									</#if>
									<#if (goods_index + 1) % 4 == 0 && goods_has_next>
										<div>
										<ul>
									</#if>
								</#list>
							</div>
						</div>
					</#if>
				<a class="next browse"></a>
			</div>
		</div>
		<div class="blank"></div>
		<img src="${base}/vegetables/shop/images/organic.png" />
		<div class="blank"></div>
		<div class="newGoods">
			<div class="left">
				<ul id="newGoodsTab" class="newGoodsTab">
						<#list productCategoryList as goodsCategory>
							<li>${goodsCategory.name}</li>
							<#if goodsCategory_index + 1 == 4>
									<#break />
								</#if>
						</#list>
				</ul>
			</div>
			<div class="right">
					<#list productCategoryList as goodsCategory>
						<ul class="newGoodsTabContent hidden">
					        <#list newProductMap[goodsCategory.id] as goods>
								<li>
									<a href="${base}/product/${goods.id}">
										<img src="${base}/vegetables/inventory/${goods.productImageList[0].thumbnailImageView}" alt="${goods.name}" />
				                         <p title="${goods.name}"><#if goods.name?length lt 18>${goods.name}<#else>${goods.name[0..18]}...</#if></p>
									</a>
								</li>
							</#list>
						</ul>
					</#list>
			</div>
		</div>
		<div class="blank"></div>
		<div class="bodyLeft">
				<#if (hotProductList?size > 0)>
					<div class="hotGoods">
						<div class="top">热销排行</div>
						<div class="middle">
							<ul>
								<#list hotProductList as goods>
									<li class="number${goods_index + 1}">
										<span class="icon">&nbsp;</span>
										<a href="${base}/product/${goods.id}" title="${goods.name}"><#if goods.name?length lt 24>${goods.name}<#else>${goods.name[0..18]}...</#if></a>
									</li>
								</#list>
							</ul>
						</div>
						<div class="bottom"></div>
					</div>
					<div class="blank"></div>
				</#if>
		</div>
		<div class="bodyRight">
				<#if (hotProductList?size > 0)>
					<div class="bestGoods">
						<div class="top">
							<strong>精品推荐</strong>BEST
						</div>
						<div class="middle">
							<ul>
								<#list (bestProductList)! as goods>
									<li>
										<a href="${base}/product/${goods.id}">
											<img src="${base}/vegetables/inventory/${goods.productImageList[0].thumbnailImageView}" alt="${goods.name}" />
											<p title="${goods.name}"><#if goods.name?length lt 18>${goods.name}<#else>${goods.name[0..18]}...</#if></p>
											<p class="red">${goods.price?string(currencyFormat)}</p>
										</a>
									</li>
								</#list>
							</ul>
						</div>
						<div class="bottom"></div>
					</div>
				</#if>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">

</body>
</html>