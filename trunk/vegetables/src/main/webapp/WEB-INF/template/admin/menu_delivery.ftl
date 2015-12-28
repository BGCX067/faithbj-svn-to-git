<#assign sec=JspTaglibs["/WEB-INF/ftl/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单</title>
<link href="${base}/vegetables/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/vegetables/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		
		<@sec.authorize ifAnyGranted="ROLE_BRAND">
			<dl>
				<dt>
					<span>配送管理&nbsp;</span>
				</dt>
				<dd>
					<a href="/faith/delivery_type/list" target="mainFrame">配送方式</a>
				</dd>
				<dd>
					<a href="/faith/area/list" target="mainFrame">地区管理</a>
				</dd>
				<dd>
					<a href="/faith/delivery_corp/list" target="mainFrame">物流公司</a>
				</dd>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>