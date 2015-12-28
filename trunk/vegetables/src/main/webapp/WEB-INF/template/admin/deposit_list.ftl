<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理礼品卡 - Powered By <#--${systemConfig.systemName}--></title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/common/include.ftl">
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
</head>
<body class="list">
	<div class="body">
			<div class="listBar">
				<h1><span class="icon">&nbsp;</span>下发礼品卡&nbsp;</h1>
			</div>
			<div class="listBar">
				<h1><span class="icon">&nbsp;</span>会员列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
			</div>
			<#--会员表单开始-->
				<form id="releaseForm" action="deposit!list.action" method="post">
					<div class="operateBar">
					<h>请选择优惠活动名称:</h>
						<select id="eventid" name="eventid" class="{required: true}">
							<#list list as list>
								<option value="${list.id}">${list.eventname}</option>
							</#list>
						</select>
						<#--
						<label>查找:</label>
						<select name="pager.property">
							<option value="username" <#if pager.property == "username">selected="selected" </#if>>
								用户名称
							</option>
						</select>
						<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
						-->
						
						
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
								<span class="sort" name="username">用户名称</span>
							</th>
							<th>
								<span class="sort" name="email">金额</span>
							</th>
							<th>
								<span class="sort" name="email">登录IP</span>
							</th>
							<th>
								<span class="sort" name="createDate">创建日期</span>
							</th>
							<th>
								<span class="sort" name="modifyDate">修改时期</span>
							</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<input type="checkbox" name="memberids" value="${list.id}" />
								</td>
								<td>
									${list.username}
								</td>
								<td>
									${list.email}
								</td>
								<td>
									${list.loginIp}
								</td>	
								<td>
									${list.createDate?string("yyyy-MM-dd HH:mm:ss")}
								</td>	
								<td>
									${list.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
								</td>	
							</tr>
						</#list>
					</table>
					<#if (pager.list?size > 0)>
						<div class="pagerBar">
							<input type="button" class="releaseButton"  url="deposit!releaseCard.action" value="下发礼品卡" disabled hidefocus="true" />
							<#include "/WEB-INF/template/admin/pager.ftl" />
						</div>
					<#else>
						<div class="noRecord">
							没有找到任何记录!
						</div>
					</#if>
				</form>
				<#--表单结束-->
	</div>
</body>
</html>
