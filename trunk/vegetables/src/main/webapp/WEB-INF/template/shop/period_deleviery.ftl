<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>定期配送- Powered By faithbj</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<#if (article.metaKeywords)! != ""><meta name="keywords" content="${article.metaKeywords}" /></#if>
<#if (article.metaDescription)! != ""><meta name="description" content="${article.metaDescription}" /></#if>
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
<body class="articleContent">
	<#include "shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="recommendArticle">
				<div class="top">最新动态</div>
				<div class="middle">
					<ul>
						<#list newArticleList as article>
							<li>
								<span class="icon">&nbsp;</span>
								<a href="${base}/article/content?id=${article.id}" title="${article.title}">${article.title?substring(0)}</a>
							</li>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
			<div class="blank"></div>
			<div class="hotArticle">
				<div class="top">热点新闻</div>
				<div class="middle">
					<ul>
						<#list hotArticleList as article>
							<li class="number${article_index + 1}">
								<span class="icon">&nbsp;</span>
								<a href="${base}/article/content?id=${article.id}" title="${article.title}">${article.title?substring(0)}</a>
							</li>
						</#list>
					</ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
					定期配送业务介绍
					</div>
					<div id="articleSearch" class="articleSearch">
						<form id="articleSearchForm" action="${base}/shop/article!search.action" method="post">
							<input type="text" name="pager.keyword" id="articleSearchKeyword" class="keyword" value="请输入关键词..." />
							<input type="submit" class="searchButton" value="" />
						</form>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="articleContentDetail">
				<div class="articleContentTop"></div>
				<div class="articleContentMiddle">
					<div class="title">定期配送业务介绍</div>
                    <div class="blank"></div>
                    <div class="info">
                    	点击: <span id="hits"></span> 次
                    	<span class="fontSize">【<a id="changeBigFontSize" href="javascript: void(0);">大</a> <a id="changeNormalFontSize" href="javascript: void(0);">中</a> <a id="changeSmallFontSize" href="javascript: void(0);">小</a>】</span>
                    </div>
					<div id="articleContent" class="content">
						定期配送用户，首选签约，然后享受签约期的有机蔬菜定期配送。<br/>
						用户可以在线选择方案。方案包括：<br/>
							一、选择配送期，如4周（1个月）、12周（1个季度）、25周（半年）或52周（一年）；<br/>
							二、选择配送频率，如1次/周、2次/周或3次/周；<br/>
							三、选择每次的配送量，如1人配量，2人配量，3人配量，4人配量等；<br/>
							四、选择每周的配送日，如周一、周三、周六、周日等。<br/><br/>
						系统根据总的配量生成价格。<br/>
						请先注册，成为注册会员！

             			<div class="blank"></div>
                    </div>
				</div>
				<div class="articleContentBottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "shop/friend_link.ftl">
		<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
		<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
		<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
		<script type="text/javascript">
		$().ready( function() {
		
			$hits = $("#hits");
		
			// 统计文章点击数
			$.ajax({
				url: "${base}/shop/article!ajaxHits.action?id=${(article.id)!}",
				dataType: "json",
				success: function(data) {
					if (data.status == "success") {
						$hits.text(data.hits);
					}
				}
			});
		
		});
		</script>
	</div>
	<div class="blank"></div>
	<#include "shop/footer.ftl">
</body>
</html>