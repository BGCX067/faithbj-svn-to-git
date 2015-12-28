<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>优惠活动- Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
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
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加优惠活动<#else>编辑优惠活动</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>deposit!saveCard.action<#else>deposit!update.action</#if>" method="post">
			<table class="inputTable">
				<tr>
					<th>
						活动名称:
					</th>
					<td>
						<input type="text" name="event.eventname" class="formText {required: true}" value="${(eventname)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						礼品卡金额:
					</th>
					<td>
						<select name="event.account" class="{required: true}">
							<option value="5">5元</option>
							<option value="10">10元</option>
							<option value="20">20元</option>	
							<option value="50">50元</option>
						</select>
						<label class="requireField">*</label>
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
