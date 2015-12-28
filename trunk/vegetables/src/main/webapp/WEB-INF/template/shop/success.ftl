<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>提示信息 - Powered By faithbj</title>
<meta name="Author" content="faithbj Team" />
<meta name="Copyright" content="faithbj" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/shop/css/shop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/shop/js/shop.js"></script>
<script type="text/javascript">
$().ready( function() {
	
	function redirectUrl() {
		<#if redirectUrl??>
			window.location.href = "${redirectUrl}"
		<#else>
			window.history.back();
		</#if>
	}
	
	<@compress single_line = true>
		$.dialog({type: "success", title: "操作提示", content: 
		"<#if (actionMessages?? && actionMessages?size > 0)>
			<#list actionMessages as actionMessage>
				${actionMessage}&nbsp;
			</#list>
		<#else>
			您的操作已成功!
		</#if>", ok: "确定", okCallback: redirectUrl, cancelCallback: redirectUrl, width: 380, modal: true});
	</@compress>

});
</script>
</head>
<body class="success">
</body>
</html>