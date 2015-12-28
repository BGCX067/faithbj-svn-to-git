<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新闻中心- Powered By faithbj</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
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
<body class="articleList">
	<#include "/shop/header.ftl">
	<div class="body">
		<div class="bodyLeft">
			<div class="recommendArticle">
				<div class="top">最新动态</div>
				<div class="middle">
					<ul>
						<#list newArticleList as article>
							<li>
								<span class="icon">&nbsp;</span>
								<a href="${base}/article/content/${article.id}" title="${article.title}">${article.title?substring(0)}</a>
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
								<a href="${base}/article/content/${article.id}" title="${article.title}">${article.title?substring(0)}</a>
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
						<a href="${base}/" class="home"><span class="icon">&nbsp;</span>首页</a> &gt;
						<#list pathList as path>
							<a href="${base}/articleCategory/${path.id}">${path.name}</a> &gt;
						</#list>
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
			<div class="articleList">
				<div class="articleListTop"></div>
				<div class="articleListMiddle">
					<ul class="articleListDetail">
						<#list pager.result as article>
                			<li>
                            	<a href="${base}/article/content?id=${article.id}" class="title">
									${article.title?substring(0)}
								</a>
                                <span class="author">
                                	作者: <#if article.author == "">未知<#else>${article.author}</#if>
                                </span>
                                <span class="createDate">
                                	${article.createDate}
                                </span>
                                <div class="contentText">
									${article.contentText?substring(0)}
									<a href="${base}/article/content/${article.id}">[阅读全文]</a>
								</div>
      		        		</li>
                		</#list>
					</ul>
					<div class="blank"></div>
         				<#include "shop/pager.ftl">
				</div>
				<div class="articleListBottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "shop/footer.ftl">
	<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
	<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
	<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
</body>
</html>