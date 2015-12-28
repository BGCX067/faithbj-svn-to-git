<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>种子列表</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {

var $deleteSeedButton = $("#listTable .deleteSeedButton");

	// 删除种子
	$deleteSeedButton.click( function() {
		if (confirm("您确定要删除此种子吗?") == false) {
			return false;
		}
	});
	});
	</script>
</head>
<body class="list">
	<div class="bar">
		种子列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="/faith/seed/lis" method="post">
			<div class="listBar">
			<input type="button" class="formButton" onclick="location.href='/faith/seed/add'" value="添加种子" hidefocus/>
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="name" <#if pager.property == "name">selected="selected" </#if>>
						种子名称
					</option>
					<option value="type" <#if pager.property == "type">selected="selected" </#if>>
						种子类型
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
					<th>
						<a href="#" class="sort" name="name" hidefocus>名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="startdate" hidefocus>起始时间</a>
					</th>
					<th>
						<a href="#" class="sort" name="enddate" hidefocus>结束时间</a>
					</th>
					<th>
						<a href="#" class="sort" name="type" hidefocus>类型</a>
					</th>
					<th>
						<a href="#" class="sort" name="description" hidefocus>描述</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as list>
					<tr>
						<td>
							${list.seedName}
						</td>
						<td>
							${list.startDate}
						</td>
						<td>
							${list.endDate}
						</td>
						<td>
							${list.type}
						</td>
						<td>
							${list.description}
						</td>
						<td>
							<a href="/faith/seed/edit?id=${list.id}" title="编辑">[编辑]</a>
							<#if list.farmland?size gt 0>
								<span title="无法删除">[删除]</span>
							<#else>
								<a href="${base}/faith/seed/${list.id}/delete" class="deleteSeedAction" title="删除" >[删除]</a>
							</#if>
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
