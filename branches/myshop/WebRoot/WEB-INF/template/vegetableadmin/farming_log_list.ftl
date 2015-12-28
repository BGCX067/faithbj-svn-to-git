<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>耕种日志列表 - Powered By ${systemConfig.systemName}</title>
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
			<h1><span class="icon">&nbsp;</span>耕种日志列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount} (共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="farming_log!list.action" method="post">
			<div class="operateBar">
				<label>查找:</label>
				<select name="pager.property">
					
					<option value="code" <#if pager.property == "code">selected="selected" </#if>>
						耕种地块代码
					</option> 
					<option value="rendBlockCode" <#if pager.property == "rendBlockCode">selected="selected" </#if>>
						承包地块代码
					</option> 
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
			</div>
			<table class="listTable">
				<tr>
					<th>
						<span class="sort" name="code">耕种地块代码</span>
					</th>
					<th>
						<span class="sort" name="rendBlockCode">所属承包地块代码</span>
					</th>
					<th>
						<span class="sort" name="area">面积</span>
					</th>
					<th>
						<span class="sort" name="status">状态</span>
					</th>
					<th>
						<span class="sort" name="username">会员名称</span>
					</th>
					<th>
						<span class="sort" name="seedName">作物</span>
					</th>
					<th>
						<span class="sort" name="operateUser">操作人</span>
					</th>
					<th>
						<span class="sort" name="operateTime">操作时间</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							${list.code}
						</td>
						<td>
							${list.rendBlockCode}
						</td>
						<td>
							${list.area}
						</td>
						<td>
							<#if list.status = '8'>
							清理
							<#elseif list.status = '2'>
							播种
							<#elseif list.status = '4'>
							生长
							<#elseif list.status = '6'>
							收获
							<#else>
							空闲
							</#if>
							
						</select>
						</td>
						<td>
							${list.username}
						</td>
						<td>
							${list.seedName}
						</td>
						<td>
							${list.operateUser}
						</td>
						<td>
							${(list.operateTime?string('yyyy-MM-dd HH:mm:ss'))!}
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
	</div>
	
</body>
</html>
