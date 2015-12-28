<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>土地会员中心 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/shop/css/login.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/register.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/shop/css/member_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/shop/js/login.js"></script>
<script type="text/javascript" src="${base}/template/shop/js/register.js"></script>
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/template/vegetable/js/product.js"></script>

<style type='text/css'>
.list .operateBar .addButton1 {
	width: 75px;
	height: 29px;
	line-height: 29px;
	margin-left: 6px;
	text-align: center;
	font-size: 12px;
	cursor: pointer;
	border: none;
	background: url(/template/admin/images/list_bg.gif) 0px -30px repeat-x;
}
.list .operateBar .addButton1:hover {
	background: url(/template/admin/images/list_bg.gif) 0px -60px repeat-x;
}
</style>

</head>
<body class="memberCenter">
	<#include "/WEB-INF/template/shop/header.ftl">
	<div class="body memberCenterIndex productList">
		<#include "/WEB-INF/template/fieldmember/include/field_center_menu.ftl">
		<div class="bodyRight">
		
			<div class="list">
				<div class="listBar">
					<h1><span class="icon">&nbsp;</span>我的耕种地块列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount} (共${pager.pageCount}页)</span></h1>
				</div>
				<form id="listForm" action="my_farming_block!manage.action" method="post">
					<div class="operateBar">
						<label>查找:</label>
						<select name="pager.property">
							
							<option value="code" <#if pager.property == "code">selected="selected" </#if>>
								耕种地块代码
							</option> 
							<option value="seedName" <#if pager.property == "seedName">selected="selected" </#if>>
								种子
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
								<span class="sort" name="code">耕种地块代码</span>
							</th>
							<th>
								<span class="sort" name="status">状态</span>
							</th>
							<th>
								<span class="sort" name="seedName">种子</span>
							</th>
							<th>
								<span class="sort" name="operateUser">操作人</span>
							</th>
							<th>
								<span class="sort" name="operateTime">操作时间</span>
							</th>
							<th>
								操作
							</th>
						</tr>
						<#list pager.list as list>
							<tr>
								<td>
									<input type="checkbox" name="ids" value="${list.id}" />
								</td>
								<td>
									<a href="${base}/fieldmember/my_farming_block!content.action?id=${list.id}" target='_blank'>${list.code}</a>
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
									${list.seedName}
								</td>
								<td>
									${list.operateUser}
								</td>
								<td>
									${(list.operateTime?string('yyyy-MM-dd HH:mm:ss'))!}
								</td>
								<td>
									<a href="#" onclick="alert('undone');return false;" title="配送">[配送]</a>
								</td>
							</tr>
						</#list>
					</table>
					<#if (pager.list?size > 0)>
						<div class="pagerBar">
							<input type="button" class='seedingButton' url="${base}/fieldmember/my_farming_block!batchSeeding.action" value="播种" disabled hidefocus="true" />
							<input type="button" class='cleanButton' url="${base}/fieldmember/my_farming_block!batchClean.action" value="清理" disabled hidefocus="true" />
							<#include "/WEB-INF/template/admin/pager.ftl" />
						</div>
					<#else>
						<div class="noRecord">
							没有找到任何记录!
						</div>
					</#if>
				</form>
			</div>
			
		</div>
		<div class="blank"></div>
		<#include "/WEB-INF/template/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/WEB-INF/template/shop/footer.ftl">
</body>
</html>
