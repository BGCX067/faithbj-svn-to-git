<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>礼品卡列表 - Powered By faithbj</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "common/include.ftl">
<link href="${base}/vegetables/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/admin/js/list.js"></script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>礼品卡列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="list" method="post">
			<div class="operateBar">
				<input type="button" class="addButton" onclick="location.href='add'" value="添加类型" />
				<label>查找:</label>
				<select name="pager.property">
					<option value="name" <#if pager.property == "name">selected="selected" </#if>>
						类型名称
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
						<span class="sort" name="alias">礼品卡中文名</span>
					</th>
					<th>
						<span class="sort" name="denomination">礼品卡面额</span>
					</th>
					<th>
						<span class="sort" name="user">用户</span>
					</th>
					<th>
						<span class="sort" name="presentNumber">礼品卡号码</span>
					</th>
					<th>
						<span class="sort" name="presentStatus">礼品卡状态</span>
					</th>
					<th>
						<span class="sort" name="startDate">礼品卡生效时间</span>
					</th>
					<th>
						<span class="sort" name="expireDate">礼品卡过期时间</span>
					</th>
				</tr>
				<#list pager.result as list>
					<tr>
					<td>
							${list.categoryid.alias}&nbsp;
						</td>
						<td>
							${list.categoryid.denomination}&nbsp;
						</td>
						
						<td>
						<#if list.memberid!=null>
						${list.memberid.username}&nbsp;
						</#if>
						</td>
						<td>
						${list.presentNumber}&nbsp;
						</td>
						<td>
						<#if list.presentStatus==0>
						激活
						<#elseif list.presentStatus==1>
						已使用
						<#elseif list.presentStatus==2>
						已过期
						</#if>
						${list.presentStatus}&nbsp;
						</td>
						<td>
						${list.startDate}&nbsp;
						</td>
						<td>
						${list.expireDate}&nbsp;
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				  <#include "admin/pager.ftl" />
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
	</div>
</body>
</html>
