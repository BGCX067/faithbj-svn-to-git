<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发送消息<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
	
	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $messageForm = $("#messageForm");
	
	var $messageType = $(".messageType");
	var $toMemberTr = $("#toMemberTr");
	var $toMemberUsername = $("#toMemberUsername");
	
	$messageType.click( function(event) {
		if ($(this).val() == "member") {
			$toMemberTr.show();
			$toMemberUsername.attr("disabled", false);
		} else {
			$toMemberTr.hide();
			$toMemberUsername.attr("disabled", true);
		}
	});
	
	// 表单验证
	$messageForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"toMemberUsername": {
				"required": true,
				"notEqual": "${loginMember.username}",
				"remote": "message!checkUsername.action"
			},
			"message.title": "required",
			"message.content": "required"
		},
		messages: {
			"toMemberUsername": {
				"required": "请填写对方用户名",
				"notEqual": "对方用户名不允许为自己",
				"remote": "对方用户名不存在"
			},
			"message.title": "请填写标题",
			"message.content": "请填写消息内容"
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
	<div class="body messageSend">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">发送消息</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="messageForm" action="message!save.action" method="post">
						<table class="inputTable">
							<tr>
								<th>
									发送给: 
								</th>
								<td>
									<label><input type="radio" name="messageType" class="messageType" value="member"<#if (message == null || message.toMember??)!> checked</#if>>其它会员</label>
									<label><input type="radio" name="messageType" class="messageType" value="admin"<#if (message.toMember == null)!> checked</#if>>管理员</label>
								</td>
							</tr>
							<tr id="toMemberTr"<#if (!message.toMember??)!> class="hidden"</#if>>
								<th>
									对方用户名: 
								</th>
								<td>
									<input type="text" id="toMemberUsername" name="toMemberUsername" class="formText"<#if (!message.toMember??)!> disabled</#if> />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									标题: 
								</th>
								<td>
									<input type="text" name="message.title" class="formText" value="${(message.title)!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									内容: 
								</th>
								<td>
									<textarea name="message.content" class="formTextarea" rows="5" cols="50">${(message.content)!}</textarea>
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									选项: 
								</th>
								<td>
									<label><input type="radio" name="message.isSaveDraftbox" value="false" checked>立即发送</label>
									<label><input type="radio" name="message.isSaveDraftbox" value="true">保存到草稿箱</label>
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