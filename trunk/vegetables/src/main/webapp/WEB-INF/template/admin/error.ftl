<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>提示信息 - Powered By SHOP++</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/vegetables/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/vegetables/admin/js/admin.js"></script>
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
		$.dialog({type: "error", title: "操作提示", content: 
		"
		<#if errorMessages!"您的操作出现错误!">
		 ${errorMessages?replace('^\\[', '', 'r')?replace('\\]$', '', 'r')}<br>
		   您的操作出现错误!
		</#if>
		", ok: "确定", okCallback: redirectUrl, cancelCallback: redirectUrl, width: 380, modal: true});
	</@compress>

});
</script>
</head>
<body class="error">
</body>
</html>