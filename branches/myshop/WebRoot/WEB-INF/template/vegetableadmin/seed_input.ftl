<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑种子 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="MyShop Team" />
<meta name="Copyright" content="MyShop" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/date97/WdatePicker.js"></script>

<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加种子<#else>编辑种子</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>seed!save.action<#else>seed!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<div class="blank"></div>
			<#--<ul class="tab">
				<#if seed.type==1>
					<li>
						<input type="button" value="默认种子" hidefocus="true" />
					</li>
				<#else>
					<li>
						<input type="button" value="自选种子" hidefocus="true" />
					</li>
				</#if>
				-->
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						名称:
					</th>
					<td>
							<#if isAdd??>
							<input type="text" name="seed.name" class="formText {required: true, name: true, remote: 'seed!checkName.action?oldValue=${(seed.name?url)!}', minlength: 2, maxlength: 20, messages: {remote: '种子已存在,请重新输入!'}}" title="用户名只允许包含中文" />
							<label class="requireField">*</label>
						<#else>
							${seed.name}
							<input type="hidden" name="seed.name" value="${(seed.name)!}" />
						</#if>
					</td>
				</tr>
				<tr>
					<th>
						类型:
					</th>
					<td>
					<#if isAdd??>
						<select name="seed.type">
							<option value=true selected="selected" >
								默认种子
							</option>
							<option value=false >
								自选种子
							</option>
						</select>
					<#else>
						<#if seed.type==true>
							默认种子
						<#else>
							自选种子
						</#if>
					</#if>
					</td>
				</tr>
				<tr>
					<th>
						起始日期:
					</th>
					<td>
						<input type="text" name="seed.startDate" value="${(seed.startDate)!}"  onclick="WdatePicker()"/>
						
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						结束日期:
					</th>
					<td>
						<input type="text" name="seed.endDate" value="${(seed.endDate)!}" onclick="WdatePicker()"/>
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
