<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑优惠活动 - Powered By <#--${systemConfig.systemName}--></title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />

<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加活动<#else>编辑优惠活动</#if></h1>
		</div>
		<div class="blank"></div>
		<form id="inputForm" class="validate" action="<#if isAdd??>deposit!save.action<#else>deposit!update.action</#if>" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="event.id" value="${event.id}" />
			<ul class="tab">
				<li>
					<input type="button" value="基本信息" hidefocus="true" />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						活动名称:
					</th>
					<td>
						<input type="text" name="event.eventname" class="formText {required: true}" value="${(event.eventname)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						赠送额度:
					</th>
					<td>
						<input type="text" class="formText" name="event.account" value="${(event.account)!}" <#--title="若留空则由系统随机生成"--> />
					</td>
				</tr>
				<tr>
					<th>
						产生日期:
					</th>
					<td>
						<input type="text" class="formText" name="event.createdate" value="${(event.createDate?string("yyyy-MM-dd HH:mm:ss"))!}" />
					</td>
				</tr>
				<tr>
					<th>
						过期日期:
					</th>
					<td>
						<input type="text" class="formText" name="event.expiredate" value="${(event.expiredate?string("yyyy-MM-dd HH:mm:ss"))!}"  />
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>
