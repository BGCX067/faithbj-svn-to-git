<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>种子列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
<script type='text/javascript'>
	function returnHandler()
	{
		var returnValue = null;
		var ids = document.listForm.ids;
		if (ids.length)
		{
			for (var i = 0; i < ids.length; i++)
			{
				if (ids[i].checked)
				{
					returnValue = ids[i].value;
					break;
				}
			}
		}
		else
		{
			returnValue = ids.value;
		}
		if (returnValue)
		{
			window.returnValue = returnValue;
			window.close();
		}
		else
		{
			alert("请先选中");
		}
	}
</script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>种子列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount} (共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" name='listForm' action="seed!popup.action" method="post">
			<div class="operateBar">
				<label>查找:</label>
				<select name="pager.property">
					<option value="name" <#if pager.property == "name">selected="selected" </#if>>
						种子名称
					</option>
					<option value="type" <#if pager.property == "type">selected="selected" </#if>>
						种子类型
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
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="name">名称</span>
					</th>
					<th>
						<span class="sort" name="type">类型</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="{id:'${list.id}',name:'${list.name}'}" />
						</td>
						<td>
							${list.name}
						</td>
						<td>
							<#if list.type==true>
								默认种子
							<#else>
								自选种子
							</#if>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<input type="button" onclick='returnHandler();' value="返回" />
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
