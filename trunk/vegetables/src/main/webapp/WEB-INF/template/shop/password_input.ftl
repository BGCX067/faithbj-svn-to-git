<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>修改密码<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
	var $passwordForm = $("#passwordForm");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 表单验证
	$passwordForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"oldPassword": {
				requiredTo: "#password"
			},
			"member.password": {
				minlength: 4,
				maxlength: 20,
				requiredTo: "#oldPassword"
			},
			"rePassword": {
				equalTo: "#password"
			},
			"member.safeQuestion": {
				requiredTo: "#memberSafeAnswer"
			},
			"member.safeAnswer": {
				requiredTo: "#memberSafeQuestion"
			}
		},
		messages: {
			"oldPassword": {
				requiredTo: "请填写旧密码"
			},
			"member.password": {
				minlength: "密码长度必须大于等于4",
				maxlength: "密码长度必须小于等于20",
				requiredTo: "请填写新密码"
			},
			"rePassword": {
				equalTo: "两次密码输入不一致"
			},
			"member.safeQuestion": {
				requiredTo: "请填写安全问题"
			},
			"member.safeAnswer": {
				requiredTo: "请填写安全答案"
			}
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
	<div class="body passwordInput">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">修改密码</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="passwordForm" action="password!update.action" method="post">
						<ul id="tab" class="tab">
							<li>
								<input type="button" value="修改密码" hidefocus />
							</li>
							<li>
								<input type="button" value="安全问题" hidefocus />
							</li>
						</ul>
						<table class="inputTable tabContent">
							<tr>
								<th>
									旧密码: 
								</th>
								<td>
									<input type="password" id="oldPassword" name="oldPassword" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									新密码: 
								</th>
								<td>
									<input type="password" id="password" name="member.password" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									确认新密码: 
								</th>
								<td>
									<input type="password" name="rePassword" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示: 若密码留空则保持不变</span>
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
						<table class="inputTable tabContent">
							<tr>
								<th>
									安全问题: 
								</th>
								<td>
									<input type="text" id="memberSafeQuestion" name="member.safeQuestion" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									安全答案: 
								</th>
								<td>
									<input type="text" id="memberSafeAnswer" name="member.safeAnswer" class="formText" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示: 若安全问题留空则保持不变</span>
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