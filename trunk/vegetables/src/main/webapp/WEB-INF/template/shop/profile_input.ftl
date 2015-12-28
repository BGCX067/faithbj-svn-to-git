<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>个人信息<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
<script type="text/javascript" src="${base}/vegetables/common/datePicker/WdatePicker.js"></script>
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
	var $validateForm = $("#validateForm");

	var $areaSelect = $("#areaSelect");

	// 地区选择菜单
	$areaSelect.lSelect({
		url: "${base}/shop/area!ajaxArea.action"// AJAX数据获取url
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"member.email": {
				required: true,
				email: true
			},
			"member.score": {
				required: true,
				digits: true
			},
			"member.deposit": {
				required: true,
				min: 0
			}
			<#list memberAttributeList as memberAttribute>
				<#if memberAttribute.isRequired || memberAttribute.attributeType == "number" || memberAttribute.attributeType == "alphaint">
					,"memberAttributeValueMap['${memberAttribute.id}']": {
						<#if memberAttribute.isRequired>
							<#if memberAttribute.attributeType == "number" || memberAttribute.attributeType == "alphaint">
								required: true,
							<#else>
								required: true
							</#if>
						</#if>
						<#if memberAttribute.attributeType == "number">
							<#if memberAttribute.attributeType == "alphaint">
								number: true,
							<#else>
								number: true
							</#if>
						</#if>
						<#if memberAttribute.attributeType == "alphaint">
							lettersonly: true
						</#if>
					}
				</#if>
			</#list>
		},
		messages: {
			"member.email": {
				required: "请填写E-mail",
				email: "E-mail格式不正确"
			},
			"member.score": {
				required: "请填写积分",
				digits: "积分必须为零或正整数"
			},
			"member.deposit": {
				required: "请填写预存款",
				min: "预存款必须为零或正数"
			}
			<#list memberAttributeList as memberAttribute>
				<#if memberAttribute.isRequired || memberAttribute.attributeType == "number" || memberAttribute.attributeType == "alphaint">
					,"memberAttributeValueMap['${memberAttribute.id}']": {
						<#if memberAttribute.isRequired>
							<#if memberAttribute.attributeType == "number" || memberAttribute.attributeType == "alphaint">
								required: "请填写${memberAttribute.name}",
							<#else>
								required: "请填写${memberAttribute.name}"
							</#if>
						</#if>
						<#if memberAttribute.attributeType == "number">
							<#if memberAttribute.attributeType == "alphaint">
								number: "${memberAttribute.name}只允许输入数字",
							<#else>
								number: "${memberAttribute.name}只允许输入数字"
							</#if>
						</#if>
						<#if memberAttribute.attributeType == "alphaint">
							lettersonly: "${memberAttribute.name}只允许输入字母"
						</#if>
					}
				</#if>
			</#list>
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
	<div class="body profileInput">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">个人信息</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="validateForm" action="/cjlhome/profile/update" method="post">
						<table class="inputTable">
							<tr>
								<th>
									E-mail: 
								</th>
								<td>
									<input type="text" name="email" class="formText" value="${(loginMember.email)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<#list memberAttributeList as memberAttribute>
								<tr>
									<th>
										${memberAttribute.name}: 
									</th>
									<td>
										<#if memberAttribute.systemAttributeType??>
											<#if memberAttribute.systemAttributeType == "name">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "gender">
												<label><input type="radio" name="memberAttributeValueMap['${memberAttribute.id}']" value="male"<#if (member.getMemberAttributeValue(memberAttribute) == "male")!> checked</#if> />男</label>
												<label><input type="radio" name="memberAttributeValueMap['${memberAttribute.id}']" value="female"<#if (member.getMemberAttributeValue(memberAttribute) == "female")!> checked</#if> />女</label>
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "birth">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" onclick="WdatePicker()" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "area">
												<input type="text" id="areaSelect" name="memberAttributeValueMap['${memberAttribute.id}']" class="hidden" value="${(member.getMemberAttributeValue(memberAttribute).id)!}" defaultSelectedPath="${(member.getMemberAttributeValue(memberAttribute).path)!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "address">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "zipCode">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "phone">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.systemAttributeType == "mobile">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											</#if>
										<#else>
											<#if memberAttribute.attributeType == "text">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.attributeType == "number">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.attributeType == "alphaint">
												<input type="text" name="memberAttributeValueMap['${memberAttribute.id}']" class="formText" value="${(member.getMemberAttributeValue(memberAttribute))!}" />
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.attributeType == "select">
												<select name="memberAttributeValueMap['${memberAttribute.id}']">
													<option value="">请选择...</option>
													<#list memberAttribute.optionList as option>
														<option value="${option}"<#if (option == member.getMemberAttributeValue(memberAttribute))!> selected</#if>>
															${option}
														</option>
													</#list>
												</select>
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											<#elseif memberAttribute.attributeType == "checkbox">
												<#list memberAttribute.optionList as option>
													<label>
														<input type="checkbox" name="memberAttributeValueMap['${memberAttribute.id}']" value="${option}"<#if (member.getMemberAttributeValue(memberAttribute).contains(option))!> checked</#if> />${option}
													</label>
												</#list>
												<#if memberAttribute.isRequired><label class="requireField">*</label></#if>
											</#if>
										</#if>
									</td>
								</tr>
							</#list>
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