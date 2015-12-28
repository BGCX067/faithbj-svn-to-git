<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品列表</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">
		商品列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="list/${(productid)}">
		<input type="hidden" name="productid" value="${(productid)!}" />
			<div class="listBar">
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="name"<#if pager.searchBy == "name"> selected</#if>>
						商品名称
					</option>
					<option value="goodsSn"<#if pager.searchBy == "goodsSn"> selected</#if>>
						商品编号
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
						<a href="#" class="sort" name="goodsSn" hidefocus>土地编号</a>
					</th>
					<th>
						<a href="#" class="sort" name="goodsCategory" hidefocus>耕种状态</a>
					</th>
					<th>
						<a href="#" class="sort" name="price" hidefocus>种子</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as farmland>
					<tr>
						<td>
							${farmland.rendBlockCode}
						</td>
						<td>
							${farmland.landStatus}
						</td>
						<td>
							${(farmland.seed.seedName)!}
						</td>
						<td>
							<a href="${base}/faith/farmland/farming/${farmland.id}/edit" title="播种">[播种]</a>
							<a href="${base}/faith/farmland/${farmland.id}/upload" target="_blank" title="上传图片">[上传图片]</a>
						</td>
					</tr>
				</#list>
			</table>
		</form>
	</div>
</body>
</html>