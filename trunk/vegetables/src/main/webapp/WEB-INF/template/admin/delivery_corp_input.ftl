<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑物流公司 - Powered By faithbj</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"name": "required",
			"orderList": "digits"
		},
		messages: {
			"name": "请填写物流公司名称",
			"orderList": "排序必须为零或正整数"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
})
</script>
</head>
<body class="input">
	<div class="bar">
		<#if isAddAction>添加物流公司<#else>编辑物流公司</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>save<#else>../update</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<table class="inputTable">
				<tr>
					<th>
						物流公司名称: 
					</th>
					<td>
						<input type="text" name="name" class="formText" value="${(deliveryCorp.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						物流公司网址: 
					</th>
					<td>
						<input type="text" name="url" class="formText" value="${(deliveryCorp.url)!}" />
					</td>
				</tr>
				<tr>
					<th>
						排序: 
					</th>
					<td>
						<input type="text" name="orderList" class="formText" value="${(deliveryCorp.orderList)!}" title="只允许输入零或正整数" />
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>