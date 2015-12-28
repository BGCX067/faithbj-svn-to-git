<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑种子</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/editor/kindeditor.js"></script>
<script charset="utf-8" src="${base}/vegetables/common/editor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${base}/vegetables/date97/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>

<script type="text/javascript">
$(function() {
	var editor = KindEditor.create('textarea[name="content"]');
	
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
			"startDate": "required",
			"endDate": "required",
			"type": "required"
		},
		messages: {
			"name": "请填写种子名称",
			"startDate": "请填写种子起始时间",
			"endDate": "请填写种子结束时间",
			"type": "请填写种子类型"
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
<body class="input">
	<div class="bar">
		<#if isAdd??>添加种子<#else>编辑种子</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAdd??>/faith/seed/save<#else>/faith/seed/update</#if>" method="post">
			<input type="hidden" name="id" value="${(seed.id)!}" />
			<table class="inputTable">
			<tr>
					<th>
						名称:
					</th>
					<td>
							<input type="text" name="name" value="${(seed.seedName)!}" class="formText"/>
							<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						起始日期:
					</th>
					<td>
						<input type="text" name="startDate" value="${(seed.startDate)!}"  onclick="WdatePicker()"/>
						
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						结束日期:
					</th>
					<td>
						<input type="text" name="endDate" value="${(seed.endDate)!}" onclick="WdatePicker()"/>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						类型:
					</th>
					<td>
						<select name="type">
						   <option value="">请选择...</option>
    					   <option value="${(seed.type)!1}" selected >默认种子</option>
    					   <option value="${(seed.type)!0}">自选种子</option>
					   </select>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<input type="text" name="description" value="${(seed.description)!}" "/>
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
