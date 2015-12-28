<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>文章列表</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var $deleteArticleButton = $("#listTable .deleteArticleButton");

	// 删除商品分类
	$deleteArticleButton.click( function() {
		if (confirm("您确定要删除此文章分类吗?") == false) {
			return false;
		}
	});
	});
	</script>
</head>
<body class="list">
	<div class="bar">
		文章列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="/faith/article/list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='/faith/article/add'" value="添加文章" hidefocus />
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="title"<#if pager.searchBy == "title"> selected</#if>>
						标题
					</option>
				</select>
				<input type="text" name="pager.keyword" value="${pager.keyword!}" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" hidefocus />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10"<#if pager.pageSize == 10> selected</#if>>
						10
					</option>
					<option value="20"<#if pager.pageSize == 20> selected</#if>>
						20
					</option>
					<option value="50"<#if pager.pageSize == 50> selected</#if>>
						50
					</option>
					<option value="100"<#if pager.pageSize == 100> selected</#if>>
						100
					</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<a href="#" class="sort" name="title" hidefocus>标题</a>
					</th>
					<th>
						<a href="#" class="sort" name="articleCategory" hidefocus>分类</a>
					</th>
					<th>
						<a href="#" class="sort" name="isPublication" hidefocus>是否发布</a>
					</th>
					<th>
						<a href="#" class="sort" name="isRecommend" hidefocus>是否推荐</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>添加时间</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as article>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${article.id}" />
						</td>
						<td>
							<span title="${article.title}">
								${article.title?substring(0)}
							</span>
						</td>
						<td>
							${article.articleCategory.name}
						</td>
						<td>
							<span class="${article.isPublication?string('true','false')}Icon">&nbsp;</span>
						</td>
						<td>
							<span class="${article.isRecommend?string('true','false')}Icon">&nbsp;</span>
						</td>
						<td>
							<span title="${article.createDate?string("yyyy-MM-dd HH:mm:ss")}">${article.createDate}</span>
						</td>
						<td>
							<a href="/faith/article/edit?id=${article.id}" title="编辑">[编辑]</a>
							<#if article.isPublication>
								<a href="${base}${article.htmlPath}" target="_blank" title="浏览">[浏览]</a>
							<#else>
								<span title="未发布">[未发布]</span>
							</#if>
							<a href="/faith/article/delete?id=${article.id}"  class="deleteArticleButton"  title="删 除">[删除]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="pager">
						<#include "admin/pager.ftl" />
					</div>
				<div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>