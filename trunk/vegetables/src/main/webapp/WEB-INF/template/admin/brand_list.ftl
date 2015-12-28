<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>品牌列表</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var $deleteBrand = $("#listTable .deleteBrand");

	// 删除商品品牌
	$deleteBrand.click( function() {
		if (confirm("您确定要删除此品牌吗?") == false) {
			return false;
		}
	});
});
</script>
</head>
<body class="list">
	<div class="bar">
		品牌列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="/faith/brand/search" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='/faith/brand/add'" value="添加品牌" hidefocus />
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="name"<#if pager.searchBy == "name"> selected</#if>>
						名称
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
						<a href="#" class="sort" name="name" hidefocus>品牌名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="url" hidefocus>网址</a>
					</th>
					<th>
						<a href="#" class="sort" name="logoPath" hidefocus>LOGO</a>
					</th>
					<th>
						<a href="#" class="sort" name="orderList" hidefocus>排序</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as brand>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${brand.id}" />
						</td>
						<td>
							${brand.name}
						</td>
						<td>
							<#if (brand.url != "")!>
								<a href="${brand.url}" target="_blank">${brand.url}</a>
							</#if>
						</td>
						<td>
							<#if brand.logoPath??>
								<a href="${base}/${brand.logoPath}" target="_blank">查看</a>
							<#else>
								-
							</#if>
						</td>
						<td>
							${brand.orderList}
						</td>
						<td>
							<a href="${brand.id}/edit" title="编辑">[编辑]</a>
							<a href="${brand.id}/delete" class="deleteBrand" title="删除">[删除]</a>
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