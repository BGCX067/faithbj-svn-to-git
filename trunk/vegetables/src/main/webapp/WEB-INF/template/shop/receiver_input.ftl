<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>收货地址<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/vegetables/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $receiverForm = $("#receiverForm");

	var $areaSelect = $("#areaSelect");

	// 地区选择菜单
	$areaSelect.lSelect({
		url: "${base}/cjlhome/area/ajaxArea"// AJAX数据获取url
	});
	
	// 表单验证
	$receiverForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"receiver.name": "required",
			"areaId": "required",
			"receiver.address": "required",
			"receiver.mobile": {
				"requiredOne": "#receiverPhone"
			},
			"receiver.zipCode": "required"
		},
		messages: {
			"receiver.name": "请输入收货人姓名",
			"areaId": "请选择地区",
			"receiver.address": "请输入地址",
			"receiver.mobile": {
				"requiredOne": "电话或手机必须填写其中一项"
			},
			"receiver.zipCode": "请输入邮编"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body receiverInput">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">收货地址</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="receiverForm" action="<#if isAddAction>${base}/cjlhome/receiver/save<#else>${base}/cjlhome/receiver/update</#if>" method="post">
						<input type="hidden" name="id" value="${id}" />
						<table class="inputTable">
							<tr>
								<th>
									收货人姓名: 
								</th>
								<td>
									<input type="text" name="name" class="formText" value="${(receiver.name)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									地区: 
								</th>
								<td>
									<input type="text" id="areaSelect" name="areaPath" class="hidden" value="${(receiver.area.id)!}" defaultSelectedPath="${(receiver.area.path)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									地址: 
								</th>
								<td>
									<input type="text" name="address" class="formText" value="${(receiver.address)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									电话: 
								</th>
								<td>
									<input type="text" id="receiverPhone" name="phone" class="formText" value="${(receiver.phone)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									手机: 
								</th>
								<td>
									<input type="text" name="mobile" class="formText" value="${(receiver.mobile)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									邮编: 
								</th>
								<td>
									<input type="text" name="zipCode" class="formText" value="${(receiver.zipCode)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									设置: 
								</th>
								<td>
									<label>
										<input type="checkbox" name="isDefault" <#if (receiver.isDefault)!true==true>checked="checked"</#if>/>默认收货地址
									</label>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus />
									<input type="button" class="backButton" onclick="window.history.back(); return false;" value="返 回" hidefocus />
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