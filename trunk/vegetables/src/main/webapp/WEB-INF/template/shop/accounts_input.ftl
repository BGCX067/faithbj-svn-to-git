<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>增加收获记录<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
<script type="text/javascript" src="${base}/vegetables/date97/WdatePicker.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/vegetables/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
<script type="text/javascript">
$().ready( function() {

	var $tab = $("#tab");
	
	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"seed.seedName": "required"
			,
			"gainDate": "required"
			,
			"account": "required"
		},
		messages: {
			"seed.seedName": "请填写种子名称",
			"gainDate": "请填写种子收获的时间",
			"account": "请填写种子收获量"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body passwordInput">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top"><#if isAdd??>添加<#else>编辑</#if>收获记录</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="validateForm" action="<#if isAdd??>/cjlhome/account/save<#else>/cjlhome/account/update</#if>" method="post">
						<input type="hidden" name="id" value="${(account.id)!}" />
						<table class="inputTable">
							<tr>
								<th>
									种子: 
								</th>
								<td>
									<select name="seed.id">
									   <option value="">请选择...</option>
									   <#list seeds as seed>
											<option value="${seed.id}"<#if (seed.id == account.seed.id)!> selected </#if>>
												${seed.seedName}
											</option>
									    </#list>
								   </select>
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									收获时间: 
								</th>
								<td>
									<input type="text" name="gainDate" value="${(account.gainDate)!}" class="formText"  onclick="WdatePicker()"/>
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									收获量：
								</th>
								<td>
								<input type="text" name="account" value="${(account.account)!}" class="formText" />
								<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td>
								<input type="text" name="comment" value="${(account.comment)!}" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus />
								</td>
							</tr>
						</table>
						
					</form>
					<div class="blank"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "/shop/friend_link.ftl">
	</div>
	<div class="blank"></div>
	<#include "/shop/footer.ftl">
</body>
</html>