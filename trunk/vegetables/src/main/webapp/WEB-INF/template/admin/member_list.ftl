<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员列表 - Powered By faithby</title>
<meta name="Author" content="faithby Team" />
<meta name="Copyright" content="faithby" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	var $deletememberButton = $("#listTable .deletememberButton");

	// 删除商品分类
	$deletememberButton.click( function() {
		if (confirm("您确定要删除此会员吗?") == false) {
			return false;
		}
	});
	}
	</script>
</head>
<body class="list">
	<div class="bar">
		会员列表&nbsp;总记录数: ${pager.totalCount} (共${pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="list" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='new'" value="添加会员" hidefocus />
				&nbsp;&nbsp;
				<label>查找: </label>
				<select name="pager.searchBy">
					<option value="username"<#if pager.searchBy == "username"> selected</#if>>
						用户名
					</option>
					<option value="email"<#if pager.searchBy == "email"> selected</#if>>
						E-mail
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
						<a href="#" class="sort" name="username" hidefocus>用户名</a>
					</th>
					<th>
						<a href="#" class="sort" name="memberRank" hidefocus>会员等级</a>
					</th>
					<th>
						<a href="#" class="sort" name="email" hidefocus>E-mail</a>
					</th>
					<th>
						<a href="#" class="sort" name="createDate" hidefocus>注册日期</a>
					</th>
					<th>
						<span>状态</span>
					</th>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<#list pager.result as member>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${member.id}" />
						</td>
						<td>
							${member.username}
						</td>
						<td>
							${member.memberRank.name}
						</td>
						<td>
							${member.email}
						</td>
						<td>
							<span title="${member.createDate?string("yyyy-MM-dd HH:mm:ss")}">${member.createDate}</span>
						</td>
						<td>
							<#if member.isAccountEnabled && !member.isAccountLocked && !member.isAccountExpired && !member.isCredentialsExpired>
								<span class="green">正常</span>
							<#elseif !member.isAccountEnabled>
								<span class="red"> 未启用 </span>
							<#elseif member.isAccountLocked>
								<span class="red"> 已锁定 </span>
							</#if>
						</td>
						<td>
							<a href="edit?id=${member.id}" title="[编辑]">[编辑]</a>
							<a href="/faith/member/delete?id=${member.id}"  class="deletememberButton"  title="删 除">[删除]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="pager">
						<#include "/admin/pager.ftl" />
					</div>
				<div>
			<#else>
				<div class="noRecord">没有找到任何记录!</div>
			</#if>
		</form>
	</div>
</body>
</html>