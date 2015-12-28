<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>收件箱<#if setting.isShowPoweredInfo> - Powered By faithbj</#if></title>
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
	var $showMessage = $("#messageTable .showMessage");
	
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
	
	// 显示消息内容
	$showMessage.click( function() {
		var $this = $(this);
		var $messageContentTr = $this.parent().parent().next(".messageContentTr");
		if ($.trim($messageContentTr.find("td").text()) == "") {
			var messageId = $this.attr("messageId");
			$.ajax({
				url: "message!ajaxShowMessage.action",
				data: {"id": messageId},
				type: "POST",
				dataType: "json",
				async: false,
				cache: false,
				beforeSend: function(data) {
					$messageContentTr.find("td").html('<span class="loadingIcon">&nbsp;</span> 加载中...');
				},
				success: function(data) {
					if (data.status == "success") {
						$messageContentTr.find("td").html(htmlEscape(data.content));
					} else {
						$.message({type: data.status, content: data.message});
					}
				}
			});
		}
		var $showMessageContentIcon = $this.prev("span");
		if ($showMessageContentIcon.hasClass("downIcon")) {
			$messageContentTr.show();
			$showMessageContentIcon.removeClass("downIcon").addClass("upIcon");
		} else {
			$messageContentTr.hide();
			$showMessageContentIcon.removeClass("upIcon").addClass("downIcon");
		}
		return false;
	});
	
})
</script>
<style type="text/css">
<!--

.messageInbox .messageContentTr {
	display: none;
	background-color: #fafafa;
}

.messageInbox .messageContentTr td {
	padding-left: 30px;
}

-->
</style>
</head>
<body class="memberCenter">
	<#include "/shop/header.ftl">
	<div class="body messageInbox">
		<#include "/shop/member_left.ftl">
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">收件箱</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="messageTable" class="listTable">
						<tr>
							<th>标题</th>
							<th>发件人</th>
							<th>已读</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
						<#list pager.result as message>
							<tr>
								<td>
									<span class="downIcon">&nbsp;</span>
									<a href="#" class="showMessage" messageId="${message.id}">${message.title}</a>
								</td>
								<td>
									${(message.fromMember.username)!"管理员"}
								</td>
								<td>
									<#if message.isRead>
										是
									<#else>
										<span class="red">否</span>
									</#if>
								</td>
								<td>
									${message.createDate?string("yyyy-MM-dd HH: mm")}
								</td>
								<td>
									<a href="${base}/shop/message!reply.action?id=${message.id}">回复</a>
									<a href="#" class="deleteMessage" messageId="${message.id}">删除</a>
								</td>
							</tr>
							<tr class="messageContentTr">
								<td colspan="5"></td>
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