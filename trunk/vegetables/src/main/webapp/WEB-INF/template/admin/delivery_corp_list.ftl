<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>物流公司列表 - Powered By <#--${systemConfig.systemName}--></title>

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">
		物流公司管理&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='new'" value="添加公司" hidefocus />
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<a href="#" class="sort" name="name" hidefocus>物流公司名称</a>
					</th>
					<th>
						<a href="#" class="sort" name="url" hidefocus>公司网址</a>
					</th>
					<th>
						<a href="#" class="sort" name="orderList" hidefocus>排序</a>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as deliveryCorp>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${deliveryCorp.id}" />
						</td>
						<td>
							${deliveryCorp.name}
						</td>
						<td>
							${deliveryCorp.url}
						</td>
						<td>
							${deliveryCorp.orderList}
						</td>
						<td>
							<a href="${deliveryCorp.id}/edit" title="编辑">[编辑]</a>
							<a href="${deliveryCorp.id}/delete" title="删 除">[删 除]</a>
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