<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>草稿箱<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.tools.js"></script>
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

	var $deleteMessage = $("#messageTable .deleteMessage");
	
	// 删除
	$deleteMessage.click( function() {
		var $this = $(this);
		var messageId = $this.attr("messageId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteMessage});
		function deleteMessage() {
			$.ajax({
				url: "message!ajaxDelete.action",
				data: {id: messageId},
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					$.message({type: data.status, content: data.message});
					$this.parent().parent().remove();
				}
			});
		}
		return false;
	});
	
})
</script>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body messageDraftbox">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">草稿箱</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="messageTable" class="listTable">
						<tr>
							<th>标题</th>
							<th>收件人</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
						<#list pager.result as message>
							<tr>
								<td>
									<a href="${base}/shop/message!send.action?id=${message.id}">${message.title}</a>
								</td>
								<td>
									${(message.toMember.username)!"管理员"}
								</td>
								<td>
									${message.createDate?string("yyyy-MM-dd HH: mm")}
								</td>
								<td>
									<a href="${base}/shop/message!send.action?id=${message.id}">发送</a>&nbsp;&nbsp;
									<a href="#" class="deleteMessage" messageId="${message.id}">删除</a>
								</td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
         				<#include "/shop/pager.ftl">
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