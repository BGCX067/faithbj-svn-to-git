<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新闻分类列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>

</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1>未回答问题列表</h1>
		</div>
			
		<div >
			<table class="listTable">
				<tr>
					<th width="10">
						
					</th>
					<th>
						主题&nbsp;
					</th>
					<th>
						问题&nbsp;
					</th>
					<th>
						操作
					</th>
				</tr>
				<#list onlinelist as list>
					<tr>
						<td width="10">${list_index + 1}.</td>
						<td>
							<#if list.title?length lt 30>${list.title}<#else>${list.title[0..30]}...</#if>
						</td>
						<td>
							${list.question}
						</td>
						
						<td>
							<a href="online!edit.action?id=${list.id}" title="回答问题">[回答问题]</a>
						</td>
					</tr>
				</#list>
			</table>
			</div>
			<#if onlinelist?size == 0>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
	</div>
</body>
</html>
